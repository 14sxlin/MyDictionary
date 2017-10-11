package entity

import org.scalatest.FunSuite

/**
  * Created by sparr on 2017/1/18.
  */
class TestQueryContentFactory extends FunSuite{

  val chineseWord = "你好"
  val englishWord = "hello"
  val englishSentence = "what do you want"
  val chineseSentence = "滚开"
  test("wrapper should work well"){
    assert(QueryContentFactory.create(chineseWord,isEng = false,isWord = true).isInstanceOf[QueryChineseWord])
    assert(QueryContentFactory.create(chineseSentence,isEng = false,isWord = false).isInstanceOf[QueryChineseSentence])
    assert(QueryContentFactory.create(englishSentence,isEng = true,isWord = false).isInstanceOf[QueryEnglishSentence])
    assert(QueryContentFactory.create(englishWord,isEng = true,isWord = true).isInstanceOf[QueryEnglishWord])
  }
}
