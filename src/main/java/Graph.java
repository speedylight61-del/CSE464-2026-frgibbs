import java.io.FileWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    private Set<String> nodes = new LinkedHashSet<>();
    private Set<String> edges = new LinkedHashSet<>();

    public enum Algorithm {
        BFS, DFS
    }

    public void addNode(String label) {
        if (label != null && !label.trim().isEmpty()) {
            nodes.add(cleanLabel(label));
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
            srcLabel = cleanLabel(srcLabel);
            dstLabel = cleanLabel(dstLabel);
            if (!srcLabel.isEmpty() && !dstLabel.isEmpty()) {
                addNode(srcLabel);
                addNode(dstLabel);
                edges.add(srcLabel + " -> " + dstLabel);
            }
        }
    }

    public void removeNode(String nodeLabel) {
        if (nodeLabel == null || nodeLabel.trim().isEmpty()) {
            throw new IllegalArgumentException("bad input");
        }
        String clean = cleanLabel(nodeLabel);
        if (!nodes.remove(clean)) {
            throw new IllegalArgumentException("node missing");
        }
        Set<String> updatedEdges = new LinkedHashSet<>();
        for (String e : edges) {
            String[] parts = splitEdge(e);
            String left = parts[0];
            String right = parts[1];

            if (!left.equals(clean) && !right.equals(clean)) {
                updatedEdges.add(e);
            }
        }
        edges = updatedEdges;
    }

    public void removeNodes(String[] nodeArray) {
        if (nodeArray == null) {
            throw new IllegalArgumentException("null input");
        }
        for (int i = 0; i < nodeArray.length; i++) {
            removeNode(nodeArray[i]);
        }
    }

    public void removeEdge(String from, String to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("null edge");
        }
        String f = cleanLabel(from);
        String t = cleanLabel(to);
        if (f.isEmpty() || t.isEmpty()) {
            throw new IllegalArgumentException("empty edge");
        }
        String target = f + " -> " + t;
        if (!edges.remove(target)) {
            throw new IllegalArgumentException("edge missing");
        }
    }

    public String graphSearch(String source, String destination, Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return bfsSearch(source, destination);
        }
        return dfsSearch(source, destination);
    }

    private String[] splitEdge(String edge) {
        int i = edge.indexOf(" -> ");
        return new String[] {
            edge.substring(0, i),
            edge.substring(i + 4)
        };
    }

    private String cleanLabel(String label) {
        return label.trim();
    }

    private boolean hasValidSearchNodes(String source, String destination) {
        return source != null && destination != null
                && nodes.contains(cleanLabel(source))
                && nodes.contains(cleanLabel(destination));
    }

    private String bfsSearch(String source, String destination) {
        if (!hasValidSearchNodes(source, destination)) return null;
        source = cleanLabel(source);
        destination = cleanLabel(destination);

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new LinkedHashSet<>();
        Map<String, String> parent = new HashMap<>();

        q.add(source);
        visited.add(source);

        while (!q.isEmpty()) {
            String current = q.poll();
            if (current.equals(destination)) {
                break;
            }

            for (String e : edges) {
                String[] parts = splitEdge(e);
                String left = parts[0];
                String right = parts[1];

                if (left.equals(current) && !visited.contains(right)) {
                    visited.add(right);
                    parent.put(right, current);
                    q.add(right);
                }
            }
        }

        if (!visited.contains(destination)) return null;
        return buildPath(destination, parent);
    }

    private String dfsSearch(String source, String destination) {
        if (!hasValidSearchNodes(source, destination)) return null;
        source = cleanLabel(source);
        destination = cleanLabel(destination);

        Set<String> visited = new LinkedHashSet<>();
        Map<String, String> parent = new HashMap<>();

        dfsHelper(source, visited, parent);

        if (!visited.contains(destination)) return null;
        return buildPath(destination, parent);
    }

    private void dfsHelper(String current, Set<String> visited, Map<String, String> parent) {
        visited.add(current);
        for (String e : edges) {
            String[] parts = splitEdge(e);
            String left = parts[0];
            String right = parts[1];

            if (left.equals(current) && !visited.contains(right)) {
                parent.put(right, current);
                dfsHelper(right, visited, parent);
            }
        }
    }

    private String buildPath(String destination, Map<String, String> parent) {
        String path = destination;
        String cur = destination;

        while (parent.containsKey(cur)) {
            cur = parent.get(cur);
            path = cur + " -> " + path;
        }

        return path;
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