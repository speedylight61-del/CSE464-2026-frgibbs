import java.util.*;

// Random Walk chooses a random unvisited neighbor at each step.
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
            System.out.println("Visit Node History: " + String.join("-", path));
            if (current.equals(destination)) {
                System.out.println("Found target node: " + destination);
                return String.join("-", path);
            }
            List<String> neighbors = new ArrayList<>(graph.getNeighbors(current));
            neighbors.removeIf(visited::contains);
            if (neighbors.isEmpty()) {
                System.out.println("Reached dead end at node " + current);
                return null;
            }
            // Shuffle neighbors so the next step is random.
            Collections.shuffle(neighbors);
            String next = neighbors.get(0);
            path.add(next);
            visited.add(next);
        }
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