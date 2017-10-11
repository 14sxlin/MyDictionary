package dao

import entity.QueryEnglishWord
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linsixin on 2017/10/10.
  */
class TestWordDao extends FunSuite{

  val resultWord = entity.ResultWord("record")
  resultWord.sound = ArrayBuffer("sound1","sound2")
  resultWord.pron = "英 [ˈrekɔ:d] 美 [ˈrekərd]"
  resultWord.type_mean = ArrayBuffer(("n","记录"))

  test("query one  word from table"){ //apple has insert into table
    val word = WordDao.queryOneWord("apple").orNull
    assert(word != null)
    println(word.getId + " " + word.getEng  + " " + word.getEngPron + " " + word.getUsaPron)
  }


}
