package entity

import scala.collection.mutable.ArrayBuffer

/**
  * 表示查询结果的类
  * Created by LinSixin on 2016/12/26.
  */
sealed class Result(val from:String)


case class Word(mfrom:String) extends Result(mfrom){
  var pron = ""                                        /*发音*/
  var sound = new ArrayBuffer[String]()                /*音频网址*/
  var type_mean = new ArrayBuffer[(String,String)]()   /*词性,相应的翻译*/
}

case class Sentence(mfrom:String) extends Result(mfrom){
  var meaning = ""
}



