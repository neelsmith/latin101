---
layout: page
title: Topics for semester
parent: Graphs
---



<script src="//d3js.org/d3.v5.min.js"></script>
<script src="https://unpkg.com/@hpcc-js/wasm@0.3.11/dist/index.min.js"></script>
<script src="https://unpkg.com/d3-graphviz@3.0.5/build/d3-graphviz.js"></script>

# Latin 101: Fall '20


Another view of the course schedule:

<div id="graph" style="text-align: center;"></div>


<script>

var dotIndex = 0;
var graphviz = d3.select("#graph").graphviz()
    .transition(function () {
        return d3.transition("main")
            .ease(d3.easeLinear)
            .delay(500)
            .duration(1500);
    })
    .logEvents(true)
    .on("initEnd", render);

function render() {
    var dotLines = dots[dotIndex];
    var dot = dotLines.join('');
    graphviz
        .renderDot(dot)
        .on("end", function () {
            dotIndex = (dotIndex + 1) % dots.length;
            render();
        });
}

var dots = [
    [
        'digraph  {',
        '    node [style="filled", shape=rectangle]',
        '{ rank = same; sc0; m0; s0; 1; cbl0;}',

        'subgraph clusterSched {',
        '    sc0 [fillcolor="#B7D1DF", label="Course schedule"]',
        '    sca [fillcolor="#B7D1DF", label="Unit 1: An inflected language"]',
        '    scb [fillcolor="#B7D1DF", label="Unit 2: Expressing time and purpose"]',
        '    scc [fillcolor="#B7D1DF", label="Unit 3: Indirect statements and more expressions of time"]',
        '    sc0 -> sca',
        '    sca -> scb',
        '    scb -> scc',
        '}',

        'subgraph clusterMorph {',
        '    m0 [fillcolor="#BEDFC8", label="Morphology"]',
        '    m1 [fillcolor="#BEDFC8", label="Nouns and adjectives"]',
        '    m2 [fillcolor="#BEDFC8", label="Pronouns"]',
        '    m3 [fillcolor="#BEDFC8", label="Verbs"]',        
        '}',

        'subgraph clusterSyntax {',
        '    s0 [fillcolor="#F9F2B6", label="Syntax"]',
        '    s1 [fillcolor="#F9F2B6", label="Basic sentence structure"]',
        '    s2 [fillcolor="#F9F2B6", label="Indirect statement"]',
        '    s3 [fillcolor="#F9F2B6", label="Secondary clauses"]',
        '}',


        'subgraph clusterCBL {',
        '    cbl0 [fillcolor="#DDD0E5", label="CBL"]',
        '    cbl1 [fillcolor="#DDD0E5", label="CBL topic 1"]',
        '    cbl2 [fillcolor="#DDD0E5", label="CBL topic 2"]',
        '}',


        '    1 [style = "invisible"]',
        '    2 [style = "invisible"]',
        '    3 [style = "invisible"]',
        '    4 [style = "invisible"]',



        '   m0 -> m1 -> m2 -> m3',
        '   s0 -> s1 -> s2 -> s3',
        '   cbl0 -> cbl1 -> cbl2',

        '   m1 -> sca',
        '   m2 -> sca',
        '   m3 -> sca',

        '   s1 -> scb',
        '   s2 -> scb',
        '   s3 -> scc',





        '   1 -> 2  [style="invis"]',
        '   2 -> 3 [style="invis"]',
        '   3 -> 4 [style="invis"]',

        '}'
    ],
];

</script>
