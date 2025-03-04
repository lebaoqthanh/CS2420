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
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException("One or both vertices not found.");
        }
        fromNode.edges.add(toNode); // Remove the line that makes it undirected
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
    public List<Type> topoSort() {
        List<Type> result = new ArrayList<>();
        Map<Node, Integer> inDegree = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // Calculate in-degrees
        for (Node node : nodes.values()) {
            inDegree.put(node, 0);
        }
        for (Node node : nodes.values()) {
            for (Node neighbor : node.edges) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Add nodes with in-degree 0 to the queue
        for (Node node : nodes.values()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        // Process the queue
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.data);

            for (Node neighbor : current.edges) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check for cycles
        if (result.size() != nodes.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }

        return result;
    }


}

