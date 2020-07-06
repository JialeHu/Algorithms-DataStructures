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

public class Clustering
{

	public static void main(String[] args) throws FileNotFoundException
	{
		// Load Graph
		File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/clustering1.txt"));
		Scanner sc = new Scanner(file);
		
		int numNodes = Integer.parseInt(sc.nextLine());
		List<Integer> nodes = IntStream.rangeClosed(1, numNodes).boxed().collect(Collectors.toList());
		
		List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line);
			edges.add(new Edge<Integer>(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
			sc2.close();
		}
		sc.close();
		
		System.out.println(edges);
		System.out.println(nodes);
		
		int maxSpacing = maxSpacingKclustering(edges, nodes, 4);
		System.out.println(maxSpacing);
	}
	
	public static int maxSpacingKclustering(List<Edge<Integer>> edges, List<Integer> nodes, int k)
	{
		if (k < 2) throw new IllegalArgumentException("k Must >= 2");
		if (k > nodes.size()) throw new IllegalArgumentException("k Must <= Num of Nodes");
		
		// Initialize UnionFind
		UnionFind<Integer> unionFind = new UnionFind<>(nodes);
		// Heap Edges by Ascending Distances
		PriorityQueue<Edge<Integer>> edgesHeap = new PriorityQueue<>();
		edgesHeap.addAll(edges);
		
		// Union Nodes with Shorter Distance First
		while (unionFind.size() != k)
		{
			Edge<Integer> edge = edgesHeap.poll();
			unionFind.union(edge);
		}
		// Find Next Separated Pair of Nodes
		while (!edgesHeap.isEmpty())
		{
			Edge<Integer> edge = edgesHeap.poll();
			Object n1 = unionFind.find(edge.getSource());
			Object n2 = unionFind.find(edge.getTarget());
			if (!n1.equals(n2)) return edge.getDistance();
		}
		// Error
		return 0;
	}

}
