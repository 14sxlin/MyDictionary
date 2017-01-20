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

}
