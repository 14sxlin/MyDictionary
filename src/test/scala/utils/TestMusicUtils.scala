package utils

import org.scalatest.{FunSuite, Ignore}

/**
  * Created by linsixin on 2017/11/29.
  */
class TestMusicUtils extends FunSuite {

  test("test play music by file"){
    MusicUtils.playMusicByFile("E:\\Media\\Music\\3ASiC - 套路.mp3")
  }

  test("test play music by url"){
    MusicUtils.playMusicByUrl("http://res.iciba.com/resource/amp3/oxford/0/44/29/44297283b5e4b5b0a991213897f0b14a.mp3")
  }

}
