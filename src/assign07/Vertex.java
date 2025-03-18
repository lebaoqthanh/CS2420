package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a vertex in a graph.
 *
 * @param <Type> the type of data stored in the vertex
 * @author  Thanh Le and Junhee Choi
 * @version March 5, 2025
 */
public class Vertex<Type> {
    private final Type value;
    private final List<Vertex<Type>> neighbors;

    /**
     * Constructs a Vertex object with the given data.
     *
     * @param value the value to be stored in the vertex
     */
    public Vertex(Type value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    /**
     * Gets the value stored in the vertex.
     *
     * @return the value stored in the vertex
     */
    public Type getValue() {
        return value;
    }

    /**
     * Gets the list of neighboring vertices of this vertex.
     *
     * @return the list of neighboring vertices of this vertex
     */
    public List<Vertex<Type>> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets the list of neighboring vertices of this vertex.
     *
     * @return the list of neighboring vertices of this vertex
     */
    public void addNeighbor(Vertex<Type> neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * compare the this and obj
     * @param obj object to compare with this
     * @return if equal this and obj, return ture. otherwise return false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) obj;
        return Objects.equals(value, vertex.value);
    }
}