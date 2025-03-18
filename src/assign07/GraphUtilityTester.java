package assign07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Unit tests for the GraphUtility class to test the static methods for connectivity, shortest path, and topological sort.
 * @author Thanh Le and Junhee Choi
 * @version March 6 2025
 */
public class GraphUtilityTester {

    private List<String> sources;
    private List<String> destinations;

    /**
     * Set up method to initialize the sources and destinations lists before each test.
     */
    @BeforeEach
    void setUp() {
        sources = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "D", "E"));
        destinations = new ArrayList<>(Arrays.asList("B", "C", "D", "E", "F", "G"));
    }

    /**
     * Test for checking if there is a valid path from source to destination using DFS.
     * Verifies that the path exists when it is valid.
     */
    @Test
    void testAreConnected_TrueCase() {
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "F"));
    }

    /**
     * Test for checking if an IllegalArgumentException is thrown when the destination vertex does not exist.
     */
    @Test
    void testAreConnected_FalseCase() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "Z"));
    }

    /**
     * Test for checking if an IllegalArgumentException is thrown when the source vertex does not exist.
     */
    @Test
    void testAreConnected_InvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "X", "F"));
    }

    /**
     * Test for checking if an IllegalArgumentException is thrown when the destination vertex does not exist.
     */
    @Test
    void testAreConnected_InvalidDestination() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "X"));
    }

    /**
     * Test for checking if the path is correctly found from source to destination in a small graph.
     */
    @Test
    void testAreConnected_basicConnected_returnsTrue() {
        sources.clear();
        destinations.clear();
        sources.addAll(Arrays.asList("A", "B"));
        destinations.addAll(Arrays.asList("B", "C"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "C"));
    }

    /**
     * Test for checking if the path is found when the source and destination are the same vertex.
     */
    @Test
    void testAreConnected_sameSourceAndDestination_returnsTrue() {
        sources.clear();
        destinations.clear();
        sources.add("A");
        destinations.add("A");
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "A"));
    }

    /**
     * Test for checking if a self-loop correctly returns true when source equals destination.
     */
    @Test
    void testAreConnected_selfLoop_returnsTrue() {
        sources.addAll(Arrays.asList("A", "B", "C"));
        destinations.addAll(Arrays.asList("A", "C", "D"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "A"));
    }

    /**
     * Test for checking if parallel edges correctly return a valid path between source and destination.
     */
    @Test
    void testAreConnected_parallelEdges_returnsTrue() {
        sources.addAll(Arrays.asList("A", "A", "B"));
        destinations.addAll(Arrays.asList("B", "B", "C"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "C"));
    }

    /**
     * Test for checking if the method works for a basic graph with one edge.
     */
    @Test
    void testAreConnected_oneEdgeGraph_returnsTrue() {
        sources.add("A");
        destinations.add("B");
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "B"));
    }

    /**
     * Test for checking the shortest path between two vertices in a graph.
     * Validates that the correct path is returned.
     */
    @Test
    void testShortestPath_ValidPath() {
        List<String> expectedPath = Arrays.asList("A", "C", "E", "G");
        assertEquals(expectedPath, GraphUtility.shortestPath(sources, destinations, "A", "G"));
    }

    /**
     * Test for checking if the shortest path is correctly found in a simple graph.
     */
    @Test
    void testShortestPath_basicPath_returnsCorrectPath() {
        sources.clear();
        destinations.clear();
        sources.addAll(Arrays.asList("A", "B", "C"));
        destinations.addAll(Arrays.asList("B", "C", "D"));
        List<String> expected = Arrays.asList("A", "B", "C", "D");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "D"));
    }

    /**
     * Test for checking if an exception is thrown when no path exists.
     */
    @Test
    void testShortestPath_NoPath() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "Z"));
    }

    /**
     * Test for checking if an exception is thrown when no valid path exists between the source and destination.
     */
    @Test
    void testShortestPath_noPath_throwsException() {
        sources.clear();
        destinations.clear();
        sources.addAll(Arrays.asList("A", "B", "D"));
        destinations.addAll(Arrays.asList("B", "C", "E"));
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "E"));
    }

    /**
     * Test for checking if the method returns a path with just a single edge.
     */
    @Test
    void testShortestPath_oneEdgeGraph_returnsSingleStepPath() {
        sources.add("A");
        destinations.add("B");
        List<String> expectedPath = Arrays.asList("A", "B");
        assertEquals(expectedPath, GraphUtility.shortestPath(sources, destinations, "A", "B"));
    }

    /**
     * Test for checking if the topological sort works on a valid Directed Acyclic Graph (DAG).
     */
    @Test
    void testSort_ValidDAG() {
        List<String> sortedOrder = GraphUtility.sort(sources, destinations);
        assertTrue(sortedOrder.containsAll(sources) && sortedOrder.containsAll(destinations));
    }

    /**
     * Test for checking if an exception is thrown when a cycle exists in the graph.
     */
    @Test
    void testSort_CyclicGraph() {
        List<String> cyclicSources = Arrays.asList("A", "B", "C", "D", "E");
        List<String> cyclicDestinations = Arrays.asList("B", "C", "D", "E", "A"); // Cycle: A -> B -> C -> D -> E -> A
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(cyclicSources, cyclicDestinations));
    }

    /**
     * Test for checking if the topological sort correctly handles a graph with a single vertex.
     */
    @Test
    void sort_singleVertex_returnsSingleVertexList() {
        sources.clear();
        destinations.clear();
        sources.add("A");
        destinations.add("B");

        List<String> result = GraphUtility.sort(sources, destinations);
        assertEquals(Arrays.asList("A", "B"), result);
    }

    /**
     * Test for checking if an empty graph returns an empty list when topologically sorted.
     */
    @Test
    void sort_emptyGraph_returnsEmptyList() {
        sources.clear();
        destinations.clear();
        List<String> result = GraphUtility.sort(sources, destinations);
        assertTrue(result.isEmpty());
    }

    /**
     * Test for checking if the topological sort works correctly for a complex graph with multiple vertices and edges.
     */
    @Test
    void sort_complexSort_returnsCorrectOrder() {
        sources.clear();
        destinations.clear();
        sources.addAll(Arrays.asList("A", "B", "C", "D", "E"));
        destinations.addAll(Arrays.asList("B", "C", "E", "E", "F"));
        List<String> result = GraphUtility.sort(sources, destinations);
        List<String> expected = Arrays.asList("A", "D", "B", "C", "E", "F");
        assertEquals(expected, result);
    }

    /**
     * Test for checking if an exception is thrown when both cyclic and acyclic parts exist in the graph.
     */
    @Test
    void sort_mixedCyclicAndAcyclicGraph_throwsException() {
        sources.addAll(Arrays.asList("A", "B", "C", "D", "E"));
        destinations.addAll(Arrays.asList("B", "C", "D", "E", "A")); // Cycle exists: A -> B -> C -> D -> E -> A
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }

    /**
     * Test for checking if the shortest path works on a larger graph with more vertices.
     */
    @Test
    void shortestPath_largeGraph_returnsCorrectPath() {
        sources.clear();
        destinations.clear();
        sources.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
        destinations.addAll(Arrays.asList("B", "C", "D", "E", "F", "G", "H", "I"));
        List<String> expected = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "I"));
    }

    /**
     * Test for checking if an exception is thrown when the source and destination lists are of different sizes.
     */
    @Test
    void sort_differentSizedLists_throwsException() {
        sources.clear();
        destinations.clear();
        sources.add("A");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }
}
