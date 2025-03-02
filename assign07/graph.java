package assign07;

import java.util.ArrayList;
import java.util.List;

public class  graph<Type> {
    private List<Node> nodes;
    private boolean visited;

    public class Node{
        private Type data;
        private List<Node> edges;
        public Node(Type data){
            this.data = data;
            this.edges = new ArrayList<Node>();
        }
    }
    public graph() {

    }
    public void add(Node node) {
        nodes.add(node);

    }
    public Node get(Type i) {
        for (Node node : nodes) {
            if (node.data.equals(i)) {
                return node;
            }
        }
        return null;
    }
    public int size() {
        return nodes.size();
    }
    public void setEdge(Type from, Type to) {
        Node fromNode = get(from);
        Node toNode = get(to);
        if (fromNode != null && toNode != null) {
            fromNode.edges.add(toNode);
            toNode.edges.add(fromNode);
        }

    }
    public ArrayList<Node> getEdges(Node from) {
        return (ArrayList<Node>)from.edges;


    }

    public boolean depthFirstSearch(Type source, Type destination){
        Node from= get(source);
        Node to = get(destination);
        if (from != null && to != null) {
            List<Node> visited = new ArrayList<>();



        }

    }

}
