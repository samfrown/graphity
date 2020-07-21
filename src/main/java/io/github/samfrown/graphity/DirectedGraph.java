package io.github.samfrown.graphity;

import java.util.Set;

/**
 * Simple Directed Graph implementation
 *
 * @param <T> vertex value type
 */
public class DirectedGraph<T> extends AbstractSimpleGraph<T> {

    public void addEdge(T from, T to) {
        Set<T> fromNeighbours = getNeighbours(from);
        getNeighbours(to);
        fromNeighbours.add(to);
    }

    public int countEdges() {
        return vertexNeighbours.values().stream().mapToInt(Set::size).sum();
    }
}
