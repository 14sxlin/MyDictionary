package utils

import org.scalatest.FunSuite

/**
  * Created by linsixin on 2017/10/10.
  */
class TestWordUtils extends FunSuite{

  test("regex"){
    val str = "英 [ˈrekɔ:d] 美 [ˈrekərd]"
    val format = "英 [(.+)] 美 [(.+)]".r
    assert(format.regex.matches(str))
  }
  test("splitPron"){
    val str = "英 [ˈrekɔ:d] 美 [ˈrekərd]"
    val (a,b) = WordUtils.splitPron(str)
    assert(a == "[ˈrekɔ:d]" && b == "[ˈrekərd]")
  }
}
