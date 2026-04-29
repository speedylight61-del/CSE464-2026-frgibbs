import java.util.*;

public class BfsSearch extends AbstractGraphSearch {
    private Queue<String> queue;
    protected void setup(String source) {
        queue = new LinkedList<>();
        queue.add(source);
    }
    protected boolean isEmpty() {
        return queue.isEmpty();
    }
    protected void addNode(String node) {
        queue.add(node);
    }
    protected String nextNode() {
        return queue.poll();
    }
}