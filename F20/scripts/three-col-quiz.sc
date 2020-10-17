import scala.io.Source

case class QuizTriple(
  topic: String,
  question: String,
  answer: String
) {
  def mdItem(indent: Boolean = true) = {
    if (indent) {
      "    1. " + question
    } else {
      "1. " + question
    }

  }
}
def mdFor3cols(fName: String) : String = {
  val lines = Source.fromFile(fName).getLines.toVector
  val triples = for (l <- lines.tail) yield {
    val cols = l.split(",")
    if (cols.size == 3) {
      Some(QuizTriple(cols(0), cols(1), cols(2)))
    } else {
      None
    }
  }
  val grouped = triples.flatten.groupBy(_.topic)

  val md = for (groupKey <- grouped.keySet) yield {
    val group = grouped(groupKey)
    val questionList = group.map(triple => triple.mdItem()).mkString("\n")
    "1. *" + groupKey + "*\n" + questionList
  }
  md.mkString("\n")
}
