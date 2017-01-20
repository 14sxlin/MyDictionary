package connection

import entity._

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * Created by LinSixin on 2016/12/26.
  */
object EngPattern{

  val engBlockPattern = """<li class="clearfix">[\s\S]+?</li>""".r
  /*������������,�������õ���,���Ǿ��Ի�������,��Ϊ�仯̫����*/

  //  val engBlockPattern = """<li class="clearfix">(\r\n|.)+?</li>""".r
  //������(\s|.)�Ῠס
  /*+����Ӹ��ʺ�ʹ�÷�̰��ģʽ�ͺ�������*/

  val engPropPattern = """<span class="prop">(.+)</span>""".r   //ƥ�����
  val engMeanPattern = """<span>(.+)</span>""".r                //ƥ�京��
  //  val ePronSig = """[\s]+<span>Ӣ[\s\S]*</span>""".r        //ƥ��Ӣʽ����
  //  val aPronSig = """[\s]+<span>��[\s\S]*</span>""".r        //ƥ����ʽ����
  val engPron ="""<div class="base-speak">[\s\S]*?</div>""".r   //ƥ�䷢����

  //ƥ�䷢����Ƶ����ַ
  val soundPattern ="""<i class="new-speak-step" ms-on-mouseover="sound\('([\S]+)'\)">""".r
  val engSentencePattern = """<div style="width: 580px; margin-top: 15px; font-size: 15px; line-height: 24px; color: #333333;">([\S]+)</div>""".r
  val engPhrasePattern = """<span class="prop">����</span>[\s]+<p>[\s]+<span>(.+)</span>""".r
  val engPhrasePattern1 ="""<span class="prop">[\s\S]+?</p>""".r
  val contentInPhrase = """<span>([\s\S]+?)</span>""".r
  /*ʹ��������ʽƥ���ǩ�Ļ�,��һ��ĩβҪ��һ���Ľ�����*/
}

object ChinPattern{
  val chinSentencePattern = """<div style="width: 580px; margin-top: 15px; font-size: 18px; line-height: 24px; color: #333333;">([\s\S]+?)</div>""".r
  /*([\s\S]+?)������̰��ģʽ �ͻ�ƥ�䵽�����</div>
  * �����([\s\S]+) ����̰��ģʽ ���޷�ƥ���</div> ����ƥ�䵽����</div>*/


}


object ResultFormatter {

  import EngPattern._
  import ChinPattern._

  /**
    * ���ݲ�ͬ�����ͽ��в�ͬ�Ĳ�ѯ
    * @param query ��װ�Ĳ�ѯ����
    * @return ������Ӧ�Ľ������
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
    * ��ѯӢ�ĵ���,����ȡ����Ϣ
    * @param query Ҫ��ѯ��Ӣ�ĵ���,����ʵ������QWord , QueryContent������
    * @return Word���͵ķ���ֵ
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
    * ��ѯӢ�����
    * @param queryContent ��ѯ�ľ���,Ӧ����QESentence����
    * @param regex ������ʽ
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
    * ��ѯ���������
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
    * ƥ����һ������µľ���,�����Ƕ�Ӧ I love you�ľ��ӵ����
    * @param queryContent ��ѯ�ľ���,Ӧ����QESentence����
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
    * ���������formatSentence �Զ�����ͬ�����
    * @param eSentence ��ѯ�ľ���,Ӧ����QESentence����
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
