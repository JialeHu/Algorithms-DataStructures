package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Inversion
{

	public static void main(String[] args) throws FileNotFoundException
	{
		int[] array = NumberReader.int2array(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/IntegerArray.txt")); 
//		int[] array = new int[]{1,3,5,2,4,6};
		System.out.println("Array Loaded");
		System.out.println(inversions(array));
	}
	
	public static long inversions(int[] nums)
	{
		return inversionsHelper(nums, 0, nums.length-1);
	}
	private static long inversionsHelper(int[] nums, int lo, int hi)
	{	
		// Base Case
		if (hi - lo < 1) return 0;
		
		// Recursion
		int mid = (hi-lo)/2+lo;
		long n1 = inversionsHelper(nums, lo, mid);
		long n2 = inversionsHelper(nums, mid+1, hi);
		
		// MergeSort and Count
		int[] A = Arrays.copyOfRange(nums, lo, mid+1);
		int[] B = Arrays.copyOfRange(nums, mid+1, hi+1);
		int i = 0;
		int j = 0;
		long n3 = 0;
		for (int k = lo; k <= hi; k++)
		{
			if (j == B.length || (i < A.length && A[i] < B[j]))
			{
				nums[k] = A[i];
				i++;
			}
			else
			{
				if (i != A.length) n3 += A.length - i;
				nums[k] = B[j];
				j++;
			}
		}
		return n1 + n2 + n3;
	}

}
