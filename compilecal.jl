using Pkg
Pkg.activate(".")
Pkg.instantiate()
using CourseCalendar

calfile = joinpath(pwd(), "F22", "calendar1.toml")
topics = joinpath(pwd(), "F22", "topics1.txt")
sched = courseSchedule(calfile, topics)

md = mdcalendar(sched)
hdg = """---
title: "Schedule"
layout: page
nav_order: 1
has_children: true
---

# Class schedule

Deadlines to note:

- ✏️ Class preparation includes written preparation to be posted to your github repository before class
- 📜  Portfolio assignment due
- ⚠️ Last day to revise submissions for a course unit


"""


open(joinpath(pwd(), "docs", "schedule", "index.md"), "w") do io
    write(io, hdg * md)
end