package my.data_structures;

public class Pair<V1, V2>
{
	private V1 obj1;
	private V2 obj2;
	
	public Pair(V1 obj1, V2 obj2)
	{
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	public V1 get1()
	{
		return obj1;
	}

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
