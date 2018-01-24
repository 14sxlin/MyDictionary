import entity.{ResultParser, _}
import utils.WebQueryUtils

/**
  * Created by LinSixin on 2016/12/26.
  */
object Main extends App{
  val queryContent = WebQueryUtils.queryFromWebsite(QueryEnglishWord("hello"))
  //  val queryContent = ConnectionTool.queryFromFile(QEWord("cook"),"test\\temp.txt")

  val result = ResultParser.parse(queryContent)

  result match {
    case w @ ResultWord(from)=>
      println(from+"  "+w.type_mean)
      println(w.pron)
  }

  val qc1 = WebQueryUtils.queryFromWebsite(QueryEnglishSentence("i love you"))

  //  val result1 = ResultFormatter.formatPhrase(qc1)
  val result1 = ResultParser.parse(qc1)
  result1 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }
//
  val qc2 = WebQueryUtils.queryFromWebsite(QueryEnglishSentence("hello world"))
//    val result2 = ResultFormatter.formatPhrase(qc2)
  val result2 = ResultParser.parse(qc2)
  result2 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }

  val qc3 = WebQueryUtils.queryFromWebsite(QueryEnglishSentence("so what"))
//    val result2 = ResultFormatter.formatPhrase(qc2)
  val result3 = ResultParser.parse(qc3)
  result3 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }

  val qc4 = WebQueryUtils.queryFromWebsite(QueryChineseSentence("������ô��"))
//  val result4 = ResultFormatter.formatChinSentence(qc4)
  val result4 = ResultParser.parse(qc4)
  result4 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }

  val qc5 = WebQueryUtils.queryFromWebsite(QueryChineseSentence("�Ұ���"))
    val result5 = ResultParser.tryParseSentence1(qc5)
//  val result5 = ResultFormatter.format(qc5)
  result5 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }

  val qc6 = WebQueryUtils.queryFromWebsite(QueryEnglishSentence("I love you"))
  val result6 = ResultParser.parse(qc6)
  //  val result5 = ResultFormatter.format(qc5)
  result6 match {
    case s @ ResultSentence(from)=>
      println(from+"  "+s.meaning)
  }



}
