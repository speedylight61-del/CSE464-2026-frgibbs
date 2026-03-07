public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = GraphParser.parseGraph("input.dot");
            System.out.println(graph.toString());

            graph.outputGraph("graphOutput.txt");
            System.out.println("Graph output saved to graphOutput.txt");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}