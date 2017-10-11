package connection
import entity.{QueryEnglishSentence, QueryEnglishWord, ResultSentence, ResultWord}
import org.scalatest._

import scala.io.Source
/**
  * Created by LinSixin on 2016/12/27.
  */
class ResultFormatterTest extends FunSuite{

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
//    val exp = "��ʲô�����"
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
//    val exp = "������ô������"
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
//    val content = Source.fromFile("test\\cases\\�Ұ���.txt", "utf-8").mkString
////    print(content)
//    for(r <- pattern findAllIn content)
//      for(c <- EngPattern.contentInphrase findAllIn r )
//        println(c)
//  }

}
