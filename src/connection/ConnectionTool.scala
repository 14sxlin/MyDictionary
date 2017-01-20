package connection

import java.net.URLEncoder

import entity.QueryContent

import scala.io.Source

/**
  * ��ѯ����,���ӵ���ɽ�ʰԵĲ�ѯ��վȥ��ѯ��Ϣ
  * Created by LinSixin on 2016/12/26.
  */
object ConnectionTool {

  private val WEBSITE = "http://www.iciba.com/"


  def queryFromWebsite(queryContent:QueryContent) = {
    val q = queryContent.content
    val tempResult = Source.fromURL(WEBSITE+URLEncoder.encode(q,"utf-8"),"utf-8").mkString
    queryContent.result = tempResult
    queryContent
  }

  def queryFromFile(queryContent:QueryContent,file:String)={
    val content = Source.fromFile(file, "utf-8").mkString
    queryContent.result = content
    queryContent
  }

  def queryFromNote(queryContent:QueryContent):QueryContent = {
    ???
  }



}
