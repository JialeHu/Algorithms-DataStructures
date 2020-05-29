package my.data_structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import my.utility.NumberReader;

/**
 * Maintain middle number and statistic median for a stream of {@code int} input.
 * @author Jiale Hu
 */
public class MedianMaintainer
{
	/**
	 * Runtime Exception indicating empty MedianMaintainer
	 * @author Jiale Hu
	 */
	private class EmptyMaintainerException extends RuntimeException
	{
		private static final long serialVersionUID = 1L;
	}
	
	// Static
	public static void main(String[] args) throws FileNotFoundException
	{
		// Get Test Numbers
		int[] nums = NumberReader.int2array(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/Median.txt"));
		// Stream into Maintainer
		MedianMaintainer mm = new MedianMaintainer();
		for (int i = 0; i < nums.length; i++)
		{
			int num = nums[i];
			mm.add(num);
			System.out.println(num + " " + mm.getMiddle() + " " + mm.size());
		}
		// Check Result
		System.out.println("Maintainer Middle: " + mm.getMiddle() + " Array Middle: " + MedianMaintainer.getMiddle(nums));
		System.out.println("Maintainer Median: " + mm.getMedian() + " Array Median: " + MedianMaintainer.getMedian(nums));
		
		// Test pollMiddle() && toArray()
		while (!mm.isEmpty())
		{
			int[] array = mm.toArray();
			int arr = MedianMaintainer.getMiddle(array);
			int maintainer = mm.removeMiddle();
			if (arr != maintainer) System.out.println(maintainer + " " + arr);
		}
		System.out.println(mm.size());
	}
	
	/**
	 * If array size is even, smaller number is returned.
	 * @param nums Array of Integers
	 * @return Middle Number of Input Array
	 */
	public static int getMiddle(int[] nums)
	{
		int len = nums.length;
		int[] copy = Arrays.copyOf(nums, len);
		Arrays.sort(copy);
        return copy[(len-1)/2]; 
	}
	
	/**
	 * @param nums Array of Integers
	 * @return Statistic Median of Input Array
	 */
	public static double getMedian(int[] nums)
	{
		int len = nums.length;
		int[] copy = Arrays.copyOf(nums, len);
		Arrays.sort(copy);
		if (len%2 != 0) return (double) copy[len/2];
		else return (double) (copy[(len-1)/2] + copy[len/2]) / 2;
	}
	
	// Instance Variables
	PriorityQueue<Integer> lowHeap; // Max Heap
	PriorityQueue<Integer> highHeap; // Min Heap
	
	public MedianMaintainer()
	{
		lowHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		highHeap = new PriorityQueue<Integer>();
	}
	
	public synchronized void add(int num)
	{
		if (isEmpty() || lowHeap.size() <= highHeap.size()) lowHeap.add(num);
		else highHeap.add(num);
		if (highHeap.size() == 0 || lowHeap.size() == 0) return;
		if (lowHeap.peek() > highHeap.peek())
		{
			int temp = lowHeap.poll();
			lowHeap.add(highHeap.poll());
			highHeap.add(temp);
		}
	}
	
	/**
	 * If Maintainer size is even, smaller number is returned.
	 * @return Middle Number of Whole Maintainer
	 * @exception EmptyMaintainerException If Maintainer is empty
	 */
	public synchronized int getMiddle()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.peek();
		else if (lowHeap.size() < highHeap.size()) return highHeap.peek();
		else return lowHeap.peek();
	}
	
	/**
	 * @return Statistic Median of Whole Maintainer
	 * @exception EmptyMaintainerException If Maintainer is empty
	 */
	public synchronized double getMedian()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.peek();
		else if (lowHeap.size() < highHeap.size()) return highHeap.peek();
		else return (double) (lowHeap.peek() + highHeap.peek()) / 2;
	}
	
	/**
	 * If Maintainer size is even, smaller number is returned and removed. ORDER OF REMOVAL IS NOT GUARANTEED YET.
	 * @return Middle Number of Whole Maintainer
	 * @exception EmptyMaintainerException If Maintainer is empty
	 */
	public synchronized int removeMiddle()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		int output;
		if (lowHeap.size() > highHeap.size()) output = lowHeap.poll();
		else if (lowHeap.size() < highHeap.size()) output = highHeap.poll();
		else output = lowHeap.poll();
		
		if (highHeap.size() == 0 || lowHeap.size() == 0) return output;
		if (lowHeap.peek() > highHeap.peek())
		{
			int temp = lowHeap.poll();
			lowHeap.add(highHeap.poll());
			highHeap.add(temp);
		}
		return output;
	}
	
	/**
	 * @return Size of Maintainer
	 */
	public synchronized int size()
	{
		return lowHeap.size() + highHeap.size();
	}
	
	/**
	 * @return {@code true} if Maintainer is empty
	 */
	public synchronized boolean isEmpty()
	{
		return lowHeap.isEmpty() && highHeap.isEmpty();
	}
	
	/**
	 * Clear Maintainer
	 */
	public synchronized void clear()
	{
		lowHeap.clear();
		highHeap.clear();
	}
	
	/**
	 * @return {@code true} if Maintainer is empty
	 */
	public synchronized int[] toArray()
	{
		int[] output = new int[size()];
		int i = 0;
		for (int num : lowHeap)
		{
			output[i] = num;
			i++;
		}
		for (int num : highHeap)
		{
			output[i] = num;
			i++;
		}
		return output;
	}
	
	@Override
	public synchronized String toString()
	{
		return lowHeap.toString() + highHeap.toString();
	}
	
}
