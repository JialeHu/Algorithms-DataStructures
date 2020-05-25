package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class IntReader
{
	// Input per Line: Number
	public static int[] int2array(String filePath) throws FileNotFoundException
	{
		File file = new File(filePath); 
		Scanner sc = new Scanner(file);
		LinkedList<Integer> list = new LinkedList<Integer>();
		while (sc.hasNextInt())
		{
			list.add(sc.nextInt());
		}
		sc.close();
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) array[i] = list.get(i);
		return array;
	}
	
	// Input per Line: Vertex Edges
	public static HashMap<Integer, List<Integer>> adjacencyLists(String filePath) throws FileNotFoundException
	{
		File file = new File(filePath); 
		Scanner sc = new Scanner(file);
		HashMap<Integer, List<Integer>> vertices = new HashMap<Integer, List<Integer>>();
		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			Scanner sc1 = new Scanner(line);
			int idx = sc1.nextInt();
			ArrayList<Integer> edgeList = new ArrayList<Integer>();
			while (sc1.hasNextInt())
			{
				edgeList.add(sc1.nextInt());
			}
			sc1.close();
			vertices.put(idx, edgeList);
//			System.out.println(idx + " " + edgeList);
		}
		sc.close();
		return vertices;
	}
	
	// Input per Line: Number in Array1, Number in Array2...
	public static List<int[]> int2arrays(String filePath) throws FileNotFoundException
	{
		File file = new File(filePath); 
		Scanner sc = new Scanner(file);
		LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();
		String line = sc.nextLine();
		Scanner sc1 = new Scanner(line);
		while (sc1.hasNextInt())
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(sc1.nextInt());
			lists.add(list);
		}
		sc1.close();
		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			sc1 = new Scanner(line);
			int idx = 0;
			while (sc1.hasNextInt())
			{
				lists.get(idx).add(sc1.nextInt());
				idx++;
			}
			sc1.close();
		}
		sc.close();
		LinkedList<int[]> output = new LinkedList<int[]>();
		for (List<Integer> list : lists)
		{
			int[] array = new int[list.size()];
			for (int i = 0; i < array.length; i++) array[i] = list.get(i);
			output.add(array);
		}
		return output;
	}
	
	// Input per Line: Vertex Edges,Distance
//	public static void adjacencyLists2(String filePath) throws FileNotFoundException
//	{
//		File file = new File(filePath); 
//		Scanner sc = new Scanner(file);
//		HashMap<Integer, List<Integer>> vertices = new HashMap<Integer, List<Integer>>();
//		while (sc.hasNextLine())
//		{
//			String line = sc.nextLine();
//			Scanner sc1 = new Scanner(line);
//			int idx = sc1.nextInt();
//			ArrayList<Integer> edgeList = new ArrayList<Integer>();
//			while (sc1.hasNextInt())
//			{
//				edgeList.add(sc1.nextInt());
//			}
//			sc1.close();
//			vertices.put(idx, edgeList);
////			System.out.println(idx + " " + edgeList);
//		}
//		sc.close();
//		return vertices;
//	}
}
