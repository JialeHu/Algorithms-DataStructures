package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.data_structures.Pair;

import java.util.Scanner;

public class TheTravelingSalesman {

    public static void main(String[] args) throws FileNotFoundException {
	
	/*
	 * [# cities]
	 * [city 1 x] [city 1 y]
	 * ...
	 */
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/tsp.txt"));
	Scanner sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	List<Pair<Double, Double>> cities = new ArrayList<>();
	while (sc.hasNextDouble()) {
	    double x = sc.nextDouble();
	    double y = sc.nextDouble();
	    cities.add(new Pair<Double, Double>(x, y));
	}
	sc.close();
	System.out.println(cities.size());
	System.out.println(cities);
	
	System.out.println(DP_Exact(cities));
	System.out.println(nearestNeighbor_Heuristic(cities));
	
	System.out.println("----------");
	
	/*
	 * [# cities]
	 * [#] [city 1 x] [city 1 y]
	 * ...
	 */
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/nn.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	cities = new ArrayList<>();
	while (sc.hasNextDouble()) {
	    sc.nextDouble();
	    double x = sc.nextDouble();
	    double y = sc.nextDouble();
	    cities.add(new Pair<Double, Double>(x, y));
	}
	sc.close();
	System.out.println(cities.size());
	
	System.out.println(nearestNeighbor_Heuristic(cities));
	
    }
    
    // O(n^2 2^n)
    public static double DP_Exact(List<Pair<Double, Double>> set) {
	
	int n = set.size();
	if (n < 2) throw new IllegalArgumentException("Input size must be greater than 1");
	if (n > 33) throw new IllegalArgumentException("Input size must not be greater than 33");
	
	// Initialize DP
	double[][] dp = new double[1 << (n-1)][n];
	dp[0][n-1] = 0f; // For S = {n-1}, n-1 is always selected
	for (int i = 1; i < dp.length; i++) {
	    dp[i][n-1] = Double.MAX_VALUE;
	}
	
	// Construct Solution
	for (int m = 2; m <= n; m++) { // size of subset
	    for (int subset : getSubsetsBits(n, m)) { // subset with size m
		for (int j = 0; j < n-1; j++) { // node j in subset, j != n-1
		    if (((subset >>> j) & 1) != 1) continue; // Skip nodes not in subset
		    
		    double min = Double.MAX_VALUE;
		    for (int k = 0; k < n; k++) { // node k in subset, k != j
			
			if (k != n - 1) {
			    if (((subset >>> k) & 1) != 1 || k == j) continue;
			}
			int subsetJ = subset & ~(1 << j); // Subset - {j}
			if (dp[subsetJ][k] == Double.MAX_VALUE) continue;
			double dist = dp[subsetJ][k] + getDist(set.get(k), set.get(j));
			min = Math.min(min, dist);
		    }
		    dp[subset][j] = min; // Update DP
		}
	    }
	}
	
	// Find Solution
	double min = Double.MAX_VALUE;
	for (int j = 0; j < n-1; j++) {
	    double dist = dp[dp.length-1][j] + getDist(set.get(n-1), set.get(j));
	    min = Math.min(min, dist);
	}
	return min;
    }
    
    private static double getDist(Pair<Double, Double> coor1, Pair<Double, Double> coor2) {
	
	double r1 = coor1.get1() - coor2.get1();
	r1 *= r1;
	double r2 = coor1.get2() - coor2.get2();
	r2 *= r2;
	
	return Math.sqrt(r1 + r2);
    }

    /**
     * Get all subsets indices as integers in a list of set.
     * @param set input set;
     * @return all subsets
     */
    public static List<Set<Integer>> getSubsetsIndices(List<Pair<Double, Double>> set) {
	
	List<Set<Integer>> subsets = new ArrayList<>();
	
	HashSet<Integer> subset = new HashSet<>();
	subset.add(0);
	
	helper(subsets, subset, set.size(), 1);
	
	return subsets;
    }
    
    @SuppressWarnings("unchecked")
    private static void helper(List<Set<Integer>> subsets, HashSet<Integer> subset, int setSize, int startIdx) {
	
	subsets.add((Set<Integer>) subset.clone()); // Add subset to subsets
	
	for (int i = startIdx; i < setSize; i++) {
	    subset.add(i);
	    helper(subsets, subset, setSize, i+1);
	    subset.remove(i);
	}
    }
    
    /**
     * Get subsets indices represented by bits, assume the element at n-1 is always selected
     * @param n size of the set
     * @param m size of subset
     * @return {@code Set<Integer>} containing integers denoting selected element from the set by bits (0 to n-2 as index)
     */
    private static Set<Integer> getSubsetsBits(int n, int m) {
	if (m > n) throw new IllegalArgumentException("#subset m must not be greater than #elements n");
	Set<Integer> subsets = new HashSet<>();
	helper(subsets, n, m, 0, 0, 1);
	return subsets;
    }
    
    private static void helper(Set<Integer> subsets, int n, int m, int subset, int idx, int size) {
	if (size == m) {
	    subsets.add(subset);
	    return;
	}
	for (int i = idx; i < n-1; i++) {
	    helper(subsets, n, m, subset+(1 << i), i+1, size+1);
	}
    }
    
    public static double nearestNeighbor_Heuristic(List<Pair<Double, Double>> set) {
	
	int n = set.size();
	if (n < 2) throw new IllegalArgumentException("Input size must be greater than 1");
	
	boolean[] visited = new boolean[n];
	double totalDist = 0f;
	
	Pair<Double, Double> first = set.get(0); // First city
	visited[0] = true;
	int visitCount = 1;
	Pair<Double, Double> temp = first;
	
	while (visitCount < n) {
	    double minDist = Double.MAX_VALUE;
	    int minIdx = 0;
	    for (int i = 1; i < n; i++) {
		if (visited[i]) continue;
		double dist = getDist(temp, set.get(i));
		if (dist < minDist) {
		    minDist = dist;
		    minIdx = i;
		}
	    }
	    totalDist += minDist;
	    temp = set.get(minIdx);
	    visited[minIdx] = true;
	    visitCount++;
	}
	totalDist += getDist(temp, first);
	return totalDist;
    }

}
