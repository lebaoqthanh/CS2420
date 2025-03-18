package assign07;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Experiment to measure the running time of depth-first search (DFS) for random sparse graphs.
 *
 * @author Thanh Le and CS 2420 course staff
 * @version March 6, 2025
 */
public class DFSSparseTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "vertexCount";
    private static int problemSizeMin = 5000;
    private static int problemSizeCount = 11;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 1000;
    private final Random rng = new Random();
    private Graph<Integer> randomGraph;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new DFSSparseTimingExperiment();
        timingExperiment.printResults();
    }

    public DFSSparseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount,
                problemSizeStep, experimentIterationCount);
    }

    /**
     * Setup experiment for problemSize by generating a sparse random graph.
     *
     * @param problemSize - number of vertices
     */
    protected void setupExperiment(int problemSize) {
        // V = {0, 1, ..., problemSize - 1}
        randomGraph = new Graph<Integer>();

        // Randomly generate edges for the graph
        for (int i = 0; i < 2 * problemSize; i++) {
            int sourceVertexData = rng.nextInt(problemSize);
            int destinationVertexData = rng.nextInt(problemSize);
            if (sourceVertexData != destinationVertexData) {
                randomGraph.addEdge(sourceVertexData, destinationVertexData);
            }
        }
    }

    /**
     * Run the DFS computation.
     */
    protected void runComputation() {
        // Pick two random vertices to serve as the source and destination
        int sourceVertex = rng.nextInt(randomGraph.getVertices().size());
        int destinationVertex = rng.nextInt(randomGraph.getVertices().size());

        // Perform DFS from the source to the destination
        randomGraph.depthFirstSearch(sourceVertex, destinationVertex);
    }
}
