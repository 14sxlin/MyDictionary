package jsoup

import org.jsoup.Jsoup
import org.scalatest.FunSuite
/**
  * Created by sparr on 2017/3/11.
  */
class TestJsoup extends FunSuite{

    val html = "<a>hello</a><body>something in body</body>"

    test("how jsoup work"){
      val document = Jsoup.parse(html)
      val element = document.getElementsByTag("a")
      print(element.text())
//      assert(1==1)
    }
}
