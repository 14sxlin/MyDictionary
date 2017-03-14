package connection

import entity._
import org.jsoup.Jsoup

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

/**
  * 解析Html获取相应的结果
  */
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
      case s:QESentence =>formatPhrase(s)
      case c:QCSentence => formatSentence(c)
      case _ => throw new Exception("unprocess")
    }
  }

  /**
    * 查询英文单词,并提取出信息,如果无法解析,返回formatPhrase
    * @param query 要查询的英文单词,类型实际是QWord , QueryContent的子类
    * @return Result类型的返回值
    */
  def formatWord(query:QueryContent):Result = {
//    println("format word")
    val doc = Jsoup.parse(query.result)
    val result =  Word(query.content)
    val elems = doc.select("li[class=clearfix]")
      .select("span")
    var mean = ""
    var mtype = ""
    for(i <- 0 until elems.size())    // 解析单词的中文含义
    {
      val e = elems.get(i)
      if(e.hasClass("prop"))
      {
        if(mtype != ""){
          result.type_mean += ((mtype,mean))
        }
        mean = ""
        mtype = e.text()
      }
      else{
        mean += e.text()
      }
    }
    result.type_mean += ((mtype,mean))
    try {
      val prouns = doc.select("div[class=base-speak]")
      result.pron += prouns.text()
      for (i <- 0 until 2) {
        // 音标
        var s = prouns.select("i[class=new-speak-step]").get(i).attr("ms-on-mouseover")
        val regex = """sound\('(.*)'\)""".r
        val regex(tempSound) = s
        result.sound += tempSound
      }
    }catch {
      case _:IndexOutOfBoundsException => return formatSentence(query)
    }
    result
  }

  /**
    * 查询英语句子 比如 in what case
    * @param queryContent 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentence(queryContent: QueryContent):Sentence ={
//    println("format sentence")
    val sentence = Sentence(queryContent.content)
    val doc = Jsoup.parse(queryContent.result)
    val elems = doc.select("div[class=clearfix]")
                      .select("div[style]")
//    println("elem = "+elems.text)
    if (elems.text.trim == "") return formatSentence1(queryContent)
    try {
      sentence.meaning = elems.text
    }catch {
      case e:Exception=>
        println(e.getStackTrace)
        return formatSentence1(queryContent)
    }
    sentence
  }

  /**
    * 匹配另一种情况下的句子,这种是对应 I love you的句子的情况
    * @param queryContent 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentence1(queryContent: QueryContent) = {
//    println("format sententce1")
    val sentence = Sentence(queryContent.content)
    val doc = Jsoup.parse(queryContent.result)
    val elems = doc.select("li[class=clearfix]")
      .select("span")
    sentence.meaning = elems.text
    sentence
  }

  /**
    * 查询短语的释义
    * @param queryContent sentence
    * @return
    */
  def formatPhrase(queryContent: QueryContent):Sentence ={
//    println("format phrase")
    try {
      val wordResult = formatWord(queryContent).asInstanceOf[Word]
      val sentent = Sentence(wordResult.mfrom)
      sentent.meaning = wordResult.type_mean(0)._1 + wordResult.type_mean(0)._2
      sentent
    }catch{
      case e:ClassCastException=>
        println(e)
        return formatSentence(queryContent)
    }

  }

}
