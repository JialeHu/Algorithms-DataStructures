package my.data_structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

import my.algorithms.IntReader;

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
	 * @return Statistic Median of Whole Maintainer
	 * @exception EmptyMaintainerException
	 * 
	 */
	public int peekMedian()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.peek();
		else if (lowHeap.size() < highHeap.size()) return highHeap.peek();
		else return (lowHeap.peek() + highHeap.peek()) / 2;
//		return lowHeap.peek();
	}
	
	/**
	 * 
	 * @return Statistic Median of Whole Maintainer
	 * @exception EmptyMaintainerException
	 * @implNote Removes median from maintainer. 
	 * If median is constituted by two numbers in the maintainer (i.e. Even maintainer size), both numbers are removed.
	 * 
	 */
	public int pollMedian()
	{
		if (isEmpty()) throw new EmptyMaintainerException();
		if (lowHeap.size() > highHeap.size()) return lowHeap.poll();
		else if (lowHeap.size() < highHeap.size()) return highHeap.poll();
		else return (lowHeap.poll() + highHeap.poll()) / 2;
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
		int[] nums = IntReader.int2array(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/Median.txt"));
		// Stream into Maintainer
		MedianMaintainer mm = new MedianMaintainer();
		for (int i = 0; i < nums.length; i++)
		{
			int num = nums[i];
			mm.add(num);
			System.out.println(num + " " + mm.peekMedian() + " " + mm.size());
		}
	}
	
}
