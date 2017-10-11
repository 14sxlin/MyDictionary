package entity

import utils.WordUtils

/**
  * Created by linsixin on 2017/1/18.
  */
object QueryContentFactory {

  import WordUtils._
  def create(content:String, isEng:Boolean, isWord:Boolean):QueryContent ={
    if(isEng){
      if(isWord)
        QueryEnglishWord(content)
      else
        QueryEnglishSentence(content)
    }else{
      if(isWord)
        QueryChineseWord(content)
      else
        QueryChineseSentence(content)
    }
  }

  def create(content:String):QueryContent = {
    create(content,isEng(content),isWord(content))
  }

}
