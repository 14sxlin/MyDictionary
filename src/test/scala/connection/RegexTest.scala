package connection
import org.scalatest.FunSuite

/**
  * Created by LinSixin on 2016/12/27.
  */
class RegexTest extends FunSuite{
  test("how regex match a b c"){
    val mp = """<div style="width: 580px; margin-top: 15px; font-size: 18px; line-height: 24px; color: #333333;">[\s\S]+""".r
    assert((mp
        findFirstIn
      """<div style="width: 580px; margin-top: 15px; font-size: 18px; line-height: 24px; color: #333333;">You have to be careful with me</div>"""
      )isDefined)

    val test = """<a>You have to be careful with me</a>"""
    val regex = """<a>([\s\S]+)</a>""".r
    assert((regex findFirstIn test) isDefined)
    val regex(r) = test
    println(r)
  }
}
