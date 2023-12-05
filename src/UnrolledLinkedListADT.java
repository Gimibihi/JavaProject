import java.util.Comparator;

public interface UnrolledLinkedListADT<T> {
    /**
     * Method for adding data to the list from the start
     *
     * @param data the data you add
     */
    void add(T data);

    /**
     * Clears the list of data
     */
    void clear();

    /**
     * Checks if the list is empty
     *
     * @return true if list is empty
     */
    boolean isEmpty();

    /**
     * Gets the size of the List
     *
     * @return List size
     */
    int getSize();

    /**
     * Checks if your given element is in the list
     *
     * @param data element
     * @return True if it's in the list
     */
    boolean contains(T data);

    /**
     * Removes element at a specified place of the list
     *
     * @param n elemnt place
     */
    void remove(int n);

    /**
     * If the element is in the list gets its index
     *
     * @param data element
     * @return index of element
     */
    int indexOf(T data);

    /**
     * Gets the specified element from the list
     *
     * @param n index of the element
     * @return specified element
     */
    T get(int n);

    /**
     * Sorts the whole List
     *
     * @param comparator comparitor by what to sort
     */
    void sort(Comparator<T> comparator);

    int compare(T o1, T o2, Comparator<T> comparator);

    /**
     * Method which lets you add data in a specific point of the list
     *
     * @param data  the data you add
     * @param index the place you want it added to
     */
    void insert(T data, int index);

}
