package utils

import java.io.{BufferedInputStream, File, FileInputStream, IOException}
import java.net.URL
import java.util.concurrent.TimeUnit
import javazoom.jl.player.Player

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by linsixin on 2017/11/29.
  */
object MusicUtils {

  def playMusicByFile(filename:String): Unit ={
    var in: BufferedInputStream = null
    try{
      in = new BufferedInputStream(new FileInputStream(new File(filename)))
      val player = new Player(in)
      player.play()
    }catch {
      case e: IOException =>
        e.printStackTrace()
    }finally {
      in.close()
    }
  }

  def playMusicByUrl(urlStr:String): Unit ={
    val playFuture = Future{
      val url = new URL(urlStr)
      val in = url.openStream()
      val player = new Player(in)
      player.play()
    }
    try {
      Await.ready(playFuture,Duration.create(2,TimeUnit.SECONDS))
    }catch {
      case _: Throwable => println("network jam")
    }

  }

}
