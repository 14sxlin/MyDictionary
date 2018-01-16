package control

import dao.ResultWordService
import entity.{QueryContent, QueryEnglishWord, Result, ResultFormatter}
import utils.WebQueryUtils

/**
  * Created by linsixin on 2017/1/18.
  */
object Translator {

  /**
    *
    * @param content query content
    * @param onlineOnly don't query in local database
    * @return isFromLocal
    */
  def translate(content: QueryContent, onlineOnly:Boolean = false): (Boolean,Result) ={
    if( !onlineOnly && content.isInstanceOf[QueryEnglishWord]) {
      translateFromLocal(content.asInstanceOf[QueryEnglishWord]) match {
        case Some(result) => (true,result)
        case None =>
          (false,translateFromWeb(content))
      }
    }else (false,translateFromWeb(content))
  }


  def translateFromWeb(content:QueryContent):Result ={
    val queryContent = WebQueryUtils.queryFromWebsite(content)
    ResultFormatter.format(queryContent)
  }

  def translateFromLocal(query:QueryEnglishWord) : Option[Result] = {
    ResultWordService.queryOneResult(query.word)
  }

}
