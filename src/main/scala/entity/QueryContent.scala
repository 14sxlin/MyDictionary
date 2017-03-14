package entity

/**
  * Created by LinSixin on 2016/12/26.
  */

/**
  * 查询内容
  * @param content  要查询的内容
  * @param result 未格式化的查询结果
  */
sealed abstract class QueryContent(val content:String,var result:String="")

/**
  * 查询英语句子
  * @param sentence
  */
case class QESentence(sentence:String) extends QueryContent(sentence)

/**
  * 查询英语单词
  * @param word
  */
case class QEWord(word:String) extends QueryContent(word)

/**
  * 查询中文单词
  * @param word
  */
case class QCWord(word:String) extends QueryContent(word)

/**
  * 查询中文句子
  * @param sentence
  */
case class QCSentence(sentence: String) extends QueryContent(sentence)