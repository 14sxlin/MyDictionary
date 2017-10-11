package entity

import org.jsoup.Jsoup

/**
  * Created by linsixin on 2016/12/26.
  */

/**
  * 解析Html获取相应的结果
  */
object ResultFormatter {

  /**
    * 根据不同的类型进行不同的查询
    * @param query 封装的查询类型
    * @return 返回相应的结果类型
    */
  def format(query:QueryContent):Result = {
    query match {
      case q:QueryEnglishWord => formatWord(q)
      case cw:QueryChineseWord => formatWord(cw)
      case s:QueryEnglishSentence =>formatPhrase(s)
      case c:QueryChineseSentence => formatSentence(c)
      case _ => throw new Exception("unprocess")
    }
  }

  /**
    * 查询英文单词,并提取出信息,如果无法解析,返回formatPhrase
    * @param query 要查询的英文单词,类型实际是QueryWord , QueryContent的子类
    * @return Result类型的返回值
    */
  def formatWord(query:QueryContent):Result = {
//    println("format word")
    val doc = Jsoup.parse(query.result)
    val result =  ResultWord(query.content)
    val elems = doc.select("li[class=clearfix]")
      .select("span")
    var mean = ""
    var mtype = ""
    for(i <- 0 until elems.size())    // 解析单词的中文含义
    {
      val e = elems.get(i)
      if(e.hasClass("prop"))
      {
        if(mtype != ""){
          result.type_mean += ((mtype,mean))
        }
        mean = ""
        mtype = e.text()
      }
      else{
        mean += e.text()
      }
    }
    result.type_mean += ((mtype,mean))
    try {
      val prouns = doc.select("div[class=base-speak]")
      result.pron += prouns.text()
      for (i <- 0 until 2) {
        // 音标
        val s = prouns.select("i[class=new-speak-step]").get(i).attr("ms-on-mouseover")
        val regex = """sound\('(.*)'\)""".r
        val regex(tempSound) = s
        result.sound += tempSound
      }
    }catch {
      case _:IndexOutOfBoundsException => return formatSentence(query)
    }
    result
  }

  /**
    * 查询英语句子 比如 in what case
    * @param queryContent 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentence(queryContent: QueryContent):ResultSentence ={
//    println("format sentence")
    val sentence = ResultSentence(queryContent.content)
    val doc = Jsoup.parse(queryContent.result)
    val elems = doc.select("div[class=clearfix]")
                      .select("div[style]")
//    println("elem = "+elems.text)
    if (elems.text.trim == "") return formatSentence1(queryContent)
    try {
      sentence.meaning = elems.text
    }catch {
      case e:Exception=>
        println(e.getStackTrace)
        return formatSentence1(queryContent)
    }
    sentence
  }

  /**
    * 匹配另一种情况下的句子,这种是对应 I love you的句子的情况
    * @param queryContent 查询的句子,应该是QESentence类型
    * @return
    */
  def formatSentence1(queryContent: QueryContent): ResultSentence = {
    val sentence = ResultSentence(queryContent.content)
    val doc = Jsoup.parse(queryContent.result)
    val elems = doc.select("li[class=clearfix]")
      .select("span")
    sentence.meaning = elems.text
    sentence
  }

  /**
    * 查询短语的释义
    * @param queryContent sentence
    * @return
    */
  def formatPhrase(queryContent: QueryContent):ResultSentence ={
//    println("format phrase")
    try {
      val wordResult = formatWord(queryContent).asInstanceOf[ResultWord]
      val sentent = ResultSentence(wordResult.query)
      sentent.meaning = wordResult.type_mean(0)._1 + wordResult.type_mean(0)._2
      sentent
    }catch{
      case e:ClassCastException=>
        println(e)
        formatSentence(queryContent)
    }

  }

}
