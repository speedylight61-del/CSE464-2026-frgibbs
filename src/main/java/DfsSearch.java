import java.util.*;

// DFS uses a stack to search deeper before moving to another branch.
public class DfsSearch extends AbstractGraphSearch {
    private Stack<String> stack;
    protected void setup(String source) {
        stack = new Stack<>();
        stack.push(source);
    }
    protected boolean isEmpty() {
        return stack.isEmpty();
    }
    protected void addNode(String node) {
        stack.push(node);
    }
    protected String nextNode() {
        return stack.pop();
    }
}