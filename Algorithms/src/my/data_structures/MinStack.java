package my.data_structures;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Stack;

public class MinStack<E> extends Stack<E> implements Serializable {

	private static final long serialVersionUID = 5987730602286369600L;
	
	private final Stack<E> mins;
    private final Comparator<? super E> comparator;
    
    public MinStack() {
    	super();
        this.mins = new Stack<E>();
        this.comparator = null;
    }
    
    public MinStack(Comparator<? super E> comparator) {
    	this.mins = new Stack<E>();
    	this.comparator = comparator;
    }
    
    @Override
    public E push(E e) {
    	if (comparator == null) {
    		if (mins.isEmpty() || comparable(e, getMin()) <= 0) mins.push(e);
    	} else {
    		if (mins.isEmpty() || comparator(e, getMin()) <= 0) mins.push(e);
    	}
        return super.push(e);
    }
    
    @Override
    public E pop() {
    	E e = super.pop();
    	if (comparator == null) {
    		if (comparable(e, getMin()) == 0) mins.pop();
    	} else {
    		if (comparator(e, getMin()) == 0) mins.pop();
    	}
        return e;
    }
    
    @Override
    public E peek() {
        return super.peek();
    }
    
    /**
     * Returns the minimum element in this stack at the time.
     * 
     * @return the minimum element in this stack according to the 
     * comparator, or {@code null} if this stack is sorted according 
     * to the natural ordering of its elements
     */
    public E getMin() {
        return mins.peek();
    }
    
    /**
     * Returns the comparator used to order the elements in this
     * stack, or {@code null} if this stack is sorted according to
     * the {@linkplain Comparable natural ordering} of its elements.
     *
     * @return the comparator used to order this stack, or
     *         {@code null} if this stack is sorted according to the
     *         natural ordering of its elements
     */
    public Comparator<? super E> comparator() {
        return comparator;
    }
    
    @SuppressWarnings("unchecked")
	private int comparable(E input, E curMin) {
    	return ((Comparable<? super E>) input).compareTo(curMin);
    }
    
    private int comparator(E input, E curMin) {
    	return comparator.compare(input, curMin);
    }
}

