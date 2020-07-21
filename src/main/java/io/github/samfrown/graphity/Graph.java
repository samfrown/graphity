package io.github.samfrown.graphity;

import java.util.List;

/**
 * Graph interface
 * @param <T> vertex value data type
 */
public interface Graph<T> {

    /**
     * Add new vertex
     * Do nothing if this vertex already exists
     *
     * @param value new vertex value
     */
    void addVertex(T value);

    /**
     * Add edge between vertexes 'from' and 'to'.
     * Throws {@link IllegalArgumentException} if either vertex doesn't exist
     *
     * @param from vertex value
     * @param to   other vertex value
     */
    void addEdge(T from, T to);

    /**
     * Get path as list of values between vertexes 'from' and 'to'
     * or empty list if no path found.
     * NB! Returned path can be not optimal.
     *
     * Throws {@link IllegalArgumentException} if either vertex doesn't exist
     *
     * @param from vertex value
     * @param to   other vertex value
     */
    List<T> getPath(T from, T to);

    /**
     * Count number of vertexes
     *
     * @return number of vertexes
     */
    int countVertexes();

    /**
     * Count number of edges
     *
     * @return number of edges
     */
    int countEdges();
}
