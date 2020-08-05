---
layout: page
title: Two graphs on same page
parent: Graphs
---

<script src="//d3js.org/d3.v5.min.js"></script>
<script src="https://unpkg.com/@hpcc-js/wasm@0.3.11/dist/index.min.js"></script>
<script src="https://unpkg.com/d3-graphviz@3.0.5/build/d3-graphviz.js"></script>




<p>Before</p>
<div id="graph" style="text-align: center;"></div>


<p>After</p>
<div id="graph2" style="text-align: center;"></div>



<script>
d3.select("#graph").graphviz()
    .renderDot('digraph  {a -> b}');

d3.select("#graph2").graphviz()
        .renderDot('digraph  {b -> a}');
</script>
