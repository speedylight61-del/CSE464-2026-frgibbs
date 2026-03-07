import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    @Test
    public void testGraphOutputMatchesExpected() throws Exception {
        Graph g = new Graph();

        g.addNode("a");
        g.addNode("b");
        g.addNode("c");
        g.addNode("d");
        g.addNode("e");
        g.addNode("f");
        g.addNode("g");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("c", "d");
        g.addEdge("e", "f");
        g.addEdge("f", "g");
        g.outputGraph("testOutput.txt");

        String expected = Files.readString(Paths.get("expected.txt"));
        String actual = Files.readString(Paths.get("testOutput.txt"));

        assertEquals(expected.trim(), actual.trim());
    }
}