package gui

import javafx.scene.control.Menu

import config.{Configuration, DefaultConfiguration}

import scalafx.scene.control.CheckMenuItem
import ConfigName._
/**
  * Created by linsixin on 2017/10/11.
  */
class WebConfigMenu extends Menu("网络"){

  val config: Configuration = DefaultConfiguration.getInstance()

  val saveLocalCheckItem = new CheckMenuItem("保存到本地")
  val onlineModeCheckItem = new CheckMenuItem("只联网查询")

  saveLocalCheckItem.selected = config.get(saveToLocal).toBoolean
  onlineModeCheckItem.selected = config.get(onlineOnly).toBoolean
  getItems.add(saveLocalCheckItem)
  getItems.add(onlineModeCheckItem)

  saveLocalCheckItem.onAction = { _ =>
    config.put(saveToLocal,saveLocalCheckItem.selected.value.toString)
  }

  onlineModeCheckItem.onAction = { _ =>
    config.put(onlineOnly,onlineModeCheckItem.selected.value.toString)

  }

}
