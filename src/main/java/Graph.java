import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {

    private Set<String> nodes = new LinkedHashSet<>();
    private Set<String> edges = new LinkedHashSet<>();

    public void addNode(String label) {
        nodes.add(label);
    }

    public void addNodes(String[] labels) {
        for (String label : labels) {
            addNode(label);
        }
    }

    public void addEdge(String srcLabel, String dstLabel) {
        nodes.add(srcLabel);
        nodes.add(dstLabel);
        edges.add(srcLabel + " -> " + dstLabel);
    }

    public void outputGraph(String filepath) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        writer.write(toString());
        writer.close();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Number of nodes: ").append(nodes.size()).append("\n");
        result.append("Nodes:\n");

        for (String node : nodes) {
            result.append(node).append("\n");
        }

        result.append("Number of edges: ").append(edges.size()).append("\n");
        result.append("Edges:\n");

        for (String edge : edges) {
            result.append(edge).append("\n");
        }

        return result.toString();
    }
}