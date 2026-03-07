import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {

    private Set<String> nodes = new LinkedHashSet<>();
    private Set<String> edges = new LinkedHashSet<>();

    public void addNode(String label) {
        if (label != null && !label.trim().isEmpty()) {
            nodes.add(label.trim());
        }
    }

    public void addNodes(String[] labels) {
        if (labels == null) {
            return;
        }

        for (String label : labels) {
            addNode(label);
        }
    }

    public void addEdge(String srcLabel, String dstLabel) {
        if (srcLabel != null && dstLabel != null) {
            srcLabel = srcLabel.trim();
            dstLabel = dstLabel.trim();

            if (!srcLabel.isEmpty() && !dstLabel.isEmpty()) {
                addNode(srcLabel);
                addNode(dstLabel);
                edges.add(srcLabel + " -> " + dstLabel);
            }
        }
    }

    public void outputGraph(String filepath) throws Exception {
        FileWriter writer = new FileWriter(filepath);

        writer.write("digraph {\n");

        for (String edge : edges) {
            writer.write("    " + edge + ";\n");
        }

        writer.write("}\n");

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