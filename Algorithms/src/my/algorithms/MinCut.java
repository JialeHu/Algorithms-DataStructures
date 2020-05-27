package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MinCut
{
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException
	{
		
		int num = Integer.MAX_VALUE;
		for (int i = 0; i < 100; i++)
		{
			HashMap<Integer, List<Integer>> vertices = NumberReader.adjacencyLists(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/kargerMinCut.txt"));
			int temp = randomContraction(vertices);
			System.out.println(temp);
			num = Math.min(num, temp);
		}
		System.out.println("Min Cut: " + num);
		
	}
	
	public static int randomContraction(HashMap<Integer, List<Integer>> vertices)
	{
		// Random Contraction
		while (vertices.size() > 2)
		{
			contract(vertices);
		}
		// Counting Edges Left
		int num = 0;
		for (List<Integer> list : vertices.values())
		{
			num += list.size();
		}
		System.out.println(vertices);
		return num;
	}
	private static void contract(HashMap<Integer, List<Integer>> vertices)
	{
		// Pick One Edge (Two Vertices)
		Random random = new Random();
		
		Integer[] keys = vertices.keySet().toArray(new Integer[0]);
		int vertexIdx = random.nextInt(keys.length);
		int vertex = keys[vertexIdx];
		int targetIdx = random.nextInt(vertices.get(vertex).size());
		int target = vertices.get(vertex).get(targetIdx);
		merge(vertices, vertex, target);
	}
	private static void merge(HashMap<Integer, List<Integer>> vertices, int vertex, int target)
	{
		// Get Two Lists
		List<Integer> edges1 = vertices.get(vertex);
		List<Integer> edges2 = vertices.get(target);
		
		// Remove Mutual Connections
		Iterator<Integer> iterator1 = edges1.iterator();
		while (iterator1.hasNext())
		{
			if (iterator1.next() == target) iterator1.remove();
		}
		Iterator<Integer> iterator2 = edges2.iterator();
		while (iterator2.hasNext())
		{
			if (iterator2.next() == vertex) iterator2.remove();
		}
		
		// Merge Two Vertices in Lower One
		List<Integer> newEdges = new ArrayList<Integer>();
		newEdges.addAll(edges1);
		newEdges.addAll(edges2);
		
		vertices.remove(target);
		vertices.replace(vertex, newEdges);
		
		// Update All Edges
		for (List<Integer> list : vertices.values())
		{
			ListIterator<Integer> li = list.listIterator();
			while (li.hasNext()) {
				if (li.next() == target) li.set(vertex);
			}
		}
	}
	
}
