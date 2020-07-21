package io.github.samfrown.graphity;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DirectedGraphTest extends AbstractSimpleGraphTest {

    @Override
    Graph<String> createGraph() {
        return new DirectedGraph<>();
    }

    @Test
    public void getPath_withOneEdge() {
        String v1 = "from";
        String v2 = "to";
        stringsGraph.addVertex(v1);
        stringsGraph.addVertex(v2);
        stringsGraph.addEdge(v1, v2);
        List<String> expectedV1V2 = asList(v1, v2);
        // when
        List<String> actualPathV1V2 = stringsGraph.getPath(v1, v2);
        List<String> actualPathV2V1 = stringsGraph.getPath(v2, v1);
        // then
        assertThat(actualPathV1V2, is(expectedV1V2));
        assertThat(actualPathV2V1, is(emptyList()));
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
        List<String> expectedPath = asList(v1, v2);
        // when
        List<String> actualPath = stringsGraph.getPath(v1, v2);
        // then
        assertThat(actualPath, is(expectedPath));
    }
}
