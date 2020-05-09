import java.util.Arrays;
import java.util.Random;

public class ArraySort // int[]
{
	public static void main(String[] args)
	{
		// Generate Test Case
		int lowerBound = -100; 
		int upperBound = 100; 
		int size = 100; 
		Random random = new Random();
		int[] input = new int[size]; 
		for(int i = 0; i < size; i++) input[i] = random.nextInt(upperBound - lowerBound) + lowerBound; 
//		int[] input = {4,6,3,8,5,2,9,0,1,7,-1,-9,10,-5,-7,-2,-4,-8,-6,-3};
		int[] input1 = Arrays.copyOf(input, input.length);
		System.out.println(Arrays.toString(input) + "\n");
		
		// Arrays.sort() Dual-Pivot Quick Sort: O(n log(n))
		long startTime = System.nanoTime();
		Arrays.sort(input);
		long endTime = System.nanoTime();
		System.out.println(Arrays.toString(input));
		System.out.println("Arrays.sort() Time Elapsed (ns): " + (endTime-startTime) + "\n");
		
		// Merge Sort
		startTime = System.nanoTime();
		ArraySort.mergeSort(input1);
		endTime = System.nanoTime();
		System.out.println(Arrays.toString(input1));
		System.out.println("Result is: " + isSorted(input1));
		System.out.println("mergeSort() Time Elapsed (ns): " + (endTime-startTime) + " \n");
		
		//
	}
	
	public static boolean isEqual(int[] nums1, int[] nums2)
	{
		if (nums1.length != nums2.length) return false;
		for (int i = 0; i < nums1.length; i++) if (nums1[i] != nums2[i]) return false;
		return true;
	}
	
	public static boolean isSorted(int[] nums)
	{
		if (nums.length <= 1) return true;
		for (int i = 1; i < nums.length; i++) if (nums[i-1] > nums[i]) return false;
		return true;
	}
	
	// 2-way Merge Sort <= 6nlog_2(n)+6n O(NlogN)
	public static void mergeSort(int[] nums)
	{
		mergeSortHelper(nums, 0, nums.length-1);
	}
	private static void mergeSortHelper(int[] nums, int lo, int hi)
	{	
		// Base Case
		if (hi - lo < 1) return;
		
		// Recursion
		int mid = (hi-lo)/2+lo;
		mergeSortHelper(nums, lo, mid);
		mergeSortHelper(nums, mid+1, hi);
		
		// Merge
		int[] A = Arrays.copyOfRange(nums, lo, mid+1);
		int[] B = Arrays.copyOfRange(nums, mid+1, hi+1);
		int i = 0;
		int j = 0;
		for (int k = lo; k <= hi; k++)
		{
			if (j == B.length || (i < A.length && A[i] < B[j]))
			{
				nums[k] = A[i];
				i++;
			}
			else
			{
				nums[k] = B[j];
				j++;
			}
		}
	}
	
	

}
