package io.github.samfrown.graphity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public abstract class AbstractSimpleGraph<T> implements Graph<T> {

    final Map<T, Set<T>> vertexNeighbours;

    AbstractSimpleGraph() {
        this.vertexNeighbours = new HashMap<>();
    }

    @Override
    public void addVertex(T value) {
        vertexNeighbours.putIfAbsent(value, new HashSet<>());
    }

    @Override
    public int countVertexes() {
        return vertexNeighbours.size();
    }

    @Override
    public abstract void addEdge(T from, T to);

    @Override
    public abstract int countEdges();

    @Override
    public List<T> getPath(T from, T to) {
        getNeighbours(from);
        getNeighbours(to);

        Map<T, Integer> visitedVertexes = new HashMap<>(countVertexes());
        Deque<T> stack = new ArrayDeque<>();
        stack.add(from);
        while (!stack.isEmpty()) {
            T vertex = stack.peekLast();
            if (vertex.equals(to)) {
                return concat(
                        stack.stream().filter(v -> getVisits(v, visitedVertexes) == 1),
                        Stream.of(to)).collect(toList());
            }
            int visits = getVisits(vertex, visitedVertexes);
            visitedVertexes.put(vertex, visits + 1);
            if (visits != 0) {
                stack.removeLast();
                continue;
            }
            vertexNeighbours.get(vertex).stream()
                    .filter(v -> getVisits(v, visitedVertexes) == 0)
                    .forEach(stack::add);
        }

        return emptyList();
    }

    private int getVisits(T vertex, Map<T, Integer> visited) {
        Integer visits = visited.get(vertex);
        if (visits == null) {
            visited.put(vertex, 0);
            return 0;
        }
        return visits;
    }

    Set<T> getNeighbours(T vertex) {
        Set<T> neighbours = vertexNeighbours.get(vertex);
        if (neighbours == null) {
            throw new IllegalArgumentException("Vertex '" + vertex + "' doesn't exists");
        }
        return neighbours;
    }
}
