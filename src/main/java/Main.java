public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = GraphParser.parseGraph("input.dot");
            graph.addNode("e");
            graph.addNodes(new String[]{"f", "g", "a"});
            System.out.println(graph.toString());

            graph.outputGraph("graphOutput.txt");
            System.out.println("Graph saved to graphOutput.txt");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}