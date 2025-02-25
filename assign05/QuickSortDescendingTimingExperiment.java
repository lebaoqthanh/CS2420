package assign05;

import timing.TimingExperiment;
import java.util.ArrayList;
import timing.ArrayListGenerator;

/**
 * Experiment to measure the running time of quicksort on descending ordered data.
 */
public class QuickSortDescendingTimingExperiment extends TimingExperiment {

     static final String problemSizeDescription = "List size";
     static final int problemSizeMin = 1000;
     static final int problemSizeCount = 20;
     static final int problemSizeStep = 1000;
     static final int experimentIterationCount = 100;

     ArrayList<Integer> list;
    protected static PivotChooser pivotChooser = new RandomPivotChooser();
     QuickSorter quickSorter = new QuickSorter(pivotChooser);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new QuickSortDescendingTimingExperiment();

        System.out.println("\n--- Computing Timing Results for QuickSort on Descending Ordered Data ---\n");
        System.out.println("Problem Size (n)\tMedian Time (ns)");
        timingExperiment.printResults();
    }

    public QuickSortDescendingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        list = ArrayListGenerator.generateDescendingArrayList(problemSize);
    }

    @Override
    protected void runComputation() {
        quickSorter.sort(list);
    }
}
