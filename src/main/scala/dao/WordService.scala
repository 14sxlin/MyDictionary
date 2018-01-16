package dao

import scala.collection.mutable.ArrayBuffer
/**
  * Created by linsixin on 2017/10/10.
  */
object WordService {

  def queryOneWord(eng:String): Option[Word] = {
    var option :Option[Word] = None
    DAOUtils.doInSession[WordMapper]{ wordMapper =>
      val wordExample = new WordExample
      wordExample.createCriteria().andEngEqualTo(eng)
      val entityWords = wordMapper.selectByExample(wordExample)
      if(entityWords == null || entityWords.isEmpty)
        option =  None
      else option = Some(entityWords.get(0))
    }
    option
  }

}
