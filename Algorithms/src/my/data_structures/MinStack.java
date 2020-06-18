package my.data_structures;

import java.io.Serializable;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This {@code MinStack} class extends {@link Stack} and maintain the 
 * minimum element in the {@code MinStack} at any time.
 * 
 * 
 * @author Jiale Hu
 *
 */
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
    
    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param   item   the item to be pushed onto this stack.
     * @return  the {@code item} argument.
     * @see     java.util.Vector#addElement
     */
    @Override
    public E push(E item) {
    	if (comparator == null) {
    		if (mins.isEmpty() || comparable(item, getMin()) <= 0) mins.push(item);
    	} else {
    		if (mins.isEmpty() || comparator(item, getMin()) <= 0) mins.push(item);
    	}
        return super.push(item);
    }
    
    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return  The object at the top of this stack (the last item
     *          of the {@code Vector} object).
     * @throws  EmptyStackException  if this stack is empty.
     */
    @Override
    public synchronized E pop() {
    	E e = super.pop();
    	if (comparator == null) {
    		if (comparable(e, getMin()) == 0) mins.pop();
    	} else {
    		if (comparator(e, getMin()) == 0) mins.pop();
    	}
        return e;
    }
    
    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack (the last item
     *          of the {@code Vector} object).
     * @throws  EmptyStackException  if this stack is empty.
     */
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
    
    @Override
    public String toString() {
		return super.toString() + " Min: " + getMin();
    }
    
    @SuppressWarnings("unchecked")
	private int comparable(E input, E curMin) {
    	return ((Comparable<? super E>) input).compareTo(curMin);
    }
    
    private int comparator(E input, E curMin) {
    	return comparator.compare(input, curMin);
    }
}

