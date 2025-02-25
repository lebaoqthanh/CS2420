package assign05;

import timing.TimingExperiment;
import java.util.ArrayList;
import timing.ArrayListGenerator;

/**
 * Experiment to measure the running time of merge sort on nearly ascending data.
 */
public class MergeSortNearlyAscendingTimingExperiment extends TimingExperiment {

     static final String problemSizeDescription = "List size";
     static final int problemSizeMin = 1000;
     static final int problemSizeCount = 20;
     static final int problemSizeStep = 1000;
     static final int experimentIterationCount = 100;

     ArrayList<Integer> list;
    protected static int threshold = 100;
     MergeSorter mergeSorter = new MergeSorter(threshold);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MergeSortNearlyAscendingTimingExperiment();

        System.out.println("\n--- Computing Timing Results for Merge Sort on Nearly Ascending Data ---\n");
        System.out.println("Problem Size (n)\tMedian Time (ns)");
        timingExperiment.printResults();
    }

    public MergeSortNearlyAscendingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        list = ArrayListGenerator.generateNearlyAscendingArrayList(problemSize);
    }

    @Override
    protected void runComputation() {
        mergeSorter.sort(list);
    }
}
