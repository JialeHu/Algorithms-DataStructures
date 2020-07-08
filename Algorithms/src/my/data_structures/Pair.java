package my.data_structures;

/**
 * A simple immutable pair object that can hold two objects.
 * @author Jiale Hu
 *
 * @param <V1> type of object 1
 * @param <V2> type of object 2
 */
public class Pair<V1, V2>
{
	private final V1 obj1;
	private final V2 obj2;
	
	public Pair(V1 obj1, V2 obj2)
	{
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	/**
	 * @return first object in the pair
	 */
	public V1 get1()
	{
		return obj1;
	}

	/**
	 * @return second object in the pair
	 */
	public V2 get2()
	{
		return obj2;
	}
	
	@Override
	public String toString()
	{
		return "{" + obj1 + "=" + obj2 + "}";
	}

}
