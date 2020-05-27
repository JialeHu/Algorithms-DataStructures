package my.algorithms;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import my.data_structures.Min_Heap;
import my.utility.NumberReader;

public class ArraySort // int[]
{
	public static void main(String[] args)
	{
		// Generate Test Case
		int lowerBound = -10000; 
		int upperBound = 10000; 
		int size = 10000; 
		Random random = new Random();
		int[] input = new int[size]; 
		for(int i = 0; i < size; i++) input[i] = random.nextInt(upperBound - lowerBound) + lowerBound; 
//		int[] input = {4,6,3,8,5,2,9,0,1,7,-1,-9,10,-5,-7,-2,-4,-8,-6,-3};
		int[] input1 = Arrays.copyOf(input, input.length);
		int[] input2 = Arrays.copyOf(input, input.length);
		int[] input3 = Arrays.copyOf(input, input.length);
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
//		System.out.println(Arrays.toString(input1));
		System.out.println("Result is: " + isSorted(input1));
		System.out.println("mergeSort() Time Elapsed (ns): " + (endTime-startTime) + " \n");
		
		// Quick Sort I
		startTime = System.nanoTime();
		ArraySort.quickSortI(input2);
		endTime = System.nanoTime();
//		System.out.println(Arrays.toString(input2));
		System.out.println("Result is: " + isSorted(input2));
		System.out.println("quickSortI() Time Elapsed (ns): " + (endTime-startTime) + " \n");
		
		// Heap Sort
		startTime = System.nanoTime();
		ArraySort.heapSort(input3);
		endTime = System.nanoTime();
		System.out.println(Arrays.toString(input3));
		System.out.println("Result is: " + isSorted(input3));
		System.out.println("heapSort() Time Elapsed (ns): " + (endTime-startTime) + " \n");
		
		// Quick Sort I Test File
		try
		{
			int[] inputFile = NumberReader.int2array(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/QuickSort.txt"));
			System.out.println(Arrays.toString(inputFile) + "\n");
			startTime = System.nanoTime();
			ArraySort.quickSortI(inputFile);
			endTime = System.nanoTime();
			System.out.println(Arrays.toString(inputFile));
			System.out.println("Result is: " + isSorted(inputFile));
			System.out.println("quickSortI() Time Elapsed (ns): " + (endTime-startTime) + " \n");
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		
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
	
	// 2-way Merge Sort O(n log(n)) 6nlog_2(n)+6n
	public static void mergeSort(int[] nums)
	{
		mergeSortHelper(nums, 0, nums.length - 1);
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
	
	// Single Pivot QuickSort O(n log(n)) 2nln(n)
	public static void quickSortI(int[] nums)
	{
		quickSortIHelper(nums, 0, nums.length - 1);
	}
	private static void quickSortIHelper(int[] nums, int lo, int hi)
	{
		// Base Case
		if (hi - lo < 1) return;

		// Choose Pivot Position
		int pivot = choosePivot(nums, lo, hi);
		
		// Partition Around Pivot, Get New Pivot Position
		pivot = partition(nums, pivot, lo, hi);
		
		// Recursion
		quickSortIHelper(nums, lo, pivot - 1);
		quickSortIHelper(nums, pivot + 1, hi);
	}
	private static int choosePivot(int[] nums, int lo, int hi)
	{
//		int n1 = nums[lo];
//		int n2 = nums[(hi-lo)/2 + lo];
//		int n3 = nums[hi];
//		int max = Math.max(Math.max(n1, n2), n3);
//		int min = Math.min(Math.min(n1, n2), n3);
//		if (n1 != max && n1 != min) return lo;
//		if (n2 != max && n2 != min) return (hi-lo)/2 + lo;
//		if (n3 != max && n3 != min) return hi;
		
//		int rand = new Random().nextInt(hi - lo) + lo;

		return lo;
	}
	private static int partition(int[] nums, int pivot, int lo, int hi)
	{
		// Swap pivot with lo
		swap(nums, lo, pivot);
		// Looping and Swapping
		int i = lo + 1;
		for (int j = i; j <= hi; j++)
		{
			if (nums[j] < nums[lo])
			{
				swap(nums, i, j);
				i++;
			}
		}
		// Swap Pivot
		swap(nums, lo, i-1);
		// New Pivot Position
		return i-1;
	}
	private static void swap(int[] nums, int i1, int i2)
	{
		if (i1 == i2) return;
		int temp = nums[i1];
		nums[i1] = nums[i2];
		nums[i2] = temp;
	}
	
	public static void quickSortII()
	{
		
	}
	
	// Heap Sort O(n log(n)) 2nlog_2(n)
	public static void heapSort(int[] nums)
	{
		Min_Heap heap = new Min_Heap();
		for (int num : nums) heap.insert(num);
		for (int i = 0; i < nums.length; i++) nums[i] = heap.poll();
	}
	
}
