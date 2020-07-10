package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import my.data_structures.Pair;

public class Knapsack {

    public static void main(String[] args) throws FileNotFoundException {

	/*
	 * [knapsack_size][number_of_items]
	 * [value_1] [weight_1]
	 * [value_2] [weight_2]
	 */
	// Test File 1
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/knapsack1.txt"));
	Scanner sc = new Scanner(file);
	int size = sc.nextInt();
	int num = sc.nextInt();
	List<Pair<Integer, Integer>> value_weights = new ArrayList<Pair<Integer, Integer>>();
	while (sc.hasNext()) {
	    value_weights.add(new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt()));
	}
	sc.close();
	System.out.println(value_weights);
	System.out.println(num + " " + value_weights.size());
	System.out.println("knapsack size: " + size);

	System.out.println("total value: " + knapsackValue(value_weights, size));
	Set<Pair<Integer, Integer>> set = knapsack(value_weights, size);
	System.out.println(set);
	System.out.println("total value from set: " + set.stream().mapToInt((p) -> p.get1()).sum());
	System.out.println("total weight from set: " + set.stream().mapToInt((p) -> p.get2()).sum());
	
	System.out.println("-----------");
	
	// Test File 2
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/knapsack_big.txt"));
	sc = new Scanner(file);
	size = sc.nextInt();
	num = sc.nextInt();
	value_weights = new ArrayList<Pair<Integer, Integer>>();
	while (sc.hasNext()) {
	    value_weights.add(new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt()));
	}
	sc.close();
	System.out.println(value_weights);
	System.out.println(num + " " + value_weights.size());
	System.out.println("knapsack size: " + size);
	
	System.out.println("total value: " + knapsackValue2(value_weights, size));
    }

    /**
     * Calculate maximum of items can be put in a knapsack (sum of item weight <= totalSize of the knapsack).
     * @param items {@code Pair<Value, Weight>} to be put in the knapsack
     * @param totalSize of the knapsack
     * @return maximum value can be put in the knapsack
     */
    public static int knapsackValue(List<Pair<Integer, Integer>> items, int totalSize) {
	// Get Last entry in DP array
	int[][] dp = knapsack_dp(items, totalSize);
	int rL = dp.length - 1;
	int cL = dp[rL].length - 1;
	return dp[rL][cL];
    }

    /**
     * Calculate maximum of items can be put in a knapsack (sum of item weight <= totalSize of the knapsack).
     * @param items {@code Pair<Value, Weight>} to be put in the knapsack
     * @param totalSize of the knapsack
     * @return items to be put in the knapsack for maximum value
     */
    public static Set<Pair<Integer, Integer>> knapsack(List<Pair<Integer, Integer>> items, int totalSize) {
	int[][] dp = knapsack_dp(items, totalSize);
	Set<Pair<Integer, Integer>> set = new HashSet<>();
	// Reconstruct solution
	int item = dp.length - 1;
	int size = dp[item].length - 1;
	while (item >= 0) {
	    int Vi = items.get(item).get1();
	    int Wi = items.get(item).get2();
	    if (size - Wi >= 0 && dp[item-1][size] < dp[item-1][size-Wi] + Vi) {
		set.add(items.get(item));
		size -= Wi;
	    }
	    item--;
	}
	return set;
    }

    private static int[][] knapsack_dp(List<Pair<Integer, Integer>> items, int totalSize) {
	// Initialize DP memory ([remaining available size][current max value])
	int[][] dp = new int[items.size()][totalSize+1];
	dp[0][totalSize-items.get(0).get2()] = items.get(0).get1();
	// Build Solution
	for (int item = 1; item < items.size(); item++) {
	    for (int size = 0; size <= totalSize; size++) {
		// Value and weight of current item
		int Vi = items.get(item).get1();
		int Wi = items.get(item).get2();
		// Update DP array
		if (size - Wi < 0) dp[item][size] = dp[item-1][size];
		else dp[item][size] = Math.max(dp[item-1][size], dp[item-1][size-Wi] + Vi);
	    }
	}
	return dp;
    }
    
    /**
     * Calculate maximum of items can be put in a knapsack (sum of item weight <= totalSize of the knapsack).
     * Better space performance.
     * @param items {@code Pair<Value, Weight>} to be put in the knapsack
     * @param totalSize of the knapsack
     * @return maximum value can be put in the knapsack
     */
    public static int knapsackValue2(List<Pair<Integer, Integer>> items, int totalSize) {
	int[] preItem = new int[totalSize+1];
	int[] curItem = preItem;
	preItem[totalSize-items.get(0).get2()] = items.get(0).get1();
	
	for (int item = 1; item < items.size(); item++) {
	    for (int size = 0; size <= totalSize; size++) {
		int Vi = items.get(item).get1();
		int Wi = items.get(item).get2();
		if (size - Wi < 0) curItem[size] =preItem[size];
		else curItem[size] = Math.max(preItem[size], preItem[size-Wi] + Vi);
	    }
	    preItem = Arrays.copyOf(curItem, curItem.length);
	}
	return preItem[totalSize];
    }

}
