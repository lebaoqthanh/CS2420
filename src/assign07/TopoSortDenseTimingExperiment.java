package assign07;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Experiment to measure the running time of topological sort for random acyclic
 * dense graphs with a range of vertex counts.
 *
 * @author Thanh Le and Junhee Choi
 * @version February 27, 2025
 */
public class TopoSortDenseTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "vertexCount";
    private static int problemSizeMin = 100; // Adjusted for dense graphs
    private static int problemSizeCount = 10; // Adjusted for dense graphs
    private static int problemSizeStep = 50; // Adjusted for dense graphs
    private static int experimentIterationCount = 1000; // Adjusted for dense graphs
    private final Random rng = new Random();
    private Graph<Integer> acyclicDenseGraph;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TopoSortDenseTimingExperiment();
        timingExperiment.printResults();
    }

    public TopoSortDenseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount,
                problemSizeStep, experimentIterationCount);
    }

    /**
     * Setup experiment for problemSize by generating a dense acyclic graph.
     *
     * @param problemSize - number of vertices
     */
    protected void setupExperiment(int problemSize) {
        acyclicDenseGraph = new Graph<Integer>();

        // Add vertices
        for (int i = 0; i < problemSize; i++) {
            acyclicDenseGraph.addVertex(i);
        }

        // Add edges to create a dense acyclic graph
        for (int i = 0; i < problemSize; i++) {
            for (int j = i + 1; j < problemSize; j++) {
                acyclicDenseGraph.addEdge(i, j);
            }
        }
    }

    /**
     * Run the topoSort method.
     */
    protected void runComputation() {
        acyclicDenseGraph.topoSort();
    }
}