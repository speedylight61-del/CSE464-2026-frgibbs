public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = GraphParser.parseGraph("input.dot");
            graph.addNode("e");
            graph.addNodes(new String[]{"f", "g"});
            graph.addEdge("e", "f");
            System.out.println(graph.toString());
            graph.outputGraph("graphOutput.txt");
            System.out.println("Graph saved to graphOutput.txt");
            for (int i = 0; i < 5; i++) {
                graph.graphSearch("a", "c", Graph.Algorithm.RANDOM);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}