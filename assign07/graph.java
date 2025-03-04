package assign07;
import java.util.*;

public class graph<Type> {
    private HashMap<Type, Node> nodes;


    public class Node {
        private Type data;
        private List<Node> edges;
        private List<Node> from;


        public Node(Type data) {
            this.data = data;
            this.edges = new ArrayList<>();
        }
    }

    public graph() {
        nodes = new HashMap<Type,Node>();

    }

    public void add(Type vertex) {
        if (!nodes.containsKey(vertex)) {
            nodes.put(vertex, new Node(vertex));
        }
       else{
           return;
        }
    }

    public Node get(Type vertex) {
        return nodes.get(vertex);
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

    public List<Node> getEdges(Type from) {
        Node node = get(from);
        return (node != null) ? node.edges : Collections.emptyList();
    }

    public boolean depthFirstSearch(Type source, Type destination) {
        Node from = get(source);
        Node to = get(destination);
        if (from == null || to == null) {
            return false;
        }
        Set<Node> visited = new HashSet<>();
        return dfs(from, to, visited);
    }

    private boolean dfs(Node from, Node to, Set<Node> visited) {
        if (visited.contains(from)) {
            return false;
        }
        visited.add(from);
        if (from.equals(to)) {
            return true;
        }
        for (Node node : from.edges) {
            if (dfs(node, to, visited)) {
                return true;
            }
        }
        return false;
    }
    public List<Type> breadthFirstSearch(Type source, Type destination) {
        Node start = get(source);
        Node end = get(destination);
        if (start == null || end == null) {
            return Collections.emptyList();
        }

        Queue<List<Node>> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        List<Node> startPath = new ArrayList<>();
        startPath.add(start);
        queue.add(startPath);
        visited.add(start);

        while (!queue.isEmpty()) {
            List<Node> path = queue.poll();
            Node current = path.get(path.size() - 1);

            if (current.equals(end)) {
                return extractPath(path); // Convert Node list to Type list
            }

            for (Node neighbor : current.edges) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Node> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    // Converts a list of Nodes to a list of Type values
    private List<Type> extractPath(List<Node> path) {
        List<Type> result = new ArrayList<>();
        for (Node node : path) {
            result.add(node.data);
        }
        return result;
    }
    public List<Type> topoSort(){
        List<Type> result= new ArrayList<>();
        int[]inDegree =new int[nodes.size()];
        List<Node> nodeList= new ArrayList<>(nodes.values());
        Queue<Node> queue= new LinkedList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            for (Node neighbor : node.edges) {
                int index = nodeList.indexOf(neighbor);
                inDegree[index]++;
            }
        }
        for (int i=0; i< nodeList.size(); i++) {
            if (inDegree[i]==0){
                result.add(nodeList.get(i).data);
            }
        }
        while(!queue.isEmpty()){
            Node current= queue.poll();
            result.add(current.data);
            for (Node neighbor : current.edges) {
                int index = nodeList.indexOf(neighbor);
                inDegree[index]--;
                if (inDegree[index]==0){
                    queue.add(neighbor);
                }
            }
        }
        if (result.size()!= nodes.size()){
            throw new RuntimeException("CYCLE DETECETED");

        }
        return result;

    }


}

