package assign07;

import java.util.*;

/**
 * Represents a graph data structure consisting of vertices and edges.
 *
 * @param <Type> the type of data stored in the vertices
 * @author Thanh Le and Junhee Choi
 * @version 6/3/2025
 */
public class Graph<Type> {
    private Map<Type, Vertex<Type>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void addVertex(Type value) {
        if (!vertices.containsKey(value)) {
            vertices.put(value, new Vertex<>(value));
        }
    }

    public Map<Type, Vertex<Type>> getVertices() {
        return vertices;
    }

    public Vertex<Type> getVertex(Type data) {
        return vertices.get(data);
    }

    public void addEdge(Type src, Type dest) {
        addVertex(src);
        addVertex(dest);
        vertices.get(src).addNeighbor(vertices.get(dest));
    }

    public boolean depthFirstSearch(Type source, Type destination) {
        if (!vertices.containsKey(source) || !vertices.containsKey(destination)) {
            return false;
        }
        Set<Type> visited = new HashSet<>();
        return dfsHelper(vertices.get(source), vertices.get(destination), visited);
    }

    private boolean dfsHelper(Vertex<Type> current, Vertex<Type> destination, Set<Type> visited) {
        if (current.getValue().equals(destination.getValue())) { // Fixed comparison
            return true;
        }

        visited.add(current.getValue());
        for (Vertex<Type> neighbor : current.getNeighbors()) {
            if (!visited.contains(neighbor.getValue()) && dfsHelper(neighbor, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    public List<Type> breadthFirstSearch(Type source, Type destination) {
        if (!vertices.containsKey(source) || !vertices.containsKey(destination)) {
            return new ArrayList<>();
        }

        Map<Type, Type> parentMap = new HashMap<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();
        Set<Type> visited = new HashSet<>();

        queue.offer(vertices.get(source));
        visited.add(source);

        while (!queue.isEmpty()) {
            Vertex<Type> current = queue.poll();

            if (current.getValue().equals(destination)) { // Fixed comparison
                return constructPath(parentMap, source, destination);
            }

            for (Vertex<Type> neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor.getValue())) {
                    visited.add(neighbor.getValue());
                    parentMap.put(neighbor.getValue(), current.getValue());
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Type> constructPath(Map<Type, Type> parentMap, Type source, Type destination) {
        if (!parentMap.containsKey(destination)) { // Ensures a valid path exists
            return new ArrayList<>();
        }
        LinkedList<Type> path = new LinkedList<>();
        for (Type at = destination; at != null; at = parentMap.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

    public List<Type> topoSort() {
        Map<Type, Integer> inDegree = new HashMap<>();
        for (Type key : vertices.keySet()) {
            inDegree.putIfAbsent(key, 0);
            for (Vertex<Type> neighbor : vertices.get(key).getNeighbors()) {
                inDegree.put(neighbor.getValue(), inDegree.getOrDefault(neighbor.getValue(), 0) + 1);
            }
        }

        Queue<Vertex<Type>> queue = new LinkedList<>();
        for (Type key : vertices.keySet()) {
            if (inDegree.getOrDefault(key, 0) == 0) { // Fixed null safety
                queue.offer(vertices.get(key));
            }
        }

        List<Type> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex<Type> current = queue.poll();
            topoOrder.add(current.getValue());

            for (Vertex<Type> neighbor : current.getNeighbors()) {
                inDegree.put(neighbor.getValue(), inDegree.get(neighbor.getValue()) - 1);
                if (inDegree.get(neighbor.getValue()) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return topoOrder.size() == vertices.size() ? topoOrder : new ArrayList<>();
    }
}
