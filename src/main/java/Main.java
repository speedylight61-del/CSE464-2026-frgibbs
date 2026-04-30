public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = GraphParser.parseGraph("input.dot");
            System.out.println(graph.toString());
            graph.outputGraph("graphOutput.txt");
            System.out.println("Graph saved to graphOutput.txt");
            System.out.println("\n--- BFS Search ---");
            graph.graphSearch("a", "h", Graph.Algorithm.BFS);
            System.out.println("\n--- DFS Search ---");
            graph.graphSearch("a", "h", Graph.Algorithm.DFS);
            System.out.println("\n--- Random Walk Search ---");
            for (int i = 0; i < 5; i++) {
                System.out.println("Run " + (i + 1));
                graph.graphSearch("a", "h", Graph.Algorithm.RANDOM);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}