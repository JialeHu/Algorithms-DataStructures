package my.data_structures;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * This {@code MinStack} class extends {@link Stack} and maintains the minimum
 * element in the {@code MinStack}.
 * 
 * This class does NOT support methods in {@code Vector} that modify the data in
 * this stack.
 * 
 * @author Jiale Hu
 *
 */
public class MinStack<E> extends Stack<E> implements Serializable {

    private static final long serialVersionUID = 5987730602286369600L;

    private Stack<E> mins;
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
     * Pushes an item onto the top of this stack. This has exactly the same effect
     * as: <blockquote>
     * 
     * <pre>
     * addElement(item)
     * </pre>
     * 
     * </blockquote>
     *
     * @param item the item to be pushed onto this stack.
     * @return the {@code item} argument.
     * @see java.util.Vector#addElement
     */
    @Override
    public E push(E item) {
	if (comparator == null) {
	    if (mins.isEmpty() || comparable(item, getMin()) <= 0)
		mins.push(item);
	} else {
	    if (mins.isEmpty() || comparator(item, getMin()) <= 0)
		mins.push(item);
	}
	return super.push(item);
    }

    /**
     * Removes the object at the top of this stack and returns that object as the
     * value of this function.
     *
     * @return The object at the top of this stack (the last item of the
     *         {@code Vector} object).
     * @throws EmptyStackException if this stack is empty.
     */
    @Override
    public synchronized E pop() {
	E e = super.pop();
	if (comparator == null) {
	    if (comparable(e, getMin()) == 0)
		mins.pop();
	} else {
	    if (comparator(e, getMin()) == 0)
		mins.pop();
	}
	return e;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     *
     * @return the object at the top of this stack (the last item of the
     *         {@code Vector} object).
     * @throws EmptyStackException if this stack is empty.
     */
    @Override
    public E peek() {
	return super.peek();
    }

    /**
     * Remove all of the element from this stack.
     */
    @Override
    public void clear() {
	super.clear();
	mins.clear();
    }

    /**
     * Returns a clone of this stack. The copy will contain a reference to a clone
     * of the internal data, not a reference to the original internal data in this
     * stack.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
	MinStack<E> clone = (MinStack<E>) super.clone();
	clone.mins = (Stack<E>) this.mins.clone();
	return clone;
    }

    /**
     * Returns the minimum element in this stack at the time.
     * 
     * @return the minimum element in this stack according to the comparator, or
     *         {@code null} if this stack is sorted according to the natural
     *         ordering of its elements
     */
    public E getMin() {
	return mins.peek();
    }

    /**
     * Returns the comparator used to order the elements in this stack, or
     * {@code null} if this stack is sorted according to the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order this stack, or {@code null} if this
     *         stack is sorted according to the natural ordering of its elements
     */
    public Comparator<? super E> comparator() {
	return comparator;
    }

    @Override
    public String toString() {
	if (super.isEmpty())
	    return "[" + super.toString() + ",Min=]";
	return "[" + super.toString() + ",Min=" + getMin() + "]";
    }

    @SuppressWarnings("unchecked")
    private int comparable(E input, E curMin) {
	return ((Comparable<? super E>) input).compareTo(curMin);
    }

    private int comparator(E input, E curMin) {
	return comparator.compare(input, curMin);
    }

    // Unsupported Methods
    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean add(E e) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void add(int index, E element) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void addElement(E obj) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public E remove(int index) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean remove(Object o) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void removeAllElements() {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean removeElement(Object obj) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void removeElementAt(int index) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public E set(int index, E element) {
	throw new UnsupportedOperationException();
    }

    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException Unsupported Operation.
     */
    @Override
    public void setElementAt(E obj, int index) {
	throw new UnsupportedOperationException();
    }

}
