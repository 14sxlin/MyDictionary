package dao

import entity.QueryEnglishWord
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linsixin on 2017/10/10.
  */
class TestResultDao extends FunSuite{

  val resultWord = entity.ResultWord("record")
  resultWord.sound = ArrayBuffer("sound1","sound2")
  resultWord.pron = "英 [ˈrekɔ:d] 美 [ˈrekərd]"
  resultWord.type_mean = ArrayBuffer(("n","记录"))

  test("query word from table"){ //apple has insert into table
    val word = ResultWordDao.queryOneResult("apple").orNull
    assert(word != null)
    println(word.query + " " + word.pron + " " + word.type_mean.mkString("\r\n"))
    assert(word.type_mean.nonEmpty)
  }

  test("save result"){
    ResultWordDao.saveResult(resultWord)
    val word = ResultWordDao.queryOneResult("record").orNull
    assert(word != null)
    println(word.query + " " + word.pron + " " + word.type_mean.mkString("\r\n"))
    assert(word.type_mean.nonEmpty)
  }


}
