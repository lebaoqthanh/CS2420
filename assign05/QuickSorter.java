package assign05;

import java.util.ArrayList;

/**
 * Implements the QuickSort algorithm using a pivot selection strategy.
 *
 * @param <E> The type of elements in the list, which must be Comparable.
 * @author Thanh Le and Junhee Choi
 * @version 20/2/2025
 */
public class QuickSorter<E extends Comparable<? super E>> implements Sorter<E> {
    private PivotChooser<E> pivotChooser;

    /**
     * Constructs a QuickSorter with a specified pivot selection strategy.
     *
     * @param chooser The PivotChooser implementation that determines how the pivot is selected.
     */
    public QuickSorter(PivotChooser<E> chooser) {
        this.pivotChooser = chooser;
    }

    /**
     * Sorts the given list using the QuickSort algorithm.
     *
     * @param arr The ArrayList to be sorted.
     */
    @Override
    public void sort(ArrayList<E> arr) {
        if (arr == null || arr.size() <= 1) {
            return;
        }
        quickSort(arr, 0, arr.size() - 1);
    }

    /**
     * Recursively applies QuickSort to partition and sort the array.
     *
     * @param array The ArrayList being sorted.
     * @param low   The starting index of the sublist.
     * @param high  The ending index of the sublist.
     */
    private void quickSort(ArrayList<E> array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Swaps two elements in the array if they are different to avoid unnecessary operations.
     *
     * @param array The ArrayList containing the elements.
     * @param i     The index of the first element.
     * @param j     The index of the second element.
     */
    private void swap(ArrayList<E> array, int i, int j) {
        if (i != j) { // Avoid unnecessary swaps
            E temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }

    /**
     * Partitions the array around the pivot, ensuring elements smaller than the pivot are on the left
     * and larger elements are on the right.
     *
     * @param array The ArrayList being partitioned.
     * @param low   The starting index of the partition range.
     * @param high  The ending index of the partition range.
     * @return The final index of the pivot after partitioning.
     */
    private int partition(ArrayList<E> array, int low, int high) {
        int pivotIndex = pivotChooser.getPivotIndex(array, low, high);
        E pivotValue = array.get(pivotIndex);

        // Move pivot to the end (if it's not already there)
        swap(array, pivotIndex, high);

        int i = low; // Start from low instead of low - 1 to avoid unnecessary swaps
        for (int j = low; j < high; j++) {
            if (array.get(j).compareTo(pivotValue) < 0) { // Keep < for efficiency
                swap(array, i, j);
                i++;
            }
        }

        // Move pivot back to its correct position
        swap(array, i, high);
        return i; // The final pivot position
    }
}
