import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void testAddNode() {
        Graph g = new Graph();
        g.addNode("a");
        String output = g.toString();
        assertTrue(output.contains("a"));
    }
    @Test
    public void testAddNodes() {
        Graph g = new Graph();
        g.addNodes(new String[]{"a","b","c"});
        String output = g.toString();
        assertTrue(output.contains("a"));
        assertTrue(output.contains("b"));
        assertTrue(output.contains("c"));
    }
    @Test
    public void testAddEdge() {
        Graph g = new Graph();
        g.addEdge("a","b");
        String output = g.toString();
        assertTrue(output.contains("a -> b"));
    }
}