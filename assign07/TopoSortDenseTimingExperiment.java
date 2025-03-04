package assign07;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Experiment to measure the running time of topological sort for dense acyclic graphs.
 *
 * This experiment generates dense directed acyclic graphs (DAGs) by connecting each vertex
 * to many others in a way that avoids cycles. The number of edges is significantly larger
 * than in the sparse version.
 *
 * @author CS 2420
 * @version March 3, 2025
 */
public class TopoSortDenseTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "vertexCount";
    private static int problemSizeMin = 500; // Smaller starting size to avoid memory issues
    private static int problemSizeCount = 8; // Fewer test cases due to high edge density
    private static int problemSizeStep = 500; // Step by 500 vertices
    private static int experimentIterationCount = 10; // Fewer iterations for performance

    private final Random rng = new Random();
    private Graph<Integer> denseAcyclicGraph;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TopoSortDenseTimingExperiment();
        timingExperiment.printResults();
    }

    public TopoSortDenseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Setup experiment for problemSize by generating a dense acyclic graph.
     *
     * @param problemSize - number of vertices
     */
    protected void setupExperiment(int problemSize) {
        denseAcyclicGraph = new Graph<>();

        // Add vertices 0 to (problemSize - 1)
        for (int i = 0; i < problemSize; i++) {
            denseAcyclicGraph.add(i);
        }

        // Add dense edges while ensuring acyclicity
        for (int i = 0; i < problemSize; i++) {
            for (int j = i + 1; j < problemSize; j++) { // Only forward edges
                if (rng.nextDouble() < 0.7) { // 70% chance of adding an edge (dense but not fully connected)
                    denseAcyclicGraph.addEdge(i, j);
                }
            }
        }
    }

    /**
     * Run the topoSort method.
     */
    protected void runComputation() {
        denseAcyclicGraph.topoSort();
    }
}
