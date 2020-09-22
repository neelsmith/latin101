import scala.io.Source

val f = "names.cex"
val data = Source.fromFile(f).getLines.toVector
val cols = data.tail.map(_.split("#")).toVector


case class TabEntry(nominative: String,
  genitive: String,
  gender: String,
  ls: String
)


// data for tabulae:
val tabulae = cols.map(v =>
  TabEntry(
    v(3).toLowerCase,
    v(4).toLowerCase,
    v(5).toLowerCase, v(7).replaceFirst("urn:cite2:hmt:ls.markdown:","")
  )
)




val tab_cex = tabulae.map( tab =>
  "latcommon.noun" + tab.ls + "#ls." + tab.ls + "#" + tab.genitive + "#" + tab.gender + "#"
)
import java.io.PrintWriter
new PrintWriter("for-tabulae.cex"){ write(tab_cex.mkString("\n"));close;}
