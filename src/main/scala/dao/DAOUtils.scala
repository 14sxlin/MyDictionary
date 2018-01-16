package dao

import mybatis.GetSqlSession
import org.slf4j.LoggerFactory

import scala.reflect.ClassTag

/**
  * Created by linsixin on 2017/10/10.
  */
object DAOUtils {

  private val logger = LoggerFactory.getLogger(getClass)

  def doInSessionAndCommit[MapperClass:ClassTag](f : (MapperClass) => Unit): Unit ={
    val session = GetSqlSession.getSqlSession
    val mapper = session.getMapper(implicitly[ClassTag[MapperClass]].runtimeClass)
    try{
      f(mapper.asInstanceOf[MapperClass])
      session.commit()
    }catch {
      case e:Exception => logger.error("",e)
    }//finally session.close()
  }

  def doInSession[MapperClass:ClassTag](f : (MapperClass) => Unit): Unit ={
    val session = GetSqlSession.getSqlSession
    val mapper = session.getMapper(implicitly[ClassTag[MapperClass]].runtimeClass)
    try{
      f(mapper.asInstanceOf[MapperClass])
    }catch {
      case e:Exception => logger.error("",e)
    }//finally session.close()
  }

}
