package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import my.utility.NumberReader;

public class StronglyConnectedComponets {

    public static void main(String[] args) throws FileNotFoundException {
	List<int[]> lists = NumberReader
		.int2arrays(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/SCC.txt"));
	System.out.println(lists.size());
	for (int[] list : lists)
	    System.out.println(list.length);

	kosaraju(lists.get(0), lists.get(1));
	list.sort(Collections.reverseOrder());
	System.out.println(list);
    }

    // O(m+n)
    public static void kosaraju(int[] tail, int[] head) {
	// First Pass: Reversed Directed Graph
	System.out.println("First Pass");
	HashMap<Integer, List<Integer>> graph = array2map(head, tail);
	DFSloop1(graph);

	// Second Pass: Original Directed Graph
	System.out.println("Second Pass");
	graph = array2map(tail, head);
	DFSloop2(graph);

    }

    private static int finishTime;
    private static HashMap<Integer, Integer> finishingOrder;
    private static HashSet<Integer> visited;

    private static void DFSloop1(HashMap<Integer, List<Integer>> graph) {
	System.out.println(graph.size());
	System.out.println(maxNode);

	// Initialize Count for Finish Ordering
	finishTime = 0;
	finishingOrder = new HashMap<Integer, Integer>();
	// Mark All Nodes Unexplored
	visited = new HashSet<Integer>();
	// Loop Each Node From High to Low
	for (int i = maxNode; i > 0; i--) {
	    if (graph.get(i) == null)
		continue;
	    if (!visited.contains(i)) {
		DFShelper1(graph, i);
	    }
	}

    }

    private static void DFShelper1(HashMap<Integer, List<Integer>> graph, int startNode) {
	// Mark Visited
	visited.add(startNode);
	// Assign Leader

	// Recurse DFS
	for (int node : graph.get(startNode)) {
	    // Sink Vertex
	    if (graph.get(node) == null)
		continue;
	    if (!visited.contains(node))
		DFShelper1(graph, node);
	}
	// Update and Assign Finishing Time Order
	finishTime++;
	finishingOrder.put(finishTime, startNode);
    }

    private static int c;
    private static ArrayList<Integer> list;

    private static void DFSloop2(HashMap<Integer, List<Integer>> graph) {
	System.out.println(graph.size());
	System.out.println(maxNode);

	list = new ArrayList<Integer>();
	// Mark All Nodes Unexplored
	visited = new HashSet<Integer>();
	// Loop Each Node From High to Low
	for (int i = maxNode; i > 0; i--) {
	    // Finishing Order
	    if (finishingOrder.get(i) == null)
		continue;
	    int f = finishingOrder.get(i);
	    if (graph.get(f) == null)
		continue;
	    // Initialize Count for Number of Components
	    c = 0;
	    if (!visited.contains(f)) {
		DFShelper2(graph, f);
	    }
	    if (c != 0)
		list.add(c);
	}
    }

    private static void DFShelper2(HashMap<Integer, List<Integer>> graph, int startNode) {
	// Mark Visited
	visited.add(startNode);
	// Assign Leader

	// Recurse DFS
	for (int node : graph.get(startNode)) {
	    // Some End Node
	    if (graph.get(node) == null)
		continue;
	    if (!visited.contains(node))
		DFShelper2(graph, node);
	}
	// Count Number of Components
	c++;
    }

    private static int maxNode;

    public static HashMap<Integer, List<Integer>> array2map(int[] tail, int[] head) // Tail: Starting Vertex, Head: End
										    // Vertex
    {
	if (tail.length != head.length)
	    throw new IllegalArgumentException("Array Length Does Not Match");
	maxNode = 0;
	HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
	for (int i = 0; i < tail.length; i++) {
	    if (!graph.containsKey(tail[i])) {
		graph.put(tail[i], new ArrayList<Integer>());
		maxNode = Math.max(maxNode, tail[i]);
	    }
	    graph.get(tail[i]).add(head[i]);
	}
	return graph;
    }

    // public static ArrayList<List<Integer>> array2list(int[] tail, int[] head) //
    // Tail: Starting Vertex, Head: End Vertex
    // {
    // if (tail.length != head.length) throw new IllegalArgumentException("Array
    // Length Does Not Match");
    // ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>();
    // for (int i = 0; i < tail.length; i++)
    // {
    // try
    // {
    // graph.get(tail[i]).add(head[i]);
    // } catch (IndexOutOfBoundsException e)
    // {
    // List<Integer> edges = new ArrayList<Integer>();
    // edges.add(head[i]);
    // graph.add(tail[i], edges);
    // }
    // }
    // return graph;
    // }
}
