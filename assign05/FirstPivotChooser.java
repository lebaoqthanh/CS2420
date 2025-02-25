package assign05;

import java.util.ArrayList;

/**
 * Implements a pivot selection strategy that always selects the first element
 * of the given sublist as the pivot. This approach is simple but may lead to
 * poor performance in cases where the list is already sorted or reverse sorted.
 *
 * @param <E> Type of elements, which must be Comparable.
 * @Thanh Le and Junhee Choi
 * @version 20/2/2025
 */
public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

    /**
     * Returns the index of the first element in the given sublist as the pivot.
     *
     * @param list       The ArrayList from which to select a pivot.
     * @param leftIndex  The left boundary index of the sublist.
     * @param rightIndex The right boundary index of the sublist (unused in this strategy).
     * @return The index of the first element as the pivot.
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
        return leftIndex;
    }
}
