import java.io.FileWriter;
import java.util.LinkedHashSet;
import java.util.Set;

public class Graph {

    private Set<String> nodes=new LinkedHashSet<>();
    private Set<String> edges=new LinkedHashSet<>();
    public void addNode(String label){
        if(label!=null&&!label.trim().isEmpty()){
            nodes.add(label.trim());
        }
    }
    public void addNodes(String[] labels){
        if(labels==null)return;
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

   // removes a node and cleans edges
public void removeNode(String nodeLabel){
    if(nodeLabel==null||nodeLabel.trim().isEmpty()){
        throw new IllegalArgumentException("bad input");
    }
    String clean=nodeLabel.trim();
    if(!nodes.remove(clean)){
        throw new IllegalArgumentException("node missing");
    }
    // rebuild edge set instead of removing while looping
    Set<String> updatedEdges=new LinkedHashSet<>();
    for(String e:edges){
        int arrowIndex=e.indexOf(" -> ");
        String left=e.substring(0,arrowIndex);
        String right=e.substring(arrowIndex+4);
        if(!left.equals(clean)&&!right.equals(clean)){
            updatedEdges.add(e);
        }
    }
    edges=updatedEdges;
}

// removes a list of nodes
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
    boolean removed=edges.remove(target);
    if(!removed){
        throw new IllegalArgumentException("edge missing");
    }
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