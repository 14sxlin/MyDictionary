package dao

import entity.ResultWord
import org.apache.ibatis.session.SqlSession
import utils.WordUtils.{isEng, isWord, splitPron}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linsixin on 2017/10/10.
  */
object WordTransformation {

  def entityWord2ResultWord(word: Word, typeMeans:List[TypeMean]):ResultWord = {
    val resultWord = ResultWord(word.getEng)
    resultWord.pron = s"[英] ${word.getEngPron} [美] ${word.getUsaPron}"
    resultWord.sound = ArrayBuffer(word.getEngMedia,word.getUsaMedia)
    for(typeMean <- typeMeans){
      resultWord.type_mean += typeMean.getType -> typeMean.getMean
    }
    resultWord
  }

  def resultWord2EntityWord(resultWord:ResultWord):Word = {
    if(isEng(resultWord.query) && isWord(resultWord.query)){
      val word = new Word
      val (engPron,usaPron) = splitPron(resultWord.pron)
      word.setEng(resultWord.query)
      word.setEngPron(engPron)
      word.setUsaPron(usaPron)
      if(resultWord.sound.nonEmpty)
        word.setEngMedia(resultWord.sound(0))
      if(resultWord.sound.length == 2)
        word.setUsaMedia(resultWord.sound(1))
      word
    }else throw new IllegalArgumentException(s"${resultWord.query} not a English word")
  }
}
