package assign07;
import java.util.List;
/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 *
 * @author Thanh Le and Junhee Choi
 * @version March 5, 2025
 */

public class GraphUtility {
    /**
     * Checks if there exists a path between two vertices in a graph.
     *
     * @param <Type>         the type of data stored in vertices
     * @param sources        list of source vertices
     * @param destinations   list of destination vertices
     * @param srcData        data of the source vertex
     * @param dstData        data of the destination vertex
     * @return               true if there is a path between source and destination, false otherwise
     * @throws IllegalArgumentException when sources and destinations list sizes do not match
     */
    public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");
        }

        Graph<Type> graph = buildGraph(sources, destinations);

        if (!graph.getVertices().containsKey(srcData) || !graph.getVertices().containsKey(dstData)) {
            throw new IllegalArgumentException("Graph does not contain one or both of the specified vertices.");
        }

        return graph.depthFirstSearch(srcData, dstData);
    }

    /**
     * Finds the shortest path between two vertices in a graph using breadth-first search.
     *
     * @param <Type>         the type of data stored in vertices
     * @param sources        list of source vertices
     * @param destinations   list of destination vertices
     * @param srcData        data of the source vertex
     * @param dstData        data of the destination vertex
     * @return               list of vertices representing the shortest path
     * @throws IllegalArgumentException when sources and destinations list sizes do not match or when no path exists
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");
        }

        Graph<Type> graph = buildGraph(sources, destinations);

        if (!graph.getVertices().containsKey(srcData) || !graph.getVertices().containsKey(dstData)) {
            throw new IllegalArgumentException("Graph does not contain one or both of the specified vertices.");
        }

        List<Type> path = graph.breadthFirstSearch(srcData, dstData);
        if (path.isEmpty()) {
            throw new IllegalArgumentException("No path exists between the specified vertices.");
        }

        return path;
    }

    /**
     * Sorts vertices of a graph topologically.
     *
     * @param <Type>         the type of data stored in vertices
     * @param sources        list of source vertices
     * @param destinations   list of destination vertices
     * @return               list of vertices sorted in topological order
     * @throws IllegalArgumentException when sources and destinations list sizes do not match or when a cycle is detected
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must have the same size.");
        }

        Graph<Type> graph = buildGraph(sources, destinations);
        List<Type> sortedOrder = graph.topoSort();

        if (sortedOrder.size() != graph.getVertices().size()) {
            throw new IllegalArgumentException("Graph contains a cycle and cannot be topologically sorted.");
        }

        return sortedOrder;
    }

    /**
     * build a graph by using data
     * @param <Type> the type of data stored in vertices
     * @param sources list of source vertices
     * @param destinations list of destination vertices
     * @return return the graph which made by these data
     */
    private static <Type> Graph<Type> buildGraph(List<Type> sources, List<Type> destinations) {
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i));
        }
        return graph;
    }
}