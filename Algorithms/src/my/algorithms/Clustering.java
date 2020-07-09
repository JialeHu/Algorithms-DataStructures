package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import my.data_structures.UnionFind;
import my.data_structures.UnionFind.Edge;

import java.util.Scanner;

public class Clustering {

    public static void main(String[] args) throws FileNotFoundException {
	// Load Graph 1
	/*
	 * [number_of_nodes] [edge 1 node 1] [edge 1 node 2] [edge 1 cost] [edge 2 node
	 * 1] [edge 2 node 2] [edge 2 cost]
	 */
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/clustering1.txt"));
	Scanner sc = new Scanner(file);
	int numNodes = Integer.parseInt(sc.nextLine());
	List<Integer> nodes = IntStream.rangeClosed(1, numNodes).boxed().collect(Collectors.toList());
	List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    Scanner nsc = new Scanner(line);
	    edges.add(new Edge<Integer>(nsc.nextInt(), nsc.nextInt(), nsc.nextInt()));
	    nsc.close();
	}
	sc.close();
	System.out.println(edges);
	System.out.println(nodes);

	int maxSpacing = maxSpacingKclustering(edges, nodes, 4);
	System.out.println("max-spacing: " + maxSpacing);

	System.out.println("--------------------------------------------------");

	// Load Graph 2
	/*
	 * [# of nodes] [# of bits for each node's label] [first bit of node 1] ...
	 * [last bit of node 1] [first bit of node 2] ... [last bit of node 2]
	 */
	File file2 = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/clustering_big.txt"));
	Scanner sc2 = new Scanner(file2);
	System.out.println(sc2.nextLine());

	List<Integer> nodes2 = new ArrayList<>();
	while (sc2.hasNextLine()) {
	    nodes2.add(Integer.parseInt(sc2.nextLine().replaceAll("\\s", ""), 2));
	}
	sc2.close();

	// largest K that K clustering with spacing at least 3
	// spacing determined by Hamming distance
	int maxK = maxKwithMinSpacingBits(nodes2);
	System.out.println(maxK);
    }

    public static int maxSpacingKclustering(List<Edge<Integer>> edges, List<Integer> nodes, int k) {
	if (k < 2)
	    throw new IllegalArgumentException("k Must >= 2");
	if (k > nodes.size())
	    throw new IllegalArgumentException("k Must <= Num of Nodes");

	// Initialize UnionFind
	UnionFind<Integer> unionFind = new UnionFind<>(nodes);
	// Heap Edges by Ascending Distances
	PriorityQueue<Edge<Integer>> edgesHeap = new PriorityQueue<>();
	edgesHeap.addAll(edges);

	// Union Nodes with Shorter Distance First
	while (unionFind.size() != k) {
	    Edge<Integer> edge = edgesHeap.poll();
	    unionFind.union(edge);
	}
	// Find Next Separated Pair of Nodes
	while (!edgesHeap.isEmpty()) {
	    Edge<Integer> edge = edgesHeap.poll();
	    Object n1 = unionFind.find(edge.getSource());
	    Object n2 = unionFind.find(edge.getTarget());
	    if (!n1.equals(n2))
		return edge.getDistance();
	}
	// Error
	return 0;
    }

    private static int maxKwithMinSpacingBits(List<Integer> nodes) {
	// Initialize UnionFind
	UnionFind<Integer> unionFind = new UnionFind<>(nodes); // All edges with 0 distance are union

	// Loop all nodes (bits)
	for (int node : nodes) {
	    for (int i = 0; i < 24; i++) {
		// Calculate bits with Hamming distance 1
		int dist1 = node ^ (1 << i);
		unionFind.union(new Edge<Integer>(node, dist1, 0));
		for (int j = i + 1; j < 24; j++) {
		    // Calculate bits with Hamming distance 2
		    int dist2 = node ^ (1 << i) ^ (1 << j);
		    unionFind.union(new Edge<Integer>(node, dist2, 0));
		}
	    }
	}

	return unionFind.size();
    }

}
