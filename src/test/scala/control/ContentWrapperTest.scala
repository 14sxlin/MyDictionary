package control

import entity.{QCSentence, QCWord, QESentence, QEWord}
import org.scalatest.FunSuite

/**
  * Created by sparr on 2017/1/18.
  */
class ContentWrapperTest extends FunSuite{
  val chineseWord = "你好"
  val englishWord = "hello"
  val englishSentence = "what do you want"
  val chineseSentence = "滚开"
  test("wrapper should work well"){
    assert(ContentWrapper.wrap(chineseWord,false,true).isInstanceOf[QCWord])
    assert(ContentWrapper.wrap(chineseSentence,false,false).isInstanceOf[QCSentence])
    assert(ContentWrapper.wrap(englishSentence,true,false).isInstanceOf[QESentence])
    assert(ContentWrapper.wrap(englishWord,true,true).isInstanceOf[QEWord])
  }
}
