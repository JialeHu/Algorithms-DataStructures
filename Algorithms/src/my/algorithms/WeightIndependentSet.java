package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WeightIndependentSet
{

    public static void main(String[] args) throws FileNotFoundException {
	/* [number_of_vertices]
	 * [weight of first vertex]
	 * [weight of second vertex]
	 */
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/mwis.txt"));
	Scanner sc = new Scanner(file);
	int i = 0, numVertices = sc.nextInt();
	int[] weights = new int[numVertices + 1];
	while (sc.hasNextInt())
	{
	    weights[++i] = sc.nextInt();
	}
	sc.close();
	System.out.println(Arrays.toString(weights));
	System.out.println(weights.length + " " + numVertices);

	System.out.println(maxWISvalue(weights));
	Set<Integer> set = maxWIS(weights);
	System.out.println(set);
	System.out.println(set.contains(1));
	System.out.println(set.contains(2));
	System.out.println(set.contains(3));
	System.out.println(set.contains(4));
	System.out.println(set.contains(17));
	System.out.println(set.contains(117));
	System.out.println(set.contains(517));
	System.out.println(set.contains(997));
	
    }
    
    /**
     * @param weights of vertices, index starting at 1
     * @return maximum total weight of WIS
     */
    public static int maxWISvalue(int[] weights) {
	int[] dp = maxWIS_helper(weights);
	return dp[dp.length - 1];
    }
    
    /**
     * @param weights of vertices, index starting at 1
     * @return {@code Set<Integer>} of vertices that sum to maxWIS
     */
    public static Set<Integer> maxWIS(int[] weights) {
	int[] dp = maxWIS_helper(weights);
	Set<Integer> set = new HashSet<>();
	int i = dp.length - 1;
	while (i > 0) {
	    if (i > 1 && dp[i-1] >= dp[i-2] + weights[i]) {
		i -= 1;
	    } else {
		set.add(i);
		i -= 2;
	    }
	}
	return set;
    }
    
    private static int[] maxWIS_helper(int[] weights) {
	if (weights[0] != 0) throw new IllegalArgumentException("weights index starts at 1");
	int[] dp = new int[weights.length];
	dp[1] = weights[1];
	
	for (int i = 2; i < weights.length; i++) {
	    dp[i] = Math.max(dp[i-1], dp[i-2] + weights[i]);
	}
	return dp;
    }

}
