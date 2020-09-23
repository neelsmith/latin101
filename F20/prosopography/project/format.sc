import scala.io.Source
import java.io.PrintWriter

val f = "names.cex"
val data = Source.fromFile(f).getLines.toVector
val cols = data.tail.map(_.split("#")).toVector

case class TabEntry(nominative: String,
  genitive: String,
  gender: String,
  ls: String
)

case class Article( author: String,
  nominative: String,
  genitive: String,
  gender: String,
  ls: String,
  summary: String
) {

  def header : String = {
    val lines = Vector("---",
    s"title: ${nominative}",
    "layout: page",
    s"parent: ${nominative.head}",
    "---")
    lines.mkString("\n")
  }
  def attribution : String = {
    "Contribution by *" +  author + "*"
  }
  def title: String = "# " + nominative

  def articleSummary: String = "## Summary\n\n" + summary

  def lewisShort: String = {
    s"## Dictionary entry\n\n*${nominative}, ${genitive}* (${gender.toLowerCase}). See [entry in Lewis-Short](http://folio2.furman.edu/lewis-short/index.html?urn=${ls})."
  }

  def webPage: String = {
    List(header, title, attribution, articleSummary, lewisShort).mkString("\n\n\n")
  }
}


// Extract data for tabulae entries:
def tabulae = {
  cols.map(v =>
    TabEntry(
      v(3).toLowerCase,
      v(4).toLowerCase,
      v(5).toLowerCase, v(7).replaceFirst("urn:cite2:hmt:ls.markdown:","")
    )
  )
}

def writeTabulaeData(outFile : String = "for-tabulae.cex" ) = {
  val tab_cex = tabulae.map( tab =>
    "latcommon.noun" + tab.ls + "#ls." + tab.ls + "#" + tab.genitive + "#" + tab.gender + "#"
  )
  new PrintWriter(outFile){ write(tab_cex.mkString("\n"));close;}
}


def articles = {
  cols.map( v =>
    Article(
      v(2), v(3), v(4), v(5), v(7), v(8)
    )
  )
}
//auth = 2
// nom = 3
// gen = 4
// gend = 5
// in LS? 6
// LS  = 7
// summary = 8




def web = {
  for (a <- articles ) {
    val f = a.nominative + ".md"
    new PrintWriter(f){write(a.webPage); close;}
  }
}


def usage = {
  println("\n\nWrite CEX file to use with tabulae:\n")
  println("\twriteTabulaeData(<OUTFILE>)\n")
  println("Write web pages:\n")
  println ("\tweb")
}


usage
