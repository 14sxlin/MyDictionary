package entity

import java.net.URL

import org.jsoup.Jsoup
import org.scalatest._
import utils.WebQueryUtils

/**
  * Created by LinSixin on 2016/12/27.
  */
@Deprecated
class TestResultFormatter extends FunSuite{

//  test("engPron and soundPattern should work well"){
//    val exp = new Array[String](2)
//    exp(0) = "http://res.iciba.com/resource/amp3/oxford/0/43/7a/437a6065ca6f18f653616caabe015f2a.mp3"
//    exp(1) = "http://res.iciba.com/resource/amp3/1/0/56/ab/56ab24c15b72a457069c5ea42fcfc640.mp3"
//    val content = Source.fromFile("test\\happy.txt","utf-8").mkString
//    var i = 0
//    for (pron <- ResultFormatter.engPron findAllIn content) {
//      for( sound <- ResultFormatter.soundPattern findAllIn pron)
//      {
////        println(sound)
//        val ResultFormatter.soundPattern(real) = sound
//        assert(exp(i)==real)
//        i+=1
//      }
//
//    }
//
//  }

//  test("sentencePattern should work well"){
//    val exp = "在什么情况下"
//    val content = Source.fromFile("test\\inwhatcase.txt","utf-8").mkString
//    val r = ResultFormatter.engSentencePattern findFirstIn content
////    print(r)
//    r match {
//      case Some(ResultFormatter.engSentencePattern(real)) =>assert(real==exp)
//      case _ => throw new Exception("wrong regex sentencePattern")
//    }
//
//  }

//  test("prasePattern should work well") {
//    val exp = "那又怎么样！；"
//    val content = Source.fromFile("test\\sowhat.txt", "utf-8").mkString
//    val r = EngPattern.engPhrasePattern findFirstIn content
//    r match {
//      case Some(EngPattern.engPhrasePattern(real)) =>
//        print(real)
//        assert(real == exp)
//      case _ => throw new Exception("wrong regex sentencePattern")
//    }
//  }

//  test("phrase pattern1"){
//    val pattern = """<span class="prop">[\s\S]+</span>""".r
//    val exp = ""
//    val content = Source.fromFile("test\\cases\\我爱你.txt", "utf-8").mkString
////    print(content)
//    for(r <- pattern findAllIn content)
//      for(c <- EngPattern.contentInphrase findAllIn r )
//        println(c)
//  }
//    @Ignore
    test("format word"){
      val queryContent = QueryEnglishWord("apple")
      val source = WebQueryUtils.queryFromWebsite(queryContent)
      val result = ResultFormatter.formatWord(source).asInstanceOf[ResultWord]
      assert(("n.","苹果；苹果树；苹果公司；")==result.type_mean(0))
      assert(2 == result.sound.size)
      assert(result.pron == "英 [ˈæpl] 美 [ˈæpəl]")
//      print(result.pron)
      val queryContent1= QueryEnglishWord("do")
      val source1 = WebQueryUtils.queryFromWebsite(queryContent1)
      val result1 = ResultFormatter.formatWord(source1).asInstanceOf[ResultWord]
      assert(2 == result1.sound.size)
    }

//    @Ignore
    test("获得音标"){
      val url = "http://www.iciba.com/apple"
      val doc = Jsoup.parse(new URL(url).openStream(),"utf-8",url)
      val prouns = doc.select("div[class=base-speak]")
      val text = prouns.text()
//      print(text)
      assert(text=="英 [ˈæpl] 美 [ˈæpəl]")
    }

  /**
    * 英语句子 比如 in what case 看似短语 实际上是个句子
    */
  test("查询英文句子"){
    val ephrase = QueryContentFactory.create("in what case")
    val queryContent = WebQueryUtils.queryFromWebsite(ephrase)
    val sentence = ResultFormatter.formatSentence(ephrase)
    assert(sentence.meaning=="在什么情况下")
  }

  /**
    *   I love you 这种句子对应的是句子中的第二种情况
    *   这种显示的方式跟 formatWord显示的方式是一样的
    */
  test("查询英语句子 类型1"){
    val eSentence = QueryContentFactory.create("I love you")
    val source = WebQueryUtils.queryFromWebsite(eSentence)
    val sentence =  ResultFormatter.formatSentence1(eSentence)
    assert(sentence.meaning.trim == "n. 我爱你；".trim)
  }

  test("查询英语短语"){
    val eSentence = QueryContentFactory.create("holy shit")
    val source = WebQueryUtils.queryFromWebsite(eSentence)
    val sentence =  ResultFormatter.formatSentence1(eSentence)
    assert(sentence.meaning.trim == "释义 天啊(=holy cats)；".trim)
  }

  /**
    * 查询中文句子 类型和上面的英语句子的类型1 相同
    * 可能查出多重解释 但是其中的<日> <德> 这种符号会被过滤
    */
  test("查询中文句子"){
    val eSentence = QueryContentFactory.create("我爱你")
    val source = WebQueryUtils.queryFromWebsite(eSentence)
    val sentence =  ResultFormatter.formatSentence(source)
    assert(sentence.meaning.trim == ("名 I love you； " +
      "Je t’aime； " +
      "Ich liebe Dich； " +
      "わたしはあなたをあいしています").trim)
//    print(sentence.meaning)
  }

}
