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
	
}
