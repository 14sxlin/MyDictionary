package utils

/**
  * Created by linsixin on 2017/10/10.
  */
object WordUtils {

  def isEng(text:String): Boolean = {
    val first = text.charAt(0)
    val last = text.last
    first<='z'&&last<='z'
  }

  def isWord(text:String): Boolean = {
    text.split(" ").length == 1
  }

  def splitPron(pron:String) :(String,String) = {
    val usaIndex = pron.indexOf("美")
    val engIndex = pron.indexOf("英")
    (pron.substring(engIndex+1,usaIndex).trim,
    pron.substring(usaIndex + 1).trim)
  }
}
