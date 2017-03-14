package control

import entity.{QueryContent, Result}
import connection._

/**
  * Created by sparr on 2017/1/18.
  */
object Dictionary {

  def translate(content:QueryContent):Result ={
    val queryContent = ConnectionTool.queryFromWebsite(content)
    ResultFormatter.format(queryContent)
  }
}
