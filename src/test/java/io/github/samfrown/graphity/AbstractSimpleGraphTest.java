package io.github.samfrown.graphity;

import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class AbstractSimpleGraphTest {

    // under test
    final Graph<String> stringsGraph = createGraph();

    abstract Graph<String> createGraph();

    @Test
    public void addVertex_inEmptyGraph() {
        // given
        String v1 = "a";
        // when
        stringsGraph.addVertex(v1);
        // then
        assertEquals(1, stringsGraph.countVertexes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdge_inEmptyGraph() {
        stringsGraph.addEdge("a", "b");
        // throws
    }

    @Test
    public void countVertexes_forEmptyGraph() {
        int actual = stringsGraph.countVertexes();
        assertEquals(0, actual);
    }

    @Test
    public void countEdges_forEmptyGraph() {
        int actual = stringsGraph.countEdges();
        assertEquals(0, actual);
    }

    @Test
    public void addEdge_betweenExisted() {
        // given
        String v1 = "from";
        String v2 = "to";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        // when
        stringsGraph.addEdge(v1, v2);
        // then
        assertEquals(1, stringsGraph.countEdges());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPath_forEmptyGraph() {
        stringsGraph.getPath("a", "b");
        // throws
    }

    @Test
    public void getPath_withNoEdge() {
        String v1 = "from";
        String v2 = "to";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        // when
        List<String> actualPath = stringsGraph.getPath(v1, v2);
        // then
        assertTrue(actualPath.isEmpty());
    }

    @Test
    public void getPath_withOneVertex() {
        String v1 = "v1";
        String v2 = "v2";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addEdge(v1, v2);
        List<String> expectedPath = singletonList(v2);
        // when
        List<String> actualPath = stringsGraph.getPath(v2, v2);
        // then
        assertThat(actualPath, is(expectedPath));
    }

}