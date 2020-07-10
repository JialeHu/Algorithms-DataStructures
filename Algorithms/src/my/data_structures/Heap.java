package my.data_structures;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E> {

    private final ArrayList<E> array;
    private final Comparator<? super E> comparator;

    public Heap() {
	this.array = new ArrayList<E>();
	this.comparator = null;
    }
    
    public Heap(Comparator<? super E> comparator) {
	this.array = new ArrayList<E>();
	this.comparator = comparator;
    }

    public void insert(E e) {
	array.add(e);
	int idx = array.size() - 1;
	int parentIdx = idx / 2;
	while (compare(array.get(parentIdx), e) > 0) {
	    swap(idx, parentIdx);
	    idx = parentIdx;
	    parentIdx = idx / 2;
	}
    }

    public E peek() {
	if (array.isEmpty())
	    throw new IllegalArgumentException("Heap is empty.");
	return array.get(0);
    }

    public E poll() {
	if (array.isEmpty())
	    throw new IllegalArgumentException("Heap is empty.");
	E min = array.get(0);
	int len = array.size();
	if (len == 1) {
	    array.clear();
	    return min;
	}
	// Swap First with Last
	swap(0, len - 1);
	array.remove(len - 1);
	len--;
	int i = 0;
	while ((2 * i < len && compare(array.get(i), array.get(2 * i)) > 0)
		|| (2 * i + 1 < len && compare(array.get(i), array.get(2 * i + 1)) > 0)) {
	    // Swap with Smaller Child
	    if (2 * i + 1 >= len || compare(array.get(2 * i), array.get(2 * i + 1)) < 0) {
		swap(i, 2 * i);
		i = 2 * i;
	    } else {
		swap(i, 2 * i + 1);
		i = 2 * i + 1;
	    }
	}
	return min;
    }

    public boolean isEmpty() {
	return array.isEmpty();
    }

    @Override
    public String toString() {
	String str = "-----\n";
	int col = 1;
	for (int i = 0; i < array.size(); i++) {
	    str += array.get(i);
	    if (i == col - 1 || i == array.size() - 1) {
		str += "\n";
		col += 2 * col;
	    }
	}
	str += "-----";
	return str;
    }

    private void swap(int idx1, int idx2) {
	E temp = array.get(idx1);
	array.set(idx1, array.get(idx2));
	array.set(idx2, temp);
    }
    
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
	if (comparator == null) {
	    return ((Comparable<? super E>) e1).compareTo(e2);
	} else {
	    return comparator.compare(e1, e2);
	}
    }

}
