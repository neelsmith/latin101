
## Format 3-column question in markdown

Use the script `three-col-quiz.sc`

## Publish course web site

Within `ghpages` directory:

    jekyll build
    rsync -avz _site/ nsmith@shot.holycross.edu:html/courses/ada/S20


Slides with fusuma:

Individual slideshows are subdirectories within `fusuma`

Within each slideshow subdir,  one-time install:


    npm i fusuma -D

Then, anytime,

    npx fusuma start


Build pdf of syllabus data:

    pandoc -s -o syllabus/syllabus.pdf syllabus/_syllabus.md syllabus/[0-9]*md
