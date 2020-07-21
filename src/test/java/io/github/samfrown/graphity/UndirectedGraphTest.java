package io.github.samfrown.graphity;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest extends AbstractSimpleGraphTest {

    @Override
    Graph<String> createGraph() {
        return new UndirectedGraph<>();
    }

    @Test
    public void getPath_withOneEdge() {
        String v1 = "from";
        String v2 = "to";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addEdge(v1, v2);
        List<String> expectedV1V2 = asList(v1, v2);
        List<String> expectedV2V1 = asList(v2, v1);
        // when
        List<String> actualV1V2 = stringsGraph.getPath(v1, v2);
        List<String> actualV2V1 = stringsGraph.getPath(v2, v1);
        // then
        assertThat(actualV1V2, is(expectedV1V2));
        assertThat(actualV2V1, is(expectedV2V1));
    }

    @Test
    public void getPath_withTriangle() {
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addVertex(v3);
        stringsGraph.addEdge(v1, v2);
        stringsGraph.addEdge(v2, v3);
        stringsGraph.addEdge(v1, v3);
        List<String> expectedPath = asList(v3, v2, v1);
        // when
        List<String> actualPath = stringsGraph.getPath(v3, v1);
        // then
        assertThat(actualPath, is(expectedPath));
    }

    @Test
    public void getPath_withManyVertex() {
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String v5 = "v5";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addVertex(v3);
        stringsGraph.addVertex(v4);
        stringsGraph.addVertex(v5);
        stringsGraph.addEdge(v1, v2);
        stringsGraph.addEdge(v2, v3);
        stringsGraph.addEdge(v1, v3);
        stringsGraph.addEdge(v3, v5);
        stringsGraph.addEdge(v2, v3);
        stringsGraph.addEdge(v1, v4);
        List<String> expectedPath = asList(v4, v1, v3, v2);
        // when
        List<String> actualPath = stringsGraph.getPath(v4, v2);
        // then
        assertThat(actualPath, is(expectedPath));
    }

    @Test
    public void getPath_withLoopEdge() {
        String v1 = "v1";
        String v2 = "v2";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addEdge(v1, v2);
        stringsGraph.addEdge(v1, v1);
        stringsGraph.addEdge(v2, v2);
        List<String> expectedPath = asList(v2, v1);
        // when
        List<String> actualPath = stringsGraph.getPath(v2, v1);
        // then
        assertThat(actualPath, is(expectedPath));
    }

}