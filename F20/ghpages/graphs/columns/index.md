---
layout: page
title: Parallel columns
parent: Graphs
---




<script src="//d3js.org/d3.v4.min.js"></script>
<script src="https://unpkg.com/@hpcc-js/wasm@0.3.11/dist/index.min.js"></script>
<script src="https://unpkg.com/d3-graphviz@3.0.5/build/d3-graphviz.js"></script>

Graph div is here.
<div id="graph" style="text-align: center;"></div>
<script>

var dotIndex = 0;
var graphviz = d3.select("#graph").graphviz()
    .transition(function () {
        return d3.transition("main")
            .ease(d3.easeLinear)
            .delay(40)
            .duration(2000);
    })
    .logEvents(true)
    .on("initEnd", render);

function render() {
    var dotLines = [
        'digraph  {',
        '    node [style="filled"]',
    ];
    for (i = 0; i < shapes.length; i++) {
        if (dotIndex % 2 == 0) {
            shape = shapes[(dotIndex / 2) % shapes.length];
        } else {
            shape = shapes[i % shapes.length];
        }
        dotLines.push('    ' +  i + ' [label="' + shape + '" fillcolor="' + colors[i] +'" shape="' + shape + '"]');
    }
    dotLines = dotLines.concat([
        '   0 -> 1 -> 2 -> 3',
        '   4 -> 5 -> 6 -> 7',
        '   8 -> 9 -> 10 -> 11',
        '   12 -> 13 -> 14 -> 15',
        '   16 -> 17 -> 18 -> 19',
    ]);
    dotLines.push('}');
    var dot = dotLines.join('');
    graphviz
        .renderDot(dot)
        .on("end", function () {
            dotIndex += 1;
            render();
        });
}

var shapes = [
//    "box",
    "polygon",
    "ellipse",
//    "oval",
    "circle",
    "point",
    "egg",
    "triangle",
    "diamond",
    "trapezium",
    "parallelogram",
    "house",
    "pentagon",
    "hexagon",
    "septagon",
    "octagon",
    "invtriangle",
    "invtrapezium",
    "invhouse",
//    "rect",
//    "rectangle",
    "square",
    "star",
    "cds",
];

var colors = d3.schemeCategory20;

</script>
