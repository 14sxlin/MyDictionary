package control

import entity._
import org.scalatest.FunSuite

/**
  * Created by sparr on 2017/1/18.
  */
class ContentWrapperTest extends FunSuite{
  val chineseWord = "���"
  val englishWord = "hello"
  val englishSentence = "what do you want"
  val chineseSentence = "����"
  test("wrapper should work well"){
    assert(QueryContentFactory.create(chineseWord,false,true).isInstanceOf[QueryChineseWord])
    assert(QueryContentFactory.create(chineseSentence,false,false).isInstanceOf[QueryChineseSentence])
    assert(QueryContentFactory.create(englishSentence,true,false).isInstanceOf[QueryEnglishSentence])
    assert(QueryContentFactory.create(englishWord,true,true).isInstanceOf[QueryEnglishWord])
  }
}
