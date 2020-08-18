import edu.holycross.shot.coursecal._
import java.io.PrintWriter


def header(module: Int) : String = {
  s"---\nlayout: page\ntitle: Daily schedule for unit ${module}\nparent: Course schedule\nnav_order: ${2*module}\n---\n\n"
}

/*
def rewriteHeader(md: String): String = {
  val newHeader = "---\nlayout: page\ntitle: Course schedule\nnhas_children: true\nav_order: 1\n---\n\n# Course schedule\n\n## Unit 1: Latin, an inflected language\n\n"

  val lines = md.split("\n")
  newHeader + lines.slice(4, md.size).mkString("\n")
}*/

def schedule(module: Int): Unit = {
  val pg = s"ghpages/schedule/part${module}/schedule/index.md"

  val sched = Schedule(s"topics${module}.txt", s"calendar${module}.yaml")
  //val md = sched.markdownCalendar
  //val tweaked = rewriteHeader(md)
  val lines = sched.markdownCalendar.split("\n").toVector
  val md = header(module) + lines.slice(4, lines.size).mkString("\n") + "\n\n"
  new PrintWriter(pg) { write(md); close }
  println("Schedule written to " + pg)
}

println("\nWrite schedule for unit:")
println("\n\tschedule(UNIT_NUMBER)")
println("\nwhere UNIT_NUMBER is 1,2 or 3")
