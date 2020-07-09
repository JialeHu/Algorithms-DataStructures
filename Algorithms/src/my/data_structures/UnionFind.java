package my.data_structures;

import java.util.Collection;
import java.util.HashMap;

/**
 * An implementation of UnionFind data structure.
 * 
 * @author Jiale Hu
 *
 * @param <E> Objects to be used in UnionFind
 */
public class UnionFind<E> {

    /**
     * An Graph Edge {@code class} that contains distance between nodes. Has natural
     * ordering based on ascending distance.
     * 
     * @author Jiale Hu
     *
     * @param <E> Two Node Objects in an Edge
     */
    public static class Edge<E> implements Comparable<UnionFind.Edge<E>> {
	private final E source;
	private final E target;
	private final int distance;

	public Edge(E source, E target, int distance) {
	    this.source = source;
	    this.target = target;
	    this.distance = distance;
	}

	/**
	 * @return source node of the edge
	 */
	public E getSource() {
	    return source;
	}

	/**
	 * @return target node of the edge
	 */
	public E getTarget() {
	    return target;
	}

	/**
	 * @return distance of the edge
	 */
	public int getDistance() {
	    return distance;
	}

	@Override
	public int compareTo(Edge<E> e) {
	    return this.getDistance() - e.getDistance();
	}

	@Override
	public String toString() {
	    return "(" + source + "__" + distance + "__" + target + ")";
	}
    }

    /**
     * A node that can point to another node.
     * 
     * @author Jiale Hu
     *
     * @param <E> Object to be used in a Node
     */
    public static class Node<E> {
	public Node<E> next;
	private E value;

	public Node(E e) {
	    this.value = e;
	}

	/**
	 * @return {@code true} if this node points to {@code null} or points to itself
	 */
	public boolean isEnd() {
	    return next == null || this.equals(next);
	}

	@Override
	public String toString() {
	    return "(" + value + "->" + next + ")";
	}
    }

    private HashMap<E, Node<E>> nodes;
    private int numEndNodes;

    /**
     * Constructs an empty {@code UnionFind<E>}
     */
    public UnionFind() {
	nodes = new HashMap<E, Node<E>>();
	numEndNodes = 0;
    }

    /**
     * @param c a {@code Collection<? extends E>} containing elements to be used in
     *          UnionFind
     */
    public UnionFind(Collection<? extends E> c) {
	this();
	for (E e : c)
	    nodes.put(e, new Node<E>(e));
	numEndNodes = nodes.size();
    }

    /**
     * @param e element to be added to {@code UnionFind<E>}
     * @return {@code false} input element has already been in {@code UnionFind<E>}
     */
    public boolean addNode(E e) {
	if (nodes.containsKey(e))
	    return false;
	nodes.put(e, new Node<E>(e));
	return true;
    }

    /**
     * Union two elements.
     * 
     * @param edge {@link Edge} containing two elements to be union
     * @return {@code true} if union is done, {@code false} if union number is below
     *         2 or two elements are identical
     */
    public boolean union(Edge<E> edge) {
	if (numEndNodes < 2)
	    return false;

	E node1 = edge.getSource();
	E node2 = edge.getTarget();
	if (!nodes.containsKey(node1) || !nodes.containsKey(node2))
	    return false;

	Node<E> node1End = find(node1);
	Node<E> node2End = find(node2);
	if (node1End.equals(node2End))
	    return false;

	node2End.next = node1End;
	numEndNodes--;
	return true;
    }

    /**
     * Find the union an element belongs to.
     * 
     * @param e element to be found
     * @return {@link Node} that leads the union containing {@code e}
     */
    public Node<E> find(E e) {
	return findEndNode(nodes.get(e));
    }

    /**
     * @return number of unions
     */
    public int size() {
	return numEndNodes;
    }

    // TODO
    public int getNumEndNodes() {
	int num = 0;
	for (Node<E> node : nodes.values())
	    if (node.isEnd())
		num++;
	return num;
    }

    @Override
    public String toString() {
	return nodes.values().toString();
    }

    /**
     * Traverse {@code Node<E>} until end (defined in {@link Node}).
     * 
     * @param start starting {@code Node<E>} of traversal
     * @return end {@code Node<E>} of traversal
     */
    private Node<E> findEndNode(Node<E> start) {
	if (start == null)
	    return null;
	while (!start.isEnd()) {
	    start = start.next;
	}
	return start;
    }

}
