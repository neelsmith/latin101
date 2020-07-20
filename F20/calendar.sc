import edu.holycross.shot.coursecal._
import java.io.PrintWriter



def schedule = {
  val pg = "ghpages/schedule/index.md"
  val sched = Schedule("topics.txt", "calendar.yaml")
  val md = sched.markdownCalendar
  new PrintWriter(pg) { write(md); close }
  println("Schedule written to " + pg)
}


println("\nWrite a new schedule to ghpages directory:")
println("\tschedule")
