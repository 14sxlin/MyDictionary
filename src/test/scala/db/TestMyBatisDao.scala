package db

import dao.{Word, WordExample, WordMapper}
import mybatis.GetSqlSession

import scala.collection.JavaConverters._
import scala.collection.mutable
/**
  * Created by linsixin on 2017/10/10.
  */
object TestMyBatisDao extends App{
  val sqlSession = GetSqlSession.getSqlSession
  val wordMapper = sqlSession.getMapper(classOf[dao.WordMapper])

  def foreachPrintWord(words:mutable.Buffer[dao.Word]):Unit = {
    words.foreach { word =>
      println(word.getId + "\t" + word.getEng + "\t")
    }
  }

  def queryAll(): Unit ={
    val wordExample = new dao.WordExample()
    foreachPrintWord(wordMapper.selectByExample(wordExample).asScala)
  }

  def insert() :Unit = {
    val newWord = new dao.Word
    newWord.setEng("love")
    newWord.setEngPron("[lov]")
    newWord.setUsaPron("[lov]")
    wordMapper.insert(newWord)
  }



  def deleteWhereId(id:Long): Unit = {
    val count = wordMapper.deleteByPrimaryKey(id)
    println(s"delete : $count")
    queryAll()
  }


//  deleteWhereId(6L)
  insert()
  sqlSession.commit()
  queryAll()
  sqlSession.close()
}
