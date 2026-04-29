import java.util.*;

public class RandomWalkSearch extends AbstractGraphSearch {
    public String search(Graph graph, String source, String destination) {
        if (source == null || destination == null) return null;
        source = source.trim();
        destination = destination.trim();
        if (!graph.getNodes().contains(source) || !graph.getNodes().contains(destination)) {
            return null;
        }
        System.out.println("random testing");
        List<String> path = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        path.add(source);
        visited.add(source);
        while (true) {
            String current = path.get(path.size() - 1);
            System.out.println("visiting Path{nodes=" + formatPath(path) + "}");
            if (current.equals(destination)) {
                return formatPath(path);
            }
            List<String> neighbors = new ArrayList<>(graph.getNeighbors(current));
            neighbors.removeIf(visited::contains);
            if (neighbors.isEmpty()) {
                return null;
            }
            Collections.shuffle(neighbors);
            String next = neighbors.get(0);
            path.add(next);
            visited.add(next);
        }
    }
    private String formatPath(List<String> path) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < path.size(); i++) {
            result.append("Node{").append(path.get(i)).append("}");
            if (i < path.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    protected void setup(String source) {
    }
    protected boolean isEmpty() {
        return true;
    }
    protected void addNode(String node) {
    }
    protected String nextNode() {
        return null;
    }
}