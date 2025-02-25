package assign05;

import timing.TimingExperiment;
import java.util.ArrayList;
import timing.ArrayListGenerator;

/**
 * Experiment to measure the running time of merge sort on shuffled data.
 */
public class MergeSortShuffledTimingExperiment extends TimingExperiment {

    private static final String problemSizeDescription = "List size";
    private static final int problemSizeMin = 1000;
    private static final int problemSizeCount = 10;
    private static final int problemSizeStep = 1000;
    private static final int experimentIterationCount = 100;

     ArrayList<Integer> list;

     MergeSorter mergeSorter = new MergeSorter(2000);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MergeSortShuffledTimingExperiment();

        System.out.println("\n--- Computing Timing Results for Merge Sort on Shuffled Data ---\n");
        System.out.println("Problem Size (n)\tMedian Time (ns)");
        timingExperiment.printResults();
    }

    public MergeSortShuffledTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        list = ArrayListGenerator.generatePermutedArrayList(problemSize);
    }

    @Override
    protected void runComputation() {
        mergeSorter.sort(list);
    }
}