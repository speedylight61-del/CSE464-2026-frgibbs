import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphTest {

    @Test
    public void checkSingleNodeInsert() {
        Graph graphObj = new Graph();
        graphObj.addNode("x");

        String resultText = graphObj.toString();
        assertTrue(resultText.contains("x"));
    }

    @Test
    public void checkMultipleNodeInsert() {
        Graph graphObj = new Graph();
        graphObj.addNodes(new String[]{"x", "y", "z"});

        String resultText = graphObj.toString();
        assertTrue(resultText.contains("x"));
        assertTrue(resultText.contains("y"));
        assertTrue(resultText.contains("z"));
    }

    @Test
    public void checkEdgeInsert() {
        Graph graphObj = new Graph();
        graphObj.addEdge("x", "y");

        String resultText = graphObj.toString();
        assertTrue(resultText.contains("x -> y"));
    }

    @Test
    public void verifyGraphFileOutput() throws Exception {
        Graph graphObj = new Graph();

        graphObj.addNodes(new String[]{"a","b","c","d","e","f","g"});
        graphObj.addEdge("a", "b");
        graphObj.addEdge("b", "c");
        graphObj.addEdge("c", "d");
        graphObj.addEdge("e", "f");
        graphObj.addEdge("f", "g");

        graphObj.outputGraph("tempOutput.txt");

        String expectedContent = Files.readString(Paths.get("expected.txt"));
        String actualContent = Files.readString(Paths.get("tempOutput.txt"));

        assertEquals(expectedContent.trim(), actualContent.trim());
    }

    @Test
    public void testNodeRemovalBehavior() {
        Graph graphObj = new Graph();
        graphObj.addNodes(new String[]{"p", "q"});
        graphObj.addEdge("p", "q");

        graphObj.removeNode("p");

        String resultText = graphObj.toString();
        assertFalse(resultText.contains("p\n"));
        assertFalse(resultText.contains("p -> q"));
        assertTrue(resultText.contains("q"));
    }

    @Test
    public void testBulkNodeRemoval() {
        Graph graphObj = new Graph();
        graphObj.addNodes(new String[]{"p", "q", "r"});
        graphObj.addEdge("p", "q");
        graphObj.addEdge("q", "r");

        graphObj.removeNodes(new String[]{"p", "q"});

        String resultText = graphObj.toString();
        assertFalse(resultText.contains("p\n"));
        assertFalse(resultText.contains("q\n"));
        assertFalse(resultText.contains("p -> q"));
        assertFalse(resultText.contains("q -> r"));
        assertTrue(resultText.contains("r"));
    }

    @Test
    public void testEdgeRemovalBehavior() {
        Graph graphObj = new Graph();
        graphObj.addEdge("p", "q");

        graphObj.removeEdge("p", "q");

        String resultText = graphObj.toString();
        assertFalse(resultText.contains("p -> q"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingMissingNodeThrowsError() {
        Graph graphObj = new Graph();
        graphObj.addNode("p");

        graphObj.removeNode("missingNode");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingMultipleWithInvalidNodeThrowsError() {
        Graph graphObj = new Graph();
        graphObj.addNodes(new String[]{"p", "q"});

        graphObj.removeNodes(new String[]{"p", "invalid"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNonExistingEdgeThrowsError() {
        Graph graphObj = new Graph();
        graphObj.addEdge("p", "q");

        graphObj.removeEdge("q", "p");
    }
}