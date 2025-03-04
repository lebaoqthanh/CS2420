package assign07;

import java.util.*;

public class GraphUtility {

    public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size");
        }

        graph<Type> graph = new graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.add(sources.get(i));
            graph.add(destinations.get(i));
            graph.setEdge(sources.get(i), destinations.get(i));
        }

        if (graph.get(srcData) == null || graph.get(dstData) == null) {
            throw new IllegalArgumentException("Source or destination vertex does not exist in the graph");
        }

        return graph.depthFirstSearch(srcData, dstData);
    }

    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size");
        }

        graph<Type> graph = new graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.add(sources.get(i));
            graph.add(destinations.get(i));
            graph.setEdge(sources.get(i), destinations.get(i));
        }

        if (graph.get(srcData) == null || graph.get(dstData) == null) {
            throw new IllegalArgumentException("Source or destination vertex does not exist in the graph");
        }

        List<Type> path = graph.breadthFirstSearch(srcData, dstData);
        if (path.isEmpty()) {
            throw new IllegalArgumentException("No path exists between the given vertices");
        }

        return path;
    }

    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size");
        }

        graph<Type> graph = new graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.add(sources.get(i));
            graph.add(destinations.get(i));
            graph.setEdge(sources.get(i), destinations.get(i));
        }

        List<Type> sortedList = graph.topoSort();
        if (sortedList.size() != graph.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }

        return sortedList;
    }
}
