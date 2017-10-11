package utils

import java.net.URLEncoder

import entity.QueryContent

import scala.io.Source

/**
  * 查询工具,连接到金山词霸的查询网站去查询信息
  * Created by LinSixin on 2016/12/26.
  */
object WebQueryUtils {

  private val WEBSITE = "http://www.iciba.com/"


  def queryFromWebsite(queryContent:QueryContent): QueryContent = {
    val q = queryContent.content
    val tempResult = Source.fromURL(WEBSITE+URLEncoder.encode(q,"utf-8"),"utf-8").mkString
    queryContent.result = tempResult
    queryContent
  }


}
