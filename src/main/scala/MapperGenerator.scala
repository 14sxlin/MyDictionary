package entity

import java.util

import org.apache.ibatis.io.Resources
import org.mybatis.generator.api.MyBatisGenerator
import org.mybatis.generator.config.xml.ConfigurationParser
import org.mybatis.generator.internal.DefaultShellCallback

/**
  * Created by linsixin on 2017/10/10.
  */
object MapperGenerator extends App{


  val warnings = new util.ArrayList[String]()
  val configFile = Resources.getResourceAsFile("generatorConfig.xml")
  val configParser = new ConfigurationParser(warnings)

  val config = configParser.parseConfiguration(configFile)

  val overwrite = false
  val callBack = new DefaultShellCallback(overwrite)
  val generator = new MyBatisGenerator(config,callBack,warnings)
  generator.generate(null)
  warnings.forEach(warning => println(warning))

}
