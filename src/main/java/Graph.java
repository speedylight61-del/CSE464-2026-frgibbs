import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<String> nodes = new HashSet<>();
    private Set<String> edges = new HashSet<>();

    public void addNode(String label) {
        nodes.add(label);
    }

    public void addEdge(String srcLabel, String dstLabel) {
        nodes.add(srcLabel);
        nodes.add(dstLabel);
        edges.add(srcLabel + " -> " + dstLabel);
    }

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