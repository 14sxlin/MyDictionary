package dao

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linsixin on 2017/10/10.
  */
class TestWordTransformation extends FunSuite{

  val resultWord = entity.ResultWord("record")
  resultWord.sound = ArrayBuffer("sound1","sound2")
  resultWord.pron = "英 [ˈrekɔ:d] 美 [ˈrekərd]"
  resultWord.type_mean = ArrayBuffer(("n","记录"))

  test("result word to entity word"){
    val word = WordTransformation.resultWord2EntityWord(resultWord)
    assert(word.getEng == "apple")
    assert(word.getEngPron == "[ˈrekɔ:d]")
    assert(word.getUsaPron == "[ˈrekərd]")
  }

  test("entity word to result word"){

  }
}
