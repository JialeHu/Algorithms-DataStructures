package my.data_structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Graph
{
	
	public class Edge
	{
		private int head;
		private int distance;
		
		public Edge(int head, int distance)
		{
			this.head = head;
			this.distance = distance;
		}
		
		public int getHead()
		{
			return head;
		}
		public int getDist()
		{
			return distance;
		}
		
		@Override
		public String toString()
		{
			return "(Head: " + head + " Distance: " + distance + ")";
		}
	}
	
	private HashMap<Integer, ArrayList<Edge>> vertices;
	
	public Graph(String filePath) throws FileNotFoundException
	{
		vertices = new HashMap<Integer, ArrayList<Edge>>();
		
		File file = new File(filePath); 
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			str2graph(line);
		}
		sc.close();
	}
	
	private void str2graph(String line)
	{
		Scanner sc = new Scanner(line);
		int vertex = sc.nextInt();

		ArrayList<Edge> edges = new ArrayList<Edge>();
		while (sc.hasNext())
		{
			String edgeStr = sc.next();
			Scanner sce = new Scanner(edgeStr);
			sce.useDelimiter(",");
			int head = sce.nextInt();
			int dist = sce.nextInt();
			sce.close();
			edges.add(new Edge(head, dist));
		}
		sc.close();
		vertices.put(vertex, edges);
//		System.out.println(vertices);
	}
	
	public Set<Integer> getVertices()
	{
		return vertices.keySet();
	}
	
	public ArrayList<Edge> getEdges(int vertex)
	{
		return vertices.get(vertex);
	}
	
	public int getVertexSize()
	{
		return vertices.size();
	}
	
	public void print()
	{
		System.out.println(vertices);
	}
	
	@Override
	public String toString()
	{
		return "# Vertices: " + vertices.size();
	}

}
