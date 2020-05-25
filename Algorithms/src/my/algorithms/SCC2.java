package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SCC2 // Strongly Connected Components Iterative NOT CORRECTLY IMPLEMENTED YET
{

	public static void main(String[] args) throws FileNotFoundException
	{
		List<int[]> lists = IntReader.int2arrays(new File("").getAbsolutePath().concat("/src/my/algorithms/SCC.txt"));
		System.out.println(lists.size());
		for (int[] list : lists) System.out.println(list.length);
		
		kosaraju(lists.get(0), lists.get(1));
		
	}
	
	// O(m+n)
	public static void kosaraju(int[] tail, int[] head)
	{	
		// First Pass: Reversed Directed Graph
		System.out.println("First Pass");
		HashMap<Integer, List<Integer>> graph = array2map(head, tail);
		DFSloop1(graph);
//		System.out.println(graph);
//		System.out.println(finishingOrder);
		
		// Second Pass: Original Directed Graph
		System.out.println("Second Pass");
//		graph = array2map(tail, head);
		DFSloop2(graph);
	}
	private static int t;
	private static HashMap<Integer, Integer> finishingOrder;
	private static void DFSloop1(HashMap<Integer, List<Integer>> graph)
	{
		System.out.println(graph.size());
		System.out.println(maxNode);
		
		// Initialize Count for Finish Ordering
		t = 0;
		finishingOrder = new HashMap<Integer, Integer>();
		// Mark All Nodes Unexplored
		HashSet<Integer> visited = new HashSet<Integer>();
		// Loop Each Node From High to Low
		for (int i = maxNode; i > 0; i--)
		{
			if (visited.contains(i) || graph.get(i) == null) 
			{
				t++;
				finishingOrder.put(t, i);
				continue;
			}
			if (!visited.contains(i))
			{
				DFShelper1(graph, i, visited);
			}
		}
		
	}
	private static void DFShelper1(HashMap<Integer, List<Integer>> graph, int startNode, HashSet<Integer> visited)
	{
		// DFS Stack
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(startNode);
		// DFS
		while (!stack.isEmpty())
		{
			// Pop New Tail
			int tail = stack.pop();
			// Assign Finish Order When Visited or Sink Node
			if (visited.contains(tail) || graph.get(tail) == null)
			{
				t++;
				finishingOrder.put(t, tail);
				continue;
			}
			// Mark Visited
			visited.add(tail);
			// Push New Tails
			for (int head : graph.get(tail)) stack.push(head);
		}
	}
	
	private static void DFSloop2(HashMap<Integer, List<Integer>> graph)
	{
		System.out.println(graph.size());
		System.out.println(maxNode);
		
		// Store Number of Components
		ArrayList<Integer> list = new ArrayList<Integer>();
		// Mark All Nodes Unexplored
		HashSet<Integer> visited = new HashSet<Integer>();
		// Loop Each Node From High to Low
		for (int i = maxNode; i > 0; i--)
		{
			// Finishing Order
			if (finishingOrder.get(i) == null) continue;
			int f = finishingOrder.get(i);
			if (graph.get(f) == null) continue;
			if (!visited.contains(f)) 
			{
				int c = DFShelper2(graph, f, visited);
				list.add(c);
			}
		}
		list.sort(Collections.reverseOrder());
		System.out.println(list);
	}
	private static int DFShelper2(HashMap<Integer, List<Integer>> graph, int startNode, HashSet<Integer> visited)
	{
		// Counter
		int c = 0;
		// DFS Stack
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(startNode);
		// DFS
		while (!stack.isEmpty())
		{
			// Pop New Tail
			int tail = stack.pop();
			// Skip When Visited or Sink Node
			if (visited.contains(tail) || graph.get(tail) == null) continue;
			// Count Nodes
			c++;
			// Mark Visited
			visited.add(tail);
			// Push New Tails
			for (int head : graph.get(tail)) stack.push(head);
		}
		return c;
	}
	

	private static int maxNode;
	public static HashMap<Integer, List<Integer>> array2map(int[] tail, int[] head) // Tail: Starting Vertex, Head: End Vertex
	{
		if (tail.length != head.length) throw new IllegalArgumentException("Array Length Does Not Match");
		maxNode = 0;
		HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < tail.length; i++)
		{
			if (!graph.containsKey(tail[i])) {
				graph.put(tail[i], new ArrayList<Integer>());
				maxNode = Math.max(maxNode, tail[i]);
			}
			graph.get(tail[i]).add(head[i]);
		}
		return graph;
	}
	
//	public static ArrayList<List<Integer>> array2list(int[] tail, int[] head) // Tail: Starting Vertex, Head: End Vertex
//	{
//		if (tail.length != head.length) throw new IllegalArgumentException("Array Length Does Not Match");
//		ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>();
//		for (int i = 0; i < tail.length; i++)
//		{
//			try
//			{
//				graph.get(tail[i]).add(head[i]);
//			} catch (IndexOutOfBoundsException e)
//			{
//				List<Integer> edges = new ArrayList<Integer>();
//				edges.add(head[i]);
//				graph.add(tail[i], edges);
//			}
//		}
//		return graph;
//	}
}
