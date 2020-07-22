package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import my.data_structures.Graph;
import my.data_structures.Graph.Edge;

public class ShortestPath {

    public static void main(String[] args) throws FileNotFoundException {
	
	/*
	 * [Vertex1] [[head1,dist1] [head2,dist2] ...]
	 * ...
	 */
	Graph graph = new Graph(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/dijkstraData.txt"));
	graph.print();

	HashMap<Integer, Integer> shortestDist = dijkstra(graph, 1);
	System.out.println(shortestDist);
	// 7,37,59,82,99,115,133,165,188,197
	System.out.print(shortestDist.get(7) + ",");
	System.out.print(shortestDist.get(37) + ",");
	System.out.print(shortestDist.get(59) + ",");
	System.out.print(shortestDist.get(82) + ",");
	System.out.print(shortestDist.get(99) + ",");
	System.out.print(shortestDist.get(115) + ",");
	System.out.print(shortestDist.get(133) + ",");
	System.out.print(shortestDist.get(165) + ",");
	System.out.print(shortestDist.get(188) + ",");
	System.out.println(shortestDist.get(197));
    }

    // O(m log(n)) with TreeMap (Non-negative edge cost ONLY)
    public static HashMap<Integer, Integer> dijkstra(Graph graph, int sourceVertex) {
	// Store Processed Vertices and Their Distance {Vertex=Distance}
	HashMap<Integer, Integer> shortestDist = new HashMap<Integer, Integer>();
	// Initialize with Source Vertex
	shortestDist.put(sourceVertex, 0);
	// Main Loop
	while (shortestDist.size() != graph.getNumOfTails()) {
	    // Store Distances of Candidates {Distance=Head}
	    TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
	    // Find Every Processed Tail
	    for (int tail : shortestDist.keySet()) {
		// Find Every Unprocessed Head
		List<Edge> edges = graph.getEdges(tail);
		for (Edge edge : edges) {
		    int head = edge.getHead();
		    // Skip Processed Head
		    if (shortestDist.containsKey(head))
			continue;
		    // Calculate Distance to Source
		    int distance = shortestDist.get(tail) + edge.getDist();
		    treeMap.put(distance, head);
		}
	    }
	    // Find The Head with Shortest Distance
	    Entry<Integer, Integer> shortestEntry = treeMap.firstEntry();
	    // Update Processed Vertices and Their Distance
	    shortestDist.put(shortestEntry.getValue(), shortestEntry.getKey());
	}
	return shortestDist;
    }
    
    public static void Bellman_Ford(Graph graph, int sourceVertex) {
	
    }
    
}
