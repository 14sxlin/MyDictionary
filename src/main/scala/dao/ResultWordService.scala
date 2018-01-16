package dao

import dao.WordService.queryOneWord
import dao.WordTransformation.{entityWord2ResultWord, resultWord2EntityWord}
import entity.{Result, ResultWord}
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
  * Created by linsixin on 2017/10/10.
  */
object ResultWordService {

  private val logger = LoggerFactory.getLogger(getClass)

  def queryOneResult(english:String): Option[ResultWord] = {
    if(english == null)
      throw new IllegalArgumentException("query should not be null")

    val result = {
      queryOneWord(english) match {
        case None => null
        case Some(word) =>
          val typeMeans = TypeMeanService.queryTypeMeansByWordId(word.getId)
          entityWord2ResultWord(word,typeMeans)
      }
    }

    Option(result)
  }


  def saveResult(result: Result): Unit ={
    result match {
      case resultWord:ResultWord =>
        logger.info(s"save ${resultWord.query}")
        saveWordAndTypeMean(resultWord)

      case _ =>
        throw new IllegalArgumentException("don't save sentence yet")
    }
  }

  private def saveWordAndTypeMean(resultWord:ResultWord): Unit = {
    DAOUtils.doInSessionAndCommit { wordMapper: WordMapper =>
      val word = resultWord2EntityWord(resultWord)
      wordMapper.insert(word)

      logger.info(s"saving type means id = ${word.getId}")
      assert(word.getId != null && word.getId != 0)

      TypeMeanService.saveTypeMeans(word.getId,resultWordGetTypeMeans(resultWord))
    }
  }

  private def resultWordGetTypeMeans(resultWord:entity.ResultWord):List[TypeMean] = {
    val list = ArrayBuffer[TypeMean]()
    resultWord.type_mean.foreach{ rawTypeMean =>
      val typeMean = new TypeMean
      typeMean.setType(rawTypeMean._1)
      typeMean.setMean(rawTypeMean._2)
      list += typeMean
    }
    list.toList
  }
}
