import java.io.FileWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Graph {

    private Set<String> nodes=new LinkedHashSet<>();
    private Set<String> edges=new LinkedHashSet<>();

    public void addNode(String label){
        if(label!=null&&!label.trim().isEmpty()){
            nodes.add(label.trim());
        }
    }

    public void addNodes(String[] labels){
        if(labels==null){
            return;
        }
        for(String label:labels){
            addNode(label);
        }
    }

    public void addEdge(String srcLabel,String dstLabel){
        if(srcLabel!=null&&dstLabel!=null){
            srcLabel=srcLabel.trim();
            dstLabel=dstLabel.trim();
            if(!srcLabel.isEmpty()&&!dstLabel.isEmpty()){
                addNode(srcLabel);
                addNode(dstLabel);
                edges.add(srcLabel+" -> "+dstLabel);
            }
        }
    }

    // removes one node and its edges
    public void removeNode(String nodeLabel){
        if(nodeLabel==null||nodeLabel.trim().isEmpty()){
            throw new IllegalArgumentException("bad input");
        }
        String clean=nodeLabel.trim();
        if(!nodes.remove(clean)){
            throw new IllegalArgumentException("node missing");
        }
        Set<String> updatedEdges=new LinkedHashSet<>();
        for(String e:edges){
            int i=e.indexOf(" -> ");
            String left=e.substring(0,i);
            String right=e.substring(i+4);
            if(!left.equals(clean)&&!right.equals(clean)){
                updatedEdges.add(e);
            }
        }
        edges=updatedEdges;
    }

    // removes multiple nodes
    public void removeNodes(String[] nodeArray){
        if(nodeArray==null){
            throw new IllegalArgumentException("null input");
        }
        for(int i=0;i<nodeArray.length;i++){
            removeNode(nodeArray[i]);
        }
    }

    // removes one edge
    public void removeEdge(String from,String to){
        if(from==null||to==null){
            throw new IllegalArgumentException("null edge");
        }
        String f=from.trim();
        String t=to.trim();
        if(f.isEmpty()||t.isEmpty()){
            throw new IllegalArgumentException("empty edge");
        }
        String target=f+" -> "+t;
        if(!edges.remove(target)){
            throw new IllegalArgumentException("edge missing");
        }
    }

    // BFS search to find a path from start to end
    public String graphSearch(String start,String end){
        if(start==null||end==null)return null;
        start=start.trim();
        end=end.trim();
        if(!nodes.contains(start)||!nodes.contains(end))return null;

        Queue<String> q=new LinkedList<>();
        Set<String> visited=new LinkedHashSet<>();
        Map<String,String> parent=new HashMap<>();

        q.add(start);
        visited.add(start);

        while(!q.isEmpty()){
            String current=q.poll();
            if(current.equals(end))break;

            for(String e:edges){
                int i=e.indexOf(" -> ");
                String left=e.substring(0,i);
                String right=e.substring(i+4);

                if(left.equals(current)&&!visited.contains(right)){
                    visited.add(right);
                    parent.put(right,current);
                    q.add(right);
                }
            }
        }

        if(!visited.contains(end))return null;

        String path=end;
        String cur=end;

        while(parent.containsKey(cur)){
            cur=parent.get(cur);
            path=cur+" -> "+path;
        }

        return path;
    }

    public void outputGraph(String filepath)throws Exception{
        FileWriter writer=new FileWriter(filepath);
        writer.write("digraph {\n");
        for(String edge:edges){
            writer.write("    "+edge+";\n");
        }
        writer.write("}\n");
        writer.close();
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append("Number of nodes: ").append(nodes.size()).append("\n");
        result.append("Nodes:\n");
        for(String node:nodes){
            result.append(node).append("\n");
        }
        result.append("Number of edges: ").append(edges.size()).append("\n");
        result.append("Edges:\n");
        for(String edge:edges){
            result.append(edge).append("\n");
        }
        return result.toString();
    }
}