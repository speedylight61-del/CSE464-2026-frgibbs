import java.util.*;

public abstract class AbstractGraphSearch implements Search {

    public String search(Graph graph, String source, String destination) {
        if (source == null || destination == null) return null;
        source = source.trim();
        destination = destination.trim();
        if (!graph.getNodes().contains(source) || !graph.getNodes().contains(destination)) {
            return null;
        }
        Set<String> visited = new LinkedHashSet<>();
        Map<String, String> parent = new HashMap<>();
        setup(source);
        visited.add(source);
        while (!isEmpty()) {
            String current = nextNode();
            if (current.equals(destination)) {
                return buildPath(destination, parent);
            }
            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    addNode(neighbor);
                }
            }
        }
        return null;
    }
    protected abstract void setup(String source);
    protected abstract boolean isEmpty();
    protected abstract void addNode(String node);
    protected abstract String nextNode();

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