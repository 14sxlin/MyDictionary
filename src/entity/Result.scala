package entity

import scala.collection.mutable.ArrayBuffer

/**
  * ��ʾ��ѯ�������
  * Created by LinSixin on 2016/12/26.
  */
sealed class Result(val from:String)


case class Word(mfrom:String) extends Result(mfrom){
  var pron = ""                                        /*����*/
  var sound = new ArrayBuffer[String]()                /*��Ƶ��ַ*/
  var type_mean = new ArrayBuffer[(String,String)]()   /*����,��Ӧ�ķ���*/
}

case class Sentence(mfrom:String) extends Result(mfrom){
  var meaning = ""
}



