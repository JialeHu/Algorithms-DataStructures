package my.algorithms;
import java.math.BigInteger;
import java.util.Random;

public class Arithmetic
{
	public static void main(String[] args)
	{
		// Generate Test Case
		int length1 = 100; 
		int length2 = 100; 
		Random random = new Random();
		String input1 = "";
		String input2 = "";
		for(int i = 0; i < length1; i++) input1 += Integer.toString(random.nextInt(10));
		for(int i = 0; i < length2; i++) input2 += Integer.toString(random.nextInt(10)); 
//		String input1 = "31415926535897932384626433832795028841971693993751058209749445921";
//		String input2 = "27182818284590452353602874713526624977572470936999595749669676271";
		System.out.println(input1 + "\n x\n" + input2 + "\n =\n");
		
		// BigInteger
		long startTime = System.nanoTime();
		BigInteger n1 = new BigInteger(input1);
		BigInteger n2 = new BigInteger(input2);
		String res0 = n1.multiply(n2).toString();
		long endTime = System.nanoTime();
		System.out.println(res0);
		System.out.println("BigInteger Time Elapsed (ns): " + (endTime-startTime) + "\n");

		// Karatsuba Multiplication
		startTime = System.nanoTime();
		String res1 = karatsuba(input1, input2);
		endTime = System.nanoTime();
		System.out.println(res1);
		System.out.println("Result is: " + isEqual(res0, res1));
		System.out.println("Karatsuba Time Elapsed (ns): " + (endTime-startTime) + "\n");

	}
	
	public static boolean isEqual(String num1, String num2)
	{
		if (num1.length() != num2.length()) return false;
		for (int i = 0; i < num1.length(); i++) if (num1.charAt(i) != num2.charAt(i)) return false;
		return true;
	}

	// O(n^(log2(3))) = O(n^1.59)
	public static String karatsuba(String num1, String num2)
	{
		// Padding 0 until even length
		int diff = num1.length() - num2.length();
		if (diff > 0)
		{
			for (int i = 0; i < diff; i++) num2 = "0" + num2;
		}
		else if (diff < 0)
		{
			diff = -diff;
			for (int i = 0; i < diff; i++) num1 = "0" + num1;
		}
		int n = Math.max(num1.length(), num2.length());
		if (n % 2 != 0)
		{
			num1 = "0" + num1;
			num2 = "0" + num2;
			n++;
		}
		// Base case solve by int
		if (n <= 2) {
			int n1 = Integer.parseInt(num1);
			int n2 = Integer.parseInt(num2);
			return Integer.toString(n1 * n2);
		}
		// Substrings
		int mid = n/2;
		String a = num1.substring(0, mid);
		String b = num1.substring(mid);
		String c = num2.substring(0, mid);
		String d = num2.substring(mid);
		// Recursive calls
		String ac = karatsuba(a, c);
		String bd = karatsuba(b, d);
		String abcd = karatsuba(add(a, b), add(c, d));
		String adbc = add(abcd, "-"+add(ac, bd));   
		// Padding 0
		for (int i = 0; i < n; i++) ac += "0";
		for (int i = 0; i < mid; i++) adbc += "0";
		// Assemble Ans
		return add(ac, add(adbc, bd));
	}

	public static String add(String num1, String num2)
	{
		BigInteger n1 = new BigInteger(num1);
		BigInteger n2 = new BigInteger(num2);
		return n1.add(n2).toString();
	}

}
