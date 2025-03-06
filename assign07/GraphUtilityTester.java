package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilityTest {

    private List<String> sources;
    private List<String> destinations;

    @BeforeEach
    void setUp() {
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
    }

    @Test
    void areConnected_basicConnected_returnsTrue() {
        sources.addAll(Arrays.asList("A", "B"));
        destinations.addAll(Arrays.asList("B", "C"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "C"));
    }

    @Test
    void areConnected_basicDisconnected_returnsFalse() {
        sources.addAll(Arrays.asList("A", "B", "D"));
        destinations.addAll(Arrays.asList("B", "C", "E"));
        assertFalse(GraphUtility.areConnected(sources, destinations, "A", "E"));
    }

    @Test
    void areConnected_sameSourceAndDestination_returnsTrue() {
        sources.add("A");
        destinations.add("A");
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "A"));
    }

    @Test
    void areConnected_sourceOrDestinationNotFound_throwsException() {
        sources.add("A");
        destinations.add("B");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "C", "B"));
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "C"));
    }

    @Test
    void areConnected_differentSizedLists_throwsException() {
        sources.add("A");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "B"));
    }

    @Test
    void shortestPath_basicPath_returnsCorrectPath() {
        sources.addAll(Arrays.asList("A", "B", "C"));
        destinations.addAll(Arrays.asList("B", "C", "D"));
        List<String> expected = Arrays.asList("A", "B", "C", "D");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "D"));
    }

    @Test
    void shortestPath_noPath_throwsException() {
        sources.addAll(Arrays.asList("A", "B", "D"));
        destinations.addAll(Arrays.asList("B", "C", "E"));
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "E"));
    }

    @Test
    void shortestPath_sourceOrDestinationNotFound_throwsException() {
        sources.add("A");
        destinations.add("B");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "C", "B"));
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "C"));
    }

    @Test
    void shortestPath_differentSizedLists_throwsException() {
        sources.add("A");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "B"));
    }

    @Test
    void sort_basicSort_returnsCorrectOrder() {
        sources.addAll(Arrays.asList("A", "B", "C", "D"));
        destinations.addAll(Arrays.asList("B", "C", "D", "E"));
        List<String> result = GraphUtility.sort(sources, destinations);
        List<String> expected = Arrays.asList("A", "B", "C", "D", "E");
        assertEquals(expected, result);
    }

    @Test
    void sort_complexSort_returnsCorrectOrder() {
        sources.addAll(Arrays.asList("A", "B", "C", "D", "E"));
        destinations.addAll(Arrays.asList("B", "C", "E", "E", "F"));
        List<String> result = GraphUtility.sort(sources, destinations);
        List<String> expected = Arrays.asList("A", "D", "B", "C", "E", "F");
        assertEquals(expected, result);
    }

    @Test
    void sort_cycle_throwsException() {
        sources.addAll(Arrays.asList("A", "B", "C"));
        destinations.addAll(Arrays.asList("B", "C", "A"));
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }

    @Test
    void sort_differentSizedLists_throwsException() {
        sources.add("A");
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }

    // Additional Edge Case Tests
    @Test
    void areConnected_emptyGraph_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "B"));
    }

    @Test
    void areConnected_singleVertex_returnsTrue() {
        sources.add("A");
        destinations.add("A");
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "A"));
    }

    @Test
    void areConnected_multipleDisconnectedComponents_returnsFalse() {
        sources.addAll(Arrays.asList("A", "C"));
        destinations.addAll(Arrays.asList("B", "D"));
        assertFalse(GraphUtility.areConnected(sources, destinations, "A", "D"));
    }

    @Test
    void areConnected_complexGraph_returnsCorrectResult() {
        sources.addAll(Arrays.asList("A", "A", "B", "C", "D", "E"));
        destinations.addAll(Arrays.asList("B", "C", "D", "E", "F", "A"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "F"));
        assertFalse(GraphUtility.areConnected(sources, destinations, "F", "B"));
    }

    @Test
    void shortestPath_emptyGraph_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "B"));
    }

    @Test
    void shortestPath_singleVertex_returnsCorrectPath() {
        sources.add("A");
        destinations.add("A");
        List<String> expected = Arrays.asList("A");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "A"));
    }

    @Test
    void shortestPath_multiplePaths_returnsShortest() {
        sources.addAll(Arrays.asList("A", "A", "B", "C"));
        destinations.addAll(Arrays.asList("B", "C", "C", "D"));
        List<String> expected = Arrays.asList("A", "C", "D");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "D"));
    }

    @Test
    void shortestPath_largeGraph_returnsCorrectPath() {
        sources.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
        destinations.addAll(Arrays.asList("B", "C", "D", "E", "F", "G", "H", "I"));
        List<String> expected = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        assertEquals(expected, GraphUtility.shortestPath(sources, destinations, "A", "I"));
    }

    @Test
    void sort_emptyGraph_returnsEmptyList() {
        List<String> result = GraphUtility.sort(sources, destinations);
        assertTrue(result.isEmpty());
    }

    @Test
    void sort_singleVertex_returnsSingleVertexList() {
        sources.add("A");
        destinations.add("B");
        List<String> result = GraphUtility.sort(sources, destinations);
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }
}