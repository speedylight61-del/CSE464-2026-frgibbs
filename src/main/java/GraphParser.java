import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GraphParser {

    public static Graph parseGraph(String filepath) throws IOException {
        Graph graph = new Graph();

        List<String> lines = Files.readAllLines(Path.of(filepath));

        for (String line : lines) {
            line = line.trim();

            if (line.length() == 0) {
                continue;
            }

            if (line.equals("digraph {") || line.equals("{") || line.equals("}")) {
                continue;
            }

            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }

            int arrowIndex = line.indexOf("->");

            if (arrowIndex != -1) {
                String left = line.substring(0, arrowIndex).trim();
                String right = line.substring(arrowIndex + 2).trim();

                if (!left.isEmpty() && !right.isEmpty()) {
                    graph.addEdge(left, right);
                }
            }
        }

        return graph;
    }
}