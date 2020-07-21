package io.github.samfrown.graphity;

import java.util.Set;

/**
 * Simple Undirected Graph implementation
 *
 * @param <T> vertex value type
 */
public class UndirectedGraph<T> extends AbstractSimpleGraph<T> {

    public void addEdge(T from, T to) {
        Set<T> fromNeighbours = getNeighbours(from);
        Set<T> toNeighbours = getNeighbours(to);
        fromNeighbours.add(to);
        toNeighbours.add(from);
    }

    public int countEdges() {
        int allLinks = vertexNeighbours.values().stream()
                .mapToInt(Set::size).sum();
        return allLinks / 2;
    }
}
