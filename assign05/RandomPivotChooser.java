package assign05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements a pivot selection strategy that chooses a pivot randomly from the given sublist.
 * This approach helps to reduce the chances of encountering the worst-case scenario in QuickSort
 * by ensuring that the pivot selection is not biased towards any particular order.
 *
 * @param <E> Type of elements, which must be Comparable.
 * @author Thanh Le and Junhee Choi
 * @version 20/2/2025
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

    private final Random rand = new Random();

    /**
     * Selects a random pivot index within the given sublist.
     *
     * @param list       The ArrayList from which to select a pivot.
     * @param leftIndex  The left boundary index of the sublist.
     * @param rightIndex The right boundary index of the sublist.
     * @return A randomly selected index within the specified range.
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
        return rand.nextInt(rightIndex - leftIndex + 1) + leftIndex;
    }
}
