package control

import entity._

/**
  * Created by sparr on 2017/1/18.
  */
object ContentWrapper {

  def wrap(content:String,isEng:Boolean,isWord:Boolean):QueryContent ={
    if(isEng){
      if(isWord)
        QEWord(content)
      else
        QESentence(content)
    }else{
      if(isWord)
        QCWord(content)
      else
        QCSentence(content)
    }
  }

  def wrap(content:String):QueryContent = {
    wrap(content,isEng(content),isWord(content))
  }

  def isEng(text:String) = {
    val first = text.charAt(0)
    val last = text.last
    first<='z'&&last<='z'
  }

  def isWord(text:String) = {
    text.split(" ").length == 1
  }

}
