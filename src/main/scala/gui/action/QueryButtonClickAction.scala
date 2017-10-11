package gui.action

import javafx.scene.control.Button

import config.DefaultConfiguration
import control.Translator
import dao.ResultWordDao
import entity.{QueryContentFactory, Result}
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
      resultArea.text = "正在查询..."
      button.setDisable(true)
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


  private def saveResultToLocal(result: Result):Unit = {
    if(_saveToLocal){

      Future{
        ResultWordDao.saveResult(result)
      } onComplete {
        case Success(_) =>
          appendMessage("已保存到本地")
        case Failure(e) =>
          appendMessage(s"未保存到本地 : ${e.getMessage}")
      }
    }else{
      appendMessage("未保存到本地\n 左上角 \"网络 -> 保存到本地\" 开启保存配置 ")
    }

  }

  private def showResultAndEnableButton(result:String):Unit = {
    input.clear()
    resultArea.text = result + "-"*50 + "\n"
    button.setDisable(false)
  }

  private def appendMessage(msg:String): Unit ={
    resultArea.text = resultArea.text.value + msg
  }

}
