package assign05;

import java.util.ArrayList;

/**
 * Implements the Merge Sort algorithm with an adaptive threshold for switching to
 * insertion sort on small sublists. Uses an optimized auxiliary array for efficiency.
 *
 * @param <T> The type of elements to be sorted, which must be Comparable.
 * @author Thanh Le and Junhee Choi
 * @version 20/2/2025
 */
public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {
    private int threshold;

    /**
     * Constructs a MergeSorter with a given threshold.
     *
     * @param threshold The size of sublists at which the sorting algorithm switches to insertion sort.
     * @throws IllegalArgumentException if the threshold is less than 1.
     */
    public MergeSorter(int threshold) {
        if (threshold < 1) {
            throw new IllegalArgumentException("Threshold must be positive.");
        }
        this.threshold = threshold;
    }

    /**
     * Sorts the given list using the Merge Sort algorithm with insertion sort for small sublists.
     *
     * @param list The ArrayList to be sorted.
     */
    @Override
    public void sort(ArrayList<T> list) {
        if (list == null || list.size() < 2)
            return;

        ArrayList<T> tempArr = new ArrayList<>(list);
        mergeSort(list, tempArr, 0, list.size() - 1);
    }

    /**
     * Recursively applies Merge Sort to divide and conquer the list.
     *
     * @param list     The original ArrayList being sorted.
     * @param tempArr  A temporary ArrayList used for merging.
     * @param left     The left index of the current sublist.
     * @param right    The right index of the current sublist.
     */
    private void mergeSort(ArrayList<T> list, ArrayList<T> tempArr, int left, int right) {
        int sublistSize = right - left + 1;

        // If the sublist size is smaller than the threshold, use insertion sort
        if (sublistSize <= threshold) {
            insertionSort(list, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(list, tempArr, left, mid);
        mergeSort(list, tempArr, mid + 1, right);

        merge(list, tempArr, left, mid, right);
    }

    /**
     * Performs an insertion sort on a small sublist.
     *
     * @param list  The ArrayList to be sorted.
     * @param left  The left index of the sublist.
     * @param right The right index of the sublist.
     */
    private void insertionSort(ArrayList<T> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= left && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    /**
     * Merges two sorted sublists back into the original list.
     *
     * @param list     The original ArrayList being sorted.
     * @param tempArr  A temporary ArrayList used for merging.
     * @param left     The left index of the first sublist.
     * @param mid      The midpoint index separating the two sublists.
     * @param right    The right index of the second sublist.
     */
    private void merge(ArrayList<T> list, ArrayList<T> tempArr, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            tempArr.set(i, list.get(i));
        }

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (tempArr.get(i).compareTo(tempArr.get(j)) <= 0) {
                list.set(k++, tempArr.get(i++));
            }
            else {
                list.set(k++, tempArr.get(j++));
            }
        }

        while (i <= mid) {
            list.set(k++, tempArr.get(i++));
        }

        while (j <= right) {
            list.set(k++, tempArr.get(j++));
        }
    }
}
