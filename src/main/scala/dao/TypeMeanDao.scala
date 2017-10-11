package dao

import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
/**
  * Created by linsixin on 2017/10/10.
  */
object TypeMeanDao {

  private val logger = LoggerFactory.getLogger(getClass)

//  def doInSessionAndCommit(f : (TypeMeanMapper) => Unit): Unit ={
//    val session = GetSqlSession.getSqlSession
//    val typeMeanMapper = session.getMapper(classOf[TypeMeanMapper])
//    try{
//      f(typeMeanMapper)
//      session.commit()
//    }catch {
//      case e:Exception => logger.error(e.getMessage)
//    }finally session.close()
//  }
//
//  def doInSession(f : (TypeMeanMapper) => Unit): Unit ={
//    val session = GetSqlSession.getSqlSession
//    val typeMeanMapper = session.getMapper(classOf[TypeMeanMapper])
//    try{
//      f(typeMeanMapper)
//    }catch {
//      case e:Exception => logger.error(e.getMessage)
//    }finally session.close()
//  }

  def saveTypeMeans(wordId:Long,typeMeans:List[TypeMean]):Unit = {
    DAOUtils.doInSessionAndCommit[TypeMeanMapper] { typeMeanMapper =>
      val newTypeMean = new TypeMean
      newTypeMean.setWordId(wordId)
      typeMeans foreach{
        typeMean =>
          logger.info(s"save type meaning : ${typeMean.getMean} ")
          newTypeMean.setType(typeMean.getType)
          newTypeMean.setMean(typeMean.getMean)
          typeMeanMapper.insert(newTypeMean)
      }
    }

  }

  def queryTypeMeansByWordId(wordId:Long): List[TypeMean] = {
    DAOUtils.doInSession[TypeMeanMapper]{ mapper =>
      logger.info(s"query word type mean of id=$wordId")
      val example = new TypeMeanExample
      example.createCriteria().andWordIdEqualTo(wordId)
      return mapper.selectByExample(example).asScala.toList
    }
    List.empty[TypeMean]
  }


}
