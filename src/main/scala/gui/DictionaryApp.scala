package gui

import config.DefaultConfiguration
import gui.action.{PronAction, QueryButtonClickAction}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{TextArea, _}
import scalafx.scene.image.Image
import scalafx.scene.layout._
import scalafx.scene.paint.Color
/**
  * Created by linsixin on 2017/10/8.
  */
object DictionaryApp extends JFXApp{

  val queryBtn = new Button("查询"){
    tooltip = new Tooltip("直接回车也可以")
  }

  val input = new TextField()

  val result = new TextArea{
    editable = false
  }

  val menuBar: MenuBar = new MenuBar {
    menus = Seq(
      new WebConfigMenu()
    )
  }

  val engPropBtn = new Button("英音")
  val usaPropBtn = new Button("美音")


  stage = new PrimaryStage {
    icons += new Image("dic-icon.png")
    scene = new Scene {
      title = "Dictionary"
      resizable = true
      content = new VBox{
        fillWidth = true
        val pane = new BorderPane {
          top = new HBox {
            background = new Background(
              Array(
                new BackgroundFill(Color.Orange, new CornerRadii(0), Insets.Empty)
              )
            )
            padding = Insets(5)
            spacing = 10
            alignment = Pos.Center
            queryBtn.defaultButton = true
            children = Seq(
              new Label("输入要查询的词/句:"),
              input,
              queryBtn,
              engPropBtn,
              usaPropBtn
            )
          }
          center = result
        }
        children += menuBar
        children += pane
      }
    }
    onCloseRequest = { _ =>
      DefaultConfiguration.save()
    }
  }

  queryBtn.onAction = QueryButtonClickAction(queryBtn,input,result).queryAction
  engPropBtn.onAction = PronAction.engPron()
  usaPropBtn.onAction = PronAction.usaPron()

}
