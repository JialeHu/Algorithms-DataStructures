package my.data_structures;

import java.util.ArrayList;

public class Min_Heap
{
	private class EmptyHeapException extends RuntimeException
	{
		private static final long serialVersionUID = 1L;
	}
	
	ArrayList<Integer> array;

	public Min_Heap()
	{
		this.array = new ArrayList<Integer>();
		
	}
	
	public void insert(int num)
	{
		array.add(num);
		int idx = array.size()-1;
		int parentIdx = idx / 2;
		while (array.get(parentIdx) > num)
		{
			swap(idx, parentIdx);
		}
	}
	
	public int peek() throws EmptyHeapException
	{
		if (array.isEmpty()) throw new EmptyHeapException();
		return array.get(0);
	}
	
	public int extract() throws EmptyHeapException
	{
		if (array.isEmpty()) throw new EmptyHeapException();
		int min = array.get(0);
		int len = array.size();
		if (len == 1)
		{
			array.clear();
			return min;
		}
		// Swap First with Last
		swap(0, len-1);
		array.remove(len-1);
		len--;
		int i = 0;
		while ((2*i < len && array.get(i) > array.get(2*i)) || (2*i+1 < len && array.get(i) > array.get(2*i+1)))
		{
			// Swap with Smaller Child
			if (2*i+1 >= len || array.get(2*i) < array.get(2*i+1))
			{
				swap(i, 2*i);
				i = 2*i;
			} else
			{
				swap(i, 2*i+1);
				i = 2*i+1;
			}
		}
		return min;
	}
	
	public boolean isEmpty()
	{
		return array.isEmpty();
	}
	
	@Override
	public String toString()
	{
		String str = "-----\n";
		int col = 1;
		for (int i = 0; i < array.size(); i++)
		{
			str += array.get(i);
			if (i == col-1 || i == array.size()-1)
			{
				str += "\n";
				col += 2*col;
			}
		}
		str += "-----";
		return str;
	}
	
	private void swap(int idx1, int idx2)
	{
		int temp = array.get(idx1);
		array.set(idx1, array.get(idx2));
		array.set(idx2, temp);
	}

	public static void main(String[] args)
	{
		Min_Heap heap = new Min_Heap();
		heap.insert(1);
		System.out.println(heap);
		heap.insert(0);
		heap.insert(2);
		heap.insert(5);
		heap.insert(6);
		heap.insert(3);
		heap.insert(4);
		System.out.println(heap);
		while (!heap.isEmpty())
		{
			System.out.println(heap.extract());
		}
	}

}
