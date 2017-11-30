package gui.action

import utils.MusicUtils

/**
  * Created by linsixin on 2017/11/29.
  */
object PronAction {

  def usaPron(): () => Unit = () =>
    QueryButtonClickAction.usaPron match {
      case Some(url) => MusicUtils.playMusicByUrl(url)
      case None => println("no pron")
    }

  def engPron(): () => Unit = () =>
    QueryButtonClickAction.engPron match {
      case Some(url) => MusicUtils.playMusicByUrl(url)
      case None => println("no pron")
    }

}
