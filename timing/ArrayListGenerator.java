package timing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class contains methods for generating ArrayLists of various sizes and orderings.
 * Adapted from ArrayGenerator.java.
 *
 * @author CS 2420 course staff
 * @version [Current Date]
 */
public class ArrayListGenerator {

    private static final Random rng = new Random();

    /**
     * Generates an ArrayList with problemSize random integers in nearly ascending order.
     *
     * @param problemSize - size of the list
     * @return an ArrayList with nearly sorted integers
     */
    public static ArrayList<Integer> generateNearlyAscendingArrayList(int problemSize) {
        ArrayList<Integer> list = generateAscendingArrayList(problemSize);
        slightlyShuffleList(list);
        return list;
    }

    /**
     * Generates an ArrayList with problemSize random integers in a permuted order.
     *
     * @param problemSize - size of the list
     * @return an ArrayList with shuffled integers
     */
    public static ArrayList<Integer> generatePermutedArrayList(int problemSize) {
        ArrayList<Integer> list = generateAscendingArrayList(problemSize);
        Collections.shuffle(list);
        return list;
    }

    /**
     * Generates an ArrayList with problemSize random integers in descending order.
     *
     * @param problemSize - size of the list
     * @return an ArrayList with integers sorted in descending order
     */
    public static ArrayList<Integer> generateDescendingArrayList(int problemSize) {
        ArrayList<Integer> list = generateAscendingArrayList(problemSize);
        list.sort(Collections.reverseOrder());
        return list;
    }

    /**
     * Generates an ArrayList with problemSize random integers in ascending order.
     *
     * @param problemSize - size of the list
     * @return an ArrayList with sorted integers in ascending order
     */
    private static ArrayList<Integer> generateAscendingArrayList(int problemSize) {
        ArrayList<Integer> list = new ArrayList<>(problemSize);
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            list.add(currentElement);
            currentElement += rng.nextInt(10);
        }
        return list;
    }

    /**
     * Slightly shuffles the contents of the given list to create a nearly sorted order.
     *
     * @param list to be shuffled slightly
     */
    private static void slightlyShuffleList(ArrayList<Integer> list) {
        if (list.size() < 2) return;

        int swapCount = Math.min(5 + rng.nextInt(15), list.size() / 2);
        for (int i = 0; i < swapCount; i++) {
            int idx1 = rng.nextInt(Math.max(1, list.size() - 11));
            int idx2 = idx1 + 1 + rng.nextInt(Math.min(10, list.size() - idx1 - 1));
            Collections.swap(list, idx1, idx2);
        }
    }
}
