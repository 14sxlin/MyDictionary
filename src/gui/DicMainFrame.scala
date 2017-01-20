package gui

import java.awt.event.MouseEvent
import java.awt.{Color, Dimension, Point, Toolkit}
import javax.swing.{BorderFactory, JTextField}

import control.{ContentWrapper, Dictionary}
import entity.{QCSentence, Result, Sentence, Word}

import scala.swing.BorderPanel.Position
import scala.swing.event.{ButtonClicked, Key, MouseClicked}
import scala.swing.{BorderPanel, Button, ButtonGroup, FlowPanel, Frame, GridBagPanel, GridPanel, MainFrame, Menu, MenuItem, PopupMenu, RadioButton, RadioMenuItem, ScrollPane, SimpleSwingApplication, TextArea, TextField}

/**
  * Created by sparr on 2017/1/18.
  */
object DicMainFrame extends SimpleSwingApplication {
  override def top = new MainFrame{
    title = "´Êµä"
    preferredSize =  new Dimension(300,400)
    val dim = Toolkit.getDefaultToolkit.getScreenSize
    location = new Point((dim.width-preferredSize.width)/2,(dim.height-preferredSize.height)/2)

    val pasteMenuItem = new MenuItem("Õ³Ìù")
    val popMenu = new PopupMenu{
      contents += pasteMenuItem
    }

    val inputTextField =  new TextField{
      listenTo(popMenu)
      listenTo(mouse.clicks)
      listenTo(pasteMenuItem)
      reactions+={
        case e:MouseClicked =>
          if(e.modifiers==0){//Êó±ê×ó¼ü
            this.selectAll()
          }else if(e.modifiers==256){//Êó±êÓÒ¼ü
            popMenu.show(this,e.point.x,e.point.y)
          }
        case e:ButtonClicked=>
          if(e.source==pasteMenuItem) {
            this.text = ""
            this.paste()
          }
      }
    }
    val qBtn  = new Button("²éÑ¯")
    val resultArea = new TextArea()
    resultArea.wordWrap = true

    contents = new BorderPanel {
      val inputPanel = new BorderPanel{
        border = BorderFactory.createTitledBorder("²éÑ¯")
        layout(inputTextField) = Position.Center
        layout(qBtn) = Position.East
        defaultButton = qBtn
      }
      layout(inputPanel) = Position.North


      val scroll = new ScrollPane(){
        border = BorderFactory.createTitledBorder("½á¹û")
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
          var queryContent = ContentWrapper.wrap(text,isEng(text) ,true)
          val result = Dictionary.translate(queryContent)
          val word = result.asInstanceOf[Word]
          if(word.type_mean.nonEmpty){
            resultArea.text += word.from+"\n"
            for((wtype,wmean) <- word.type_mean){
              resultArea.text += wtype+" "+wmean+"\n"
            }

          }else {
            queryContent = ContentWrapper.wrap(text, isEng(text), false)
            val result: Sentence = Dictionary.translate(queryContent).asInstanceOf[Sentence]
            resultArea.text += result.mfrom + "\n"
            resultArea.text += result.meaning+"\n"
          }
        }
    }

  }

  def isEng(text:String) = {
    val first = text.charAt(0)
    val last = text.last
    if(first<='z'&&last<='z')
      true
    else false
  }
}
