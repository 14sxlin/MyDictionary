package db

import java.sql.DriverManager

import org.scalatest.FlatSpec

/**
  * Created by linsixin on 2017/10/9.
  */
class TestDBConnection extends FlatSpec{

  "DB connection " should "work well" in {
    Class.forName("org.h2.Driver")
    val conn = DriverManager.
      getConnection("jdbc:h2:~/test", "sa", "1234")
    val result =
      conn.createStatement().executeQuery("select * from WORD")
    val meta = result.getMetaData
    1 to meta.getColumnCount foreach { i=>
      print(meta.getColumnName(i).trim + "\t")
    }
    println()
    while(result.next()){
      1 to meta.getColumnCount foreach { i =>
        print(result.getString(i) + "\t")
      }
    }

    result.close()
    conn.close()
  }
}
