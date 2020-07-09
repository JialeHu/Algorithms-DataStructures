package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import my.utility.Counter;
import my.utility.NumberReader;

public class TwoSum {

    public static void main(String[] args) throws FileNotFoundException {
	long[] input = NumberReader
		.long2array(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sum.txt"));

//		input = new long[]{-3, -1, 1, 2, 9, 11, 7, 6, 2};
	System.out.println("Input Size: " + input.length);

//		long target = -1397L;
//		List<int[]> list = twoSum(input, target);
//		printList(input, list);
//		System.out.println(twoSumUnique(input, target));

//		int count = 0;
//		for (int t = -10000; t <= 10000; t++)
//		{
//			if (twoSum(input, t).size() == 1) count++;
//		}
//		System.out.println(count);

//		int count = 0;
//		for (int t = -10000; t <= 10000; t++)
//		{
//			if (twoSumDistinct(input, t)) count++;
//			System.out.println(t);
//		}
//		System.out.println(count);

	ArrayList<Long> targets = new ArrayList<Long>();
	for (long t = -10000; t <= 10000; t++) {
	    targets.add(t);
	}
	System.out.println(targets.get(0) + " " + targets.get(targets.size() - 1));
	Counter counter = new Counter();
	targets.parallelStream().forEach(t -> {
	    if (twoSumDistinct(input, t)) {
		counter.count();
		System.out.println(t);
	    }
	});
	System.out.println("Number of Distinct: " + counter.getCount());

    }

    /**
     * 
     * @param nums   {@code long[]}: Array of numbers to find two numbers sum up to
     *               target
     * @param target {@code long}: As a target of two sum
     * @return {@code List<int[]>}: List of index pairs for numbers sum up to target
     */
    public static List<int[]> twoSum(long[] nums, long target) {
	HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
	List<int[]> idxList = new ArrayList<int[]>();
	for (int i = 0; i < nums.length; i++) {
	    long complement = target - nums[i];
	    if (hashMap.containsKey(complement)) {
		int[] idxPair = { hashMap.get(complement), i };
		idxList.add(idxPair);
	    } else {
		hashMap.put(nums[i], i);
	    }
	}
	return idxList;
    }

    /**
     * 
     * @param nums   {@code long[]}: Array of numbers to find two numbers sum up to
     *               target
     * @param target {@code long}: As a target of two sum
     * @return {@code boolean true} if nums contains at least one pair of distinct
     *         numbers sum up to target
     */
    public static boolean twoSumDistinct(long[] nums, long target) {
	HashSet<Long> hashSet = new HashSet<Long>();
	for (long num : nums) {
	    long complement = target - num;
	    if (hashSet.contains(complement)) {
		if (complement != num)
		    return true;
	    } else {
		hashSet.add(num);
	    }
	}
	return false;
    }

    /**
     * 
     * @param nums   {@code long[]}: Array of numbers to find two numbers sum up to
     *               target
     * @param target {@code long}: As a target of two sum
     * @return {@code boolean true} if nums contains only one pair of numbers sum up
     *         to target
     */
    public static boolean twoSumUnique(long[] nums, long target) {
	HashSet<Long> hashSet = new HashSet<Long>();
	short count = 0;
	for (long num : nums) {
	    long complement = target - num;
	    if (hashSet.contains(complement)) {
		if (++count > 1)
		    return false;
	    } else {
		hashSet.add(num);
	    }
	}
	return count == 1;
    }

    /**
     * 
     * {@summary }
     * 
     * @param nums    {@code long[]}: Array of numbers to find two numbers sum up to
     *                target
     * @param idxList {@code List<int[]>}: List of index pairs for numbers sum up to
     *                target
     */
    public static void printList(long[] nums, List<int[]> idxList) {
	System.out.println("Number of Pair: " + idxList.size());
	for (int[] idxPair : idxList) {
	    int i1 = idxPair[0];
	    int i2 = idxPair[1];
	    System.out.print(i1 + " => " + nums[i1] + "\t");
	    System.out.println(i2 + " => " + nums[i2] + "\tSum = " + (nums[i1] + nums[i2]));
	}
    }

}
