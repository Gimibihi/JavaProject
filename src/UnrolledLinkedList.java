import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UnrolledLinkedList<T> implements Comparator<T>, UnrolledLinkedListADT<T> {

    private Node start;
    private Node current;
    private int NUMBER_OF_NODES;
    private int size;

    /**
     * Constructor which initializes the LinkedList with a set node Array size
     *
     * @param capacity maksimum Node Array size
     */
    public UnrolledLinkedList(int capacity) {
        start = null;
        current = null;
        size = capacity + 1;
        NUMBER_OF_NODES = 0;
    }

    /**
     * Method for adding data to the list from the start
     *
     * @param data the data you add
     */
    public void add(T data) {
        if (start == null) {
            start = new Node(size);
            start.data.add(data);
            start.amount++;
            current = start;
            NUMBER_OF_NODES++;
            return;
        }
        if (current.amount + 1 < size) {
            current.data.add(data);
            current.amount++;
        } else {
            Node temp = new Node(size);
            temp.data.add(data);
            temp.previuos = current;
            current.next = temp;
            current = current.next;
            current.amount++;
            NUMBER_OF_NODES++;
        }
    }

    /**
     * Method which lets you add data in a specific point of the list
     *
     * @param data  the data you add
     * @param index the place you want it added to
     */
    public void insert(T data, int index) {
        if (index < 0 || index > size * NUMBER_OF_NODES) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size * NUMBER_OF_NODES);
        }

        current = start;
        int currentSize = 0;

        while (current != null) {
            int elementsInNode = current.amount;

            if (index <= currentSize + elementsInNode) {
                if (current.amount + 1 <= size) {
                    // If there is space in the current node, simply insert the data
                    current.data.add(index - currentSize, data);
                    current.amount++;
                } else {
                    // If there is no space, split the node and insert the data
                    Node newNode = new Node(size);

                    // Move half of the data to the new node
                    int mid = current.amount / 2;
                    for (int i = mid; i < current.amount; i++) {
                        newNode.data.add(current.data.get(i));
                        newNode.amount++;
                    }
                    current.amount -= newNode.amount;

                    // Adjust links
                    newNode.next = current.next;
                    newNode.previuos = current;
                    if (current.next != null) {
                        current.next.previuos = newNode;
                    }
                    current.next = newNode;

                    // Insert the data into the appropriate node
                    if (index < currentSize + elementsInNode) {
                        current.data.add(index - currentSize, data);
                        current.amount++;
                    } else {
                        newNode.data.add(index - currentSize - elementsInNode, data);
                        newNode.amount++;
                    }
                }
                return;
            }

            currentSize += elementsInNode;
            current = current.next;
        }

        throw new IndexOutOfBoundsException("Bad index Idiot");
    }

    /**
     * Clears the list of data
     */
    public void clear() {
        current = null;
        start = null;
        NUMBER_OF_NODES = 0;
    }

    /**
     * Checks if the list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return start == null;
    }

    /**
     * Gets the size of the List
     *
     * @return List size
     */
    public int getSize() {
        return NUMBER_OF_NODES;
    }

    /**
     * Checks if your given element is in the list
     *
     * @param data element
     * @return True if it's in the list
     */
    public boolean contains(T data) {
        current = start;
        while (current != null) {
            if (current.data.contains(data)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Removes element at a specified place of the list
     *
     * @param index elemnt place
     */
    public void remove(int index) {
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        current = start;
        int currentSize = 0;

        while (current != null) {
            int elementsInNode = current.amount;
            if (index < currentSize + elementsInNode) {
                current.data.remove(index - currentSize);
                current.amount--;
                return;
            }
            currentSize += elementsInNode;
            current = current.next;
        }

    }

    /**
     * If the element is in the list gets its index
     *
     * @param data element
     * @return index of element
     */
    public int indexOf(T data) {
        current = start;
        int Nindex = 0;
        int index = -1;
        while (current != null) {
            int n;
            if ((n = current.data.indexOf(data)) > 0) index = Nindex * size + current.data.indexOf(data);
            current = current.next;
            Nindex++;
        }
        return index;
    }

    /**
     * Gets the specified element from the list
     *
     * @param index index of the element
     * @return specified element
     */
    public T get(int index) {
        if (index < 0 || isEmpty()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        current = start;
        int currentSize = 0;

        while (current != null) {
            int elementsInNode = current.amount;
            if (index < currentSize + elementsInNode) return current.data.get((index - currentSize));
            currentSize += elementsInNode;
            current = current.next;
        }

        throw new IndexOutOfBoundsException("Bad index Idiot");
    }

    /**
     * Compares two elemenets
     *
     * @param o1   element one
     * @param o2   element two
     * @param comp comparitor
     * @return comparison
     */
    public int compare(T o1, T o2, Comparator<T> comp) {
        return comp.compare(o1, o2);
    }

    /**
     * Sorts the whole List
     *
     * @param comp comparitor by what to sort
     */
    public void sort(Comparator<T> comp) {
        current = start;

        while (current != null) {
            try {
                current.data.sort(comp);
                current = current.next;
            } catch (Exception e) {
                System.out.println("Nurodykite Komparatoriu");
                return;
            }
        }

        // After sorting individual nodes, you might want to rearrange the nodes
        // based on their contents. This step is necessary if you want the entire
        // list to be sorted.
        rearrangeNodes(comp);
    }

    /**
     * Rearanges Nodes so that the whole list is sorted
     *
     * @param comp comparitor by which to sort
     */
    private void rearrangeNodes(Comparator<T> comp) {
        if (start == null || start.next == null) {
            // Nothing to rearrange if the list is empty or has only one node.
            return;
        }

        // Collect all elements in a single ArrayList
        ArrayList<T> allElements = new ArrayList<>();
        current = start;

        while (current != null) {
            allElements.addAll(current.data);
            current = current.next;
        }

        // Sort the entire list of elements
        allElements.sort(comp);
        // Reconstruct the linked list with the sorted elements
        start = new Node(size);
        current = start;
        int count = 0;

        for (T element : allElements) {
            if (count == size) {
                Node newNode = new Node(size);
                current.next = newNode;
                newNode.previuos = current;
                current = newNode;
                count = 0;
            }

            current.data.add(element);
            current.amount++;
            count++;
        }

        // Update the number of nodes
        NUMBER_OF_NODES = (count == 0) ? 0 : 1;

        // Update the links of the last node
        if (current.amount == 0 && current.previuos != null) {
            current.previuos.next = null;
        }
    }

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }

    protected class Node {
        Node next;
        Node previuos;
        ArrayList<T> data;
        int amount;

        public Node(int n) {
            next = null;
            previuos = null;
            amount = 0;
            data = new ArrayList<>(n);
        }
    }
}
