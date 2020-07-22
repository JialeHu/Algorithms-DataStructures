package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import my.data_structures.Graph;
import my.data_structures.Graph.Edge;

public class AllPairsShortestPaths {

    public static void main(String[] args) throws FileNotFoundException {
	
	/*
	 * [# Vertices] [# Edges]
	 * [tail] [head] [length]
	 */
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/g1.txt"));
	Scanner sc = new Scanner(file);
	System.out.println(sc.nextLine());
	ArrayList<Edge> edges = new ArrayList<Edge>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    Scanner sc2 = new Scanner(line);
	    edges.add(new Edge(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
	    sc2.close();
	}
	sc.close();

	Graph g1 = new Graph(edges);
	System.out.println(g1);
	System.out.println(edges.size());
	
	System.out.println(floyd_warshall_length(g1));
	
	System.out.println("----------");
	
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/g2.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	edges = new ArrayList<Edge>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    Scanner sc2 = new Scanner(line);
	    edges.add(new Edge(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
	    sc2.close();
	}
	sc.close();
	
	Graph g2 = new Graph(edges);
	System.out.println(g2);
	System.out.println(edges.size());
	
	System.out.println(floyd_warshall_length(g2));
	
	System.out.println("----------");
	
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/g3.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	edges = new ArrayList<Edge>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    Scanner sc2 = new Scanner(line);
	    edges.add(new Edge(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
	    sc2.close();
	}
	sc.close();
	
	Graph g3 = new Graph(edges);
	System.out.println(g3);
	System.out.println(edges.size());
	
	System.out.println(floyd_warshall_length(g3));
	
	System.out.println("----------");
	
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/largeGraph.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	edges = new ArrayList<Edge>();
	while (sc.hasNextLine()) {
	    String line = sc.nextLine();
	    Scanner sc2 = new Scanner(line);
	    edges.add(new Edge(sc2.nextInt(), sc2.nextInt(), sc2.nextInt()));
	    sc2.close();
	}
	sc.close();
	
	Graph large = new Graph(edges);
	System.out.println(large);
	System.out.println(edges.size());
	
	System.out.println(floyd_warshall_length(large));
	
	
    }
    
    public static Integer floyd_warshall_length(Graph g) {
	int[][][] dp = floyd_warshall_dp(g);
	if (dp.length == 0 || containsNegativeCycle(dp)) return null;
	return dp[0][0][0];
    }
    
    // O(n^3) for time and space
    private static int[][][] floyd_warshall_dp(Graph g) {
	int n = g.getNumOfVertices(); // Number of vertices
	int[][][] dp = new int[n+1][n+1][n+1]; // Length of a shortest i-j path with nodes in 1...k
	// Base Cases
	for (int i = 1; i < n+1; i++) {
	    for (int j = 1; j < n+1; j++) {
		if (i == j) dp[i][j][0] = 0; // Case 1
		else {
		    Integer cost = g.getEdgeCost(i, j);
		    if (cost != null) dp[i][j][0] = cost; // Case 2
		    else dp[i][j][0] = Integer.MAX_VALUE; // Case 3
		}
	    }
	}
	// Find the minimum length on the way
	int minLength = Integer.MAX_VALUE;
	// Build Solution
	for (int k = 1; k < n+1; k++) {
	    for (int i = 1; i < n+1; i++) {
		for (int j = 1; j < n+1; j++) {
		    int case1 = dp[i][j][k-1];
		    int case2;
		    if (dp[i][k][k-1] == Integer.MAX_VALUE || dp[k][j][k-1] == Integer.MAX_VALUE) case2 = Integer.MAX_VALUE;
		    else case2 = dp[i][k][k-1] + dp[k][j][k-1];
		    
		    dp[i][j][k] = Math.min(case1, case2);
		    minLength = Math.min(minLength, dp[i][j][k]);
		}
	    }
	}
	// Save the minimum length in dp array
	dp[0][0][0] = minLength;
	return dp;
    }
    
    private static boolean containsNegativeCycle(int[][][] dp) {
	int len = dp.length;
	for (int ij = 1; ij < len; ij++) {
	    if (dp[ij][ij][len-1] < 0) return true;
	}
	return false;
    }
    
    public static void johnson_length(Graph g) {
	
    }
    
    private static void johnson(Graph g) {
	
	
	
    }

}
