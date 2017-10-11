package gui.action

import scalafx.scene.control.{ContextMenu, TextArea, TextField}
import scalafx.scene.input.{MouseButton, MouseEvent}

/**
  * Created by linsixin on 2017/10/9.
  */
case class InputTextFieldAction(textField:TextField) {

  val onClickDo: (MouseEvent) => Unit = { e : MouseEvent =>
//    if(e.clickCount == 1 && e.button == MouseButton.Primary){
//      textField.selectAll()
//    }
//    if(e.clickCount == 1 && e.button == MouseButton.Secondary){
//    }
  }


}
