package my.data_structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

import my.utility.NumberReader;

public class MedianMaintainer
{
	/**
	 * 
	 * @author Jiale Hu
	 * {@summary Runtime Exception indicating empty MedianMaintainer}
	 *
	 */
	private class EmptyMaintainerException extends RuntimeException
	{
		private static final long serialVersionUID = 1L;
	}
	
	PriorityQueue<Integer> lowHeap; // Max Heap
	PriorityQueue<Integer> highHeap; // Min Heap
	
	public MedianMaintainer()
	{
		lowHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		highHeap = new PriorityQueue<Integer>();
	}
	
	public void add(int num)
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
	 * 
	 * @return Middle Number of Whole Maintainer
	 * @exception EmptyMaintainerException
	 * @implNote If Maintainer size is even, smaller number is returned.
	 * 
	 */
	public int peekMiddle()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.peek();
		else if (lowHeap.size() < highHeap.size()) return highHeap.peek();
		else return lowHeap.peek();
	}
	
	/**
	 * 
	 * @return Statistic Median of Whole Maintainer
	 * @exception EmptyMaintainerException
	 * 
	 */
	public double peekMedian()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.peek();
		else if (lowHeap.size() < highHeap.size()) return highHeap.peek();
		else return (double) (lowHeap.peek() + highHeap.peek()) / 2;
	}
	
	/**
	 * 
	 * @return Middle Number of Whole Maintainer
	 * @exception EmptyMaintainerException
	 * @implNote If Maintainer size is even, smaller number is returned and removed.
	 * 
	 */
	// NOT FULLY IMPLEMENTED
	public int pollMiddle()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.poll();
		else if (lowHeap.size() < highHeap.size()) return highHeap.poll();
		else return lowHeap.poll();
	}
	
	public int size()
	{
		return lowHeap.size() + highHeap.size();
	}
	
	public boolean isEmpty()
	{
		return lowHeap.isEmpty() && highHeap.isEmpty();
	}
	
	public void clear()
	{
		lowHeap.clear();
		highHeap.clear();
	}
	
	@Override
	public String toString()
	{
		return lowHeap.toString() + highHeap.toString();
	}

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
			System.out.println(num + " " + mm.peekMiddle() + " " + mm.size());
		}
		while (!mm.isEmpty())
		{
			System.out.println(mm.pollMiddle());
		}
	}
	
}
