package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TopologicalSort
{

	public static void main(String[] args) throws FileNotFoundException
	{
		HashMap<Integer, List<Integer>> vertices = IntReader.adjacencyLists(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/kargerMinCut.txt"));
		vertices.get(1).clear(); // Set Node 1 as Sink Vertex
		if (!hasCycle(vertices)) 
		{
			int[] order = DFSsort(vertices);
			System.out.println(Arrays.toString(order));
		}
	}
	
	public static boolean hasCycle(HashMap<Integer, List<Integer>> graph) // No Topological Ordering When Cycle Occur
	{
		for (List<Integer> edges : graph.values()) if (edges.size() == 0) return false;
		return true;
	}

	// O(n)
	public static int[] DFSsort(HashMap<Integer, List<Integer>> graph)
	{
		// Get All Nodes
		Set<Integer> nodes = graph.keySet();
		// Initialize Output
		int[] order = new int[nodes.size()];
		// Initialize Order for the Last Node
		curNum = nodes.size()-1;
		// Mark All Nodes Unexplored
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		for (int node : nodes) visited.put(node, false);
		// Loop All Nodes
		for (int node : nodes)
		{
			if (!visited.get(node)) DFShelper(graph, node, visited, order);
		}
		return order;
	}
	private static int curNum;
	private static void DFShelper(HashMap<Integer, List<Integer>> graph, int startNode, HashMap<Integer, Boolean> visited, int[] order)
	{
		// Mark Visited
		visited.replace(startNode, true);
		// Get Edges
		List<Integer> edges = graph.get(startNode);
		// Recurse All Edges
		for (int node : edges)
		{
			if (!visited.get(node)) DFShelper(graph, node, visited, order);
		}
		// Sink Vertex (No Edge)
		order[curNum] = startNode;
		curNum--;
	}
	
}
