# Simple Graph library

Supports directed and undirected graphs.
Vertices should be of a user defined type.
 

##Example
```java
Graph<String> roadGraph = new UndirectedGraph<>();
roadGraph.addVertex("Piter");
roadGraph.addVertex("Moscow") ;
roadGraph.addEdge("Piter", "Moscow");

List<String> path = roadGraph.getPath("Piter", "Moscow"); 
```
#API
See javadoc

#TODO
1. Add generated graph tests
1. Add weighted edges support
1. Add traverse function that will take user defined function and apply it on every vertex of the graph
1. Thread safe graph

