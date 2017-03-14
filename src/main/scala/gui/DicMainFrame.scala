package gui

import java.awt.{ Dimension, Point, Toolkit}
import javax.swing.BorderFactory

import control.{ContentWrapper, Dictionary}
import entity.{ Sentence, Word}

import scala.swing.BorderPanel.Position
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{BorderPanel, Button, MainFrame, MenuItem, PopupMenu, ScrollPane, SimpleSwingApplication, TextArea, TextField}

/**
  * Created by sparr on 2017/1/18.
  */
object DicMainFrame extends SimpleSwingApplication {
  override def top = new MainFrame{
    title = "词典"
    preferredSize =  new Dimension(300,400)
    val dim = Toolkit.getDefaultToolkit.getScreenSize
    location = new Point((dim.width-preferredSize.width)/2,(dim.height-preferredSize.height)/2)

    val pasteMenuItem = new MenuItem("粘贴")
    val popMenu = new PopupMenu{
      contents += pasteMenuItem
    }

    val inputTextField =  new TextField{
      listenTo(popMenu)
      listenTo(mouse.clicks)
      listenTo(pasteMenuItem)
      reactions+={
        case e:MouseClicked =>
          if(e.modifiers==0){//鼠标左键
            this.selectAll()
          }else if(e.modifiers==256){//鼠标右键
            popMenu.show(this,e.point.x,e.point.y)
          }
        case e:ButtonClicked=>
          if(e.source==pasteMenuItem) {
            this.text = ""
            this.paste()
          }
      }
    }
    val qBtn  = new Button("查询")
    val resultArea = new TextArea()
    resultArea.wordWrap = true

    contents = new BorderPanel {
      val inputPanel = new BorderPanel{
        border = BorderFactory.createTitledBorder("查询")
        layout(inputTextField) = Position.Center
        layout(qBtn) = Position.East
        defaultButton = qBtn
      }
      layout(inputPanel) = Position.North


      val scroll = new ScrollPane(){
        border = BorderFactory.createTitledBorder("结果")
        contents = resultArea
      }

      layout(scroll) = Position.Center
    }


    listenTo(qBtn)
    reactions+={
      case ButtonClicked(`qBtn`)=>
        val text = inputTextField.text
        if(text!="")
        {
          val queryContent = ContentWrapper.wrap(text)
          val result = Dictionary.translate(queryContent)
          result match {
            case word:Word =>
              if(word.type_mean.nonEmpty){
                resultArea.text += word.from+"\n"
                resultArea.text += word.pron+"\n"
                for((wtype,wmean) <- word.type_mean){
                  resultArea.text += wtype+" "+wmean+"\n"
                }
              }
            case sentence:Sentence=>
              resultArea.text += sentence.mfrom + "\n"
              resultArea.text += sentence.meaning+"\n"

          }
          resultArea.text += "---------------------------\n"
        }
    }

  }

}
