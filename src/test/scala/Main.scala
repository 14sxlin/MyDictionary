
import java.net.URLEncoder

import utils.WebQueryUtils.WEBSITE
import entity._
import utils.WebQueryUtils

import scala.io.Source

/**
  * Created by LinSixin on 2016/12/26.
  */
object Main extends App{
//  val queryContent = ConnectionTool.queryFromWebsite(QEWord("hello"))
//  //  val queryContent = ConnectionTool.queryFromFile(QEWord("cook"),"test\\temp.txt")
//
//  val result = ResultFormatter.format(queryContent)
//
//  result match {
//    case w @ dao.Word(from)=>
//      println(from+"  "+w.type_mean)
//      println(w.pron)
//    case _ => println("nothing")
//  }
//
//  val qc1 = ConnectionTool.queryFromWebsite(QESentence("i love you"))
//
//  //  val result1 = ResultFormatter.formatPhrase(qc1)
//  val result1 = ResultFormatter.format(qc1)
//  result1 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }
////
//  val qc2 = ConnectionTool.queryFromWebsite(QESentence("hello world"))
////    val result2 = ResultFormatter.formatPhrase(qc2)
//  val result2 = ResultFormatter.format(qc2)
//  result2 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }
//
//  val qc3 = ConnectionTool.queryFromWebsite(QESentence("so what"))
////    val result2 = ResultFormatter.formatPhrase(qc2)
//  val result3 = ResultFormatter.format(qc3)
//  result3 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }
//
//  val qc4 = ConnectionTool.queryFromWebsite(QCSentence("那又怎么样"))
////  val result4 = ResultFormatter.formatChinSentence(qc4)
//  val result4 = ResultFormatter.format(qc4)
//  result4 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }
//
//  val qc5 = ConnectionTool.queryFromWebsite(QCSentence("我爱你"))
//    val result5 = ResultFormatter.formatSentence1(qc5)
////  val result5 = ResultFormatter.format(qc5)
//  result5 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }
//
//  val qc6 = ConnectionTool.queryFromWebsite(QESentence("I love you"))
//  val result6 = ResultFormatter.format(qc6)
//  //  val result5 = ResultFormatter.format(qc5)
//  result6 match {
//    case s @ Sentence(from)=>
//      println(from+"  "+s.meaning)
//  }

  private val WEBSITE = "http://www.iciba.com/"
  val url = WEBSITE+URLEncoder.encode("hello","utf-8")
  println(url)
  val tempResult = Source.fromURL(url,"utf-8").mkString
  println(tempResult)

}
