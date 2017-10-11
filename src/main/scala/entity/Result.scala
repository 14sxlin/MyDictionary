package entity

import scala.collection.mutable.ArrayBuffer

/**
  * 表示查询结果的类
  * Created by LinSixin on 2016/12/26.
  */
sealed trait Result

object Result{
  val empty = ResultWord("")
}

/**
  * 表示查询的结果
  * @param query 查询的单词
  */
case class ResultWord(query:String) extends Result{
  var pron = ""                                        /*发音*/
  var sound = new ArrayBuffer[String]()                /*音频网址*/
  var type_mean = new ArrayBuffer[(String,String)]()   /*词性,相应的翻译*/

  override def toString : String = {
    var value = query + "\n" + pron + "\n"
    for((wtype,wmean) <- type_mean){
      value += wtype+" "+wmean+"\n"
    }
    value
  }
}

case class ResultSentence(query:String) extends Result{
  var meaning = ""

  override def toString : String = {
    query + "\n" + meaning+"\n"
  }
}



