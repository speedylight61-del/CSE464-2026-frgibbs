import java.util.*;

// This class defines the shared search steps for all algorithms
public abstract class AbstractGraphSearch implements Search {
    // Main search method used by BFS and DFS
    public String search(Graph graph, String source, String destination) {
        if (source == null || destination == null) return null;
        source = source.trim();
        destination = destination.trim();
        // Make sure nodes exist in the graph
        if (!graph.getNodes().contains(source) || !graph.getNodes().contains(destination)) {
            return null;
        }
        Set<String> visited = new LinkedHashSet<>();
        Map<String, String> parent = new HashMap<>();
        // Setup structure, queue for BFS, stack for DFS
        setup(source);
        visited.add(source);
        while (!isEmpty()) {
            String current = nextNode();
            // Print visit history for demo (uses dash format)
            System.out.println("Visit Node History: " + buildPath(current, parent).replace(" -> ", "-"));
            // Stop when destination is found
            if (current.equals(destination)) {
                System.out.println("Found target node: " + destination);
                return buildPath(destination, parent); 
            }
            // Explore neighbors of current node
            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    addNode(neighbor); // BFS vs DFS behavior
                }
            }
        }

        return null;
    }
    // These methods are different for BFS and DFS
    protected abstract void setup(String source);
    protected abstract boolean isEmpty();
    protected abstract void addNode(String node);
    protected abstract String nextNode();
    // Builds the path from source to destination
    protected String buildPath(String destination, Map<String, String> parent) {
        String path = destination;
        String cur = destination;

        while (parent.containsKey(cur)) {
            cur = parent.get(cur);
            path = cur + " -> " + path;
        }
        return path;
    }
}