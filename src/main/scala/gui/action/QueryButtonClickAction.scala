package gui.action

import javafx.scene.control.Button

import config.DefaultConfiguration
import control.Translator
import dao.ResultWordDao
import entity.{QueryContentFactory, Result, ResultWord}
import gui.ConfigName._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scalafx.scene.control.{TextArea, TextField}
/**
  * Created by linsixin on 2017/10/9.
  */
case class QueryButtonClickAction(button: Button,
                                  input:TextField,
                                  resultArea:TextArea) {

  private val config = DefaultConfiguration.getInstance()

  private def _onlineOnly = config.getOrDefault(onlineOnly,"false").toBoolean
  private def _saveToLocal = config.getOrDefault(saveToLocal,"true").toBoolean


  val queryAction: () => Unit = () => {
    val text = input.text.value.trim
    if( ! text.isEmpty){
      doQueryAndShowResult(text)
    }
  }

  private def doQueryAndShowResult(text:String):Unit = {
    Future{

      Translator.translate(
        QueryContentFactory.create(text),
        onlineOnly = _onlineOnly
      )

    } onComplete{
      case Success((fromLocal,result)) =>
        showResultAndEnableButton(result.toString)
        setPronUrls(result)
        if(!fromLocal) {
          appendMessage("查询自web \n")
          if(! _onlineOnly)
            saveResultToLocal(result)
        }else{
          appendMessage("查询自本地 \n")
        }
      case Failure(e) =>
        showResultAndEnableButton(s"some error occur ! ${e.getMessage}")

    }
  }

  private def setPronUrls(result: Result): Unit ={
    result match {
      case word: ResultWord =>
        QueryButtonClickAction.engPron = Option(word.sound(0))
        QueryButtonClickAction.usaPron = Option(word.sound(1))
        println(s"set pron ${QueryButtonClickAction.engPron}" +
          s"${QueryButtonClickAction.usaPron}")

      case _ =>
        QueryButtonClickAction.engPron = None
        QueryButtonClickAction.usaPron = None
    }

  }


  private def saveResultToLocal(result: Result):Unit = {
    if(_saveToLocal){
      Future{
        ResultWordDao.saveResult(result)
      } onComplete {
        case Success(_) =>
          appendMessage("已保存到本地\n\n")
        case Failure(e) =>
          appendMessage(s"未保存到本地 : ${e.getMessage}\n\n")
      }
    }else{
      appendMessage("未保存到本地\n 左上角 \"网络 -> 保存到本地\" 开启保存配置 ")
    }

  }
  /*
    不知道为什么,无法scroll resultArea到底部,只能用显示在头部替代
   */
  private def showResultAndEnableButton(result:String):Unit = {
    input.clear()
    val sb = new StringBuilder()
    sb.append(result).append("-"*50).append("\n##\n").append(resultArea.text.value)
    resultArea.text = sb.toString()
  }

  private def appendMessage(msg:String): Unit ={
    resultArea.text = resultArea.text.value.replaceFirst("##",msg + "\n##\n")
  }

}

object QueryButtonClickAction{
  var engPron:Option[String] = None
  var usaPron:Option[String] = None
}
