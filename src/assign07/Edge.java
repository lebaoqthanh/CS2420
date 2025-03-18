package assign07;
/**
 * Represents an edge in a graph, connecting a source vertex to a destination
 * vertex.
 *
 * @param <Type> the type of data stored in the vertices
 * @author Thanh Le and Junhee Choi
 * @version March 5, 2025
 */
public class Edge<Type> {
    Vertex<Type> destination;
    double weight;
    /**
     * Constructs an Edge object with the given source and destination vertices.
     *
     * @param weight      the weight vertex of the edge
     * @param destinations the destination vertex of the edge
     */
    public Edge(Vertex<Type> destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
