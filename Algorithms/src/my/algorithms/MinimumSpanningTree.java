package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import my.data_structures.Graph;
import my.data_structures.Graph.Edge;

public class MinimumSpanningTree
{

	public static void main(String[] args) throws FileNotFoundException
	{
		// Load Graph
		File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/edges.txt"));
		Scanner sc = new Scanner(file);
		System.out.println(sc.nextLine());
		ArrayList<Edge> edges = new ArrayList<Edge>();
		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line);
			edges.add(new Edge(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
			sc2.close();
		}
		sc.close();
		
		System.out.println(edges.size());
		Graph graph = new Graph(edges);
		System.out.println(graph);
		
		ArrayList<Edge> MST = prim(graph);
		System.out.println(MST.size() + " " + totalCost(MST));
		
	}
	
	public static int totalCost(ArrayList<Edge> edgeList)
	{
		int cost = 0;
		for (Edge edge : edgeList) cost += edge.getDist();
		return cost;
	}
	
	public static ArrayList<Edge> prim(Graph graph)
	{
		// Initialize spanned vertex
		Integer[] vertices = graph.getVertices().toArray(new Integer[0]);
		HashSet<Integer> spannedNode = new HashSet<Integer>();
		spannedNode.add(vertices[0]);
		
		// Store edges
		PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>((e1, e2) -> {return e1.getDist() - e2.getDist();});
		HashSet<Edge> visitedEdge = new HashSet<Edge>();
		
		// Increase Number of Spanned Vertices
		while (spannedNode.size() != graph.getNumOfVertices())
		{	
			// Loop each spanned vertex
			for (int node : vertices)
			{
				// Loop each edge
				ArrayList<Edge> edges = graph.getEdges(node);
				if (edges == null) continue;
				for (Edge edge : edges)
				{	
					edge.setTail(node);
					// Find edge that is Not Stored & Span to Unspanned Vertex
					if (spannedNode.contains(edge.getHead()) && !spannedNode.contains(edge.getTail()) && !visitedEdge.contains(edge))
					{	
						minHeap.add(edge);
					} else if (!spannedNode.contains(edge.getHead()) && spannedNode.contains(edge.getTail()) && !visitedEdge.contains(edge))
					{
						minHeap.add(edge);
					}
				}
			}
			// No Edge Found, Terminate
			if (minHeap.isEmpty()) throw new IllegalArgumentException("No Spanning Tree Found.");
			
			// Find the edge with minimum cost
			Edge minEdge = minHeap.peek();
			
			minHeap.clear();
			// Store the edge with minimum cost
			if (spannedNode.contains(minEdge.getTail())) spannedNode.add(minEdge.getHead());
			else spannedNode.add(minEdge.getTail());
			visitedEdge.add(minEdge);
		}
		
		// Return spanning edges
		return new ArrayList<Edge>(visitedEdge);
	}

}
