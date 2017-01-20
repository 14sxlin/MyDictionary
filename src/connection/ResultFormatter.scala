package connection

import entity._

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * Created by LinSixin on 2016/12/26.
  */
object EngPattern{

  val engBlockPattern = """<li class="clearfix">[\s\S]+?</li>""".r
  /*经过各种试验,这个是最好的了,但是绝对还有问题,因为变化太多了*/

  //  val engBlockPattern = """<li class="clearfix">(\r\n|.)+?</li>""".r
  //这里用(\s|.)会卡住
  /*+后面加个问号使用非贪婪模式就很有灵性*/

  val engPropPattern = """<span class="prop">(.+)</span>""".r   //匹配词性
  val engMeanPattern = """<span>(.+)</span>""".r                //匹配含义
  //  val ePronSig = """[\s]+<span>英[\s\S]*</span>""".r        //匹配英式音标
  //  val aPronSig = """[\s]+<span>美[\s\S]*</span>""".r        //匹配美式音标
  val engPron ="""<div class="base-speak">[\s\S]*?</div>""".r   //匹配发音段

  //匹配发音音频的网址
  val soundPattern ="""<i class="new-speak-step" ms-on-mouseover="sound\('([\S]+)'\)">""".r
  val engSentencePattern = """<div style="width: 580px; margin-top: 15px; font-size: 15px; line-height: 24px; color: #333333;">([\S]+)</div>""".r
  val engPhrasePattern = """<span class="prop">释义</span>[\s]+<p>[\s]+<span>(.+)</span>""".r
  val engPhrasePattern1 ="""<span class="prop">[\s\S]+?</p>""".r
  val contentInPhrase = """<span>([\s\S]+?)</span>""".r
  /*使用正则表达式匹配标签的话,不一定末尾要是一样的结束符*/
}

object ChinPattern{
  val chinSentencePattern = """<div style="width: 580px; margin-top: 15px; font-size: 18px; line-height: 24px; color: #333333;">([\s\S]+?)</div>""".r
  /*([\s\S]+?)开启非贪婪模式 就会匹配到后面的</div>
  * 如果是([\s\S]+) 开启贪婪模式 就无法匹配的</div> 而是匹配到最后的</div>*/


}


object ResultFormatter {

  import EngPattern._
  import ChinPattern._

  /**
    * 根据不同的类型进行不同的查询
    * @param query 封装的查询类型
    * @return 返回相应的结果类型
    */
  def format(query:QueryContent):Result = {
    query match {
      case q:QEWord => formatWord(q)
      case cw:QCWord => formatWord(cw)
      case s:QESentence =>formatSentenceChain(s)
      case c:QCSentence => formatSentence(c,chinSentencePattern)
      case _ => throw new Exception("unprocess")
    }
  }

  /**
    * 查询英文单词,并提取出信息
    * @param query 要查询的英文单词,类型实际上是QWord , QueryContent的子类
    * @return Word类型的返回值
    */
  def formatWord(query:QueryContent):Word = {
    val content = query.result
//    val content = Source.fromFile("test\\temp.txt","utf-8").mkString
//    assert(content1==content)
    var rtype = new ArrayBuffer[String]()
    var rmean = new ArrayBuffer[String]()

    val w = Word(query.content)

    for (block <- engBlockPattern findAllIn content) {
//      println(block)
      var temp = ""
      for (prop <- engPropPattern findAllIn block) {
        prop match {
          case engPropPattern(c) => rtype += c
          case _ => println("unknown")
        }
      }
      for (mean <- engMeanPattern findAllIn block) {
        mean match {
          case engMeanPattern(c) => temp += c
          case _ => println("unknown")
        }

      }
      rmean += temp

    }

    for (pron <- engPron findAllIn content ) {
      //      println("pron = " + pron)
      for (sound <- soundPattern findAllIn pron)
        sound match {
          case soundPattern(p) =>
            w.sound += p
          //            println(p)
          case _ => throw new Exception("no sound")
        }
    }
//    val eprop = ePronSig findFirstIn content
//    val aprop = aPronSig findFirstIn content
//    eprop match{
//      case Some(ePronSig(r)) => w.pron= r+"  "+aprop.get
//      case _ => w.pron = "unknown"
//    }
    w.type_mean = rtype zip rmean
    w
  }

  /**
    * 查询英语句子
    * @param queryContent 查询的句子,应该是QESentence类型
    * @param regex 正则表达式
    * @return
    */
  def formatSentence(queryContent: QueryContent,regex: Regex):Sentence ={
    val sentence = Sentence(queryContent.content)
    val content = queryContent.result
    val r = regex findFirstIn content
    r match {
      case Some(regex(result)) => sentence.meaning = result
      case _ => sentence.meaning = "unknown"
    }
    sentence
  }

  /**
    * 查询短语的释义
    * @param queryContent sentence
    * @return
    */
  def formatPhrase(queryContent: QueryContent):Sentence ={
    val sentence = Sentence(queryContent.content)
    val content = queryContent.result
    val r = engPhrasePattern findFirstIn content
    r match {
      case Some(engPhrasePattern(result)) => sentence.meaning = result
      case _ => sentence.meaning = "unknown"
    }
    sentence
  }

  /**
    * 匹配另一种情况下的句子,这种是对应 I love you的句子的情况
    * @param queryContent 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentence1(queryContent: QueryContent) = {
    val sentence = Sentence(queryContent.content)
    val content = queryContent.result
//    print(content)
    for(r <- engPhrasePattern1 findAllIn content) {
//      println(r)
      for (c <- contentInPhrase findAllIn r) {
        //        println(c)
        c match {
          case contentInPhrase(result) =>
//            print(result)
            sentence.meaning += (result+"\n")
          case _ => sentence.meaning = "unknown"
        }
      }
    }
    sentence
  }

  /**
    * 整合上面的formatSentence 自动处理不同的情况
    * @param eSentence 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentenceChain(eSentence: QueryContent)={
    val r = formatPhrase(eSentence)
    r match{
      case _ if r.meaning.trim=="unknown"=>
        val r1 = formatSentence(eSentence,engSentencePattern)
        r1 match {
          case _ if r1.meaning=="unknown" =>
            formatSentence1(eSentence)
          case _ => r1
        }
      case _ => r
    }
  }

}
