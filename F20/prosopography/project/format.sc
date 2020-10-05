// Ammonite script
//val personalRepo = coursierapi.MavenRepository.of("https://dl.bintray.com/neelsmith/maven")
//interp.repositories() ++= Seq(personalRepo)

//import $ivy.`edu.holycross.shot::latincorpus:7.0.0-pr5`

import edu.holycross.shot.latincorpus._

import scala.io.Source
import java.io.PrintWriter
import java.io.File

val hyginusUrl = "https://raw.githubusercontent.com/LinguaLatina/analysis/master/data/hyginus/hyginus-latc.cex"
val hyginus = LatinCorpus.fromUrl(hyginusUrl)

def passageLinks(lexeme: String) : String = {
  val baseUrl = "https://lingualatina.github.io/texts/browsable/hyginus/"
  val psgs = hyginus.citableUnits.sequences.filter( cn => cn.matchesLexeme(lexeme))
  val hdr = s"## In Hyginus:  ${psgs.size} passages"
  val items = psgs.map { psg =>
    val psgRef = psg.tokens.head.urn.collapsePassageTo(1).passageComponent
    s"1. [${psgRef}](${baseUrl}${psgRef}/)"
  }
  hdr + "\n\n" + items.mkString("\n")
}




val f = "names.cex"
val data = Source.fromFile(f).getLines.toVector
val cols = data.tail.map(_.split("#")).toVector

case class TabEntry(nominative: String,
  genitive: String,
  gender: String,
  ls: String
)

case class Article(author: String,
  nominative: String,
  genitive: String,
  gender: String,
  ls: String,
  summary: String) {

  def header : String = {
    val lines = Vector("---",
    s"title: ${nominative}",
    "layout: page",
    s"parent: ${nominative.head}",
    "grand_parent: Characters in Hyginus",
    "---")
    lines.mkString("\n")
  }
  def attribution : String = {
    "Contribution by *" +  author + "*"
  }
  def title: String = "# " + nominative
  def articleSummary: String = "## Summary\n\n| " + summary

  def lewisShort: String = {
    s"## Dictionary entry\n\n*${nominative}, ${genitive}* (${gender.toLowerCase}). See [entry in Lewis-Short](http://folio2.furman.edu/lewis-short/index.html?urn=${ls})."
  }

  def passageList: String = {
    passageLinks(ls.replaceFirst("urn:cite2:hmt:ls.markdown:","ls."))
  }

  def webPage: String = {
    List(header, title, attribution, articleSummary, lewisShort, passageList).mkString("\n\n\n")
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
    val alphadir = new File(a.nominative.head.toString)
    if (! alphadir.exists) { alphadir.mkdir}
    val dir = new File(alphadir, a.nominative)
    if (! dir.exists) { dir.mkdir}
    val f = dir + "/index.md"
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
