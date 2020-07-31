import edu.holycross.shot.coursecal._
import java.io.PrintWriter



def rewriteHeader(md: String): String = {
  val newHeader = "---\nlayout: page\ntitle: Course schedule\nnhas_children: true\nav_order: 1\n---\n\n# Course schedule\n\n## Unit 1: Latin, an inflected language\n\n"

  val lines = md.split("\n")
  newHeader + lines.slice(4, md.size - 1).mkString("\n")
}

def schedule = {
  val pg = "ghpages/schedule/index.md"
  val sched = Schedule("topics.txt", "calendar.yaml")
  val md = sched.markdownCalendar
  val tweaked = rewriteHeader(md)
  new PrintWriter(pg) { write(tweaked); close }
  println("Schedule written to " + pg)
}


println("\nWrite a new schedule to ghpages directory:")
println("\tschedule")
