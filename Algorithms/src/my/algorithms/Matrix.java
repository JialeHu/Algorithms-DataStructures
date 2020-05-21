package my.algorithms;
import java.util.Arrays;

public class Matrix
{

	public static void main(String[] args)
	{
		int[][] matrix = zeros(5, 5);
		System.out.println(Matrix.toString(matrix));
		setValue(matrix, 1);
		System.out.println(Matrix.toString(matrix));
		
		int[][] mat1 = strToMat("1,2,4;-3,0,7;9,1,5;");
		int[][] mat2 = strToMat("-1,3;-3,1;1,0;");
		System.out.println(toString(multiply(mat1, mat2)));
		
	}
	
	public static String toString(int[][] matrix)
	{
		String str = "[\n";
		for (int[] row : matrix) str += " " + Arrays.toString(row) + "\n";
		str += "]";
		return str;
	}
	
	public static int[][] zeros(int r, int c)
	{
		return new int[r][c];
	}
	
	public static void setValue(int[][] matrix, int val)
	{
		for (int[] row : matrix) Arrays.fill(row, val);
	}
	
	public static int[][] strToMat(String str)
	{
		int r = 0;
		int c = 0;
		for (int i = 0; i < str.length(); i++) if (str.charAt(i) == ';') r++;
		for (int i = 0; i < str.length(); i++) 
		{
			if (str.charAt(i) == ';') 
			{
				c++;
				break;
			}
			if (str.charAt(i) == ',') c++;
		}
		int[][] mat = new int[r][c];
		int cur = 0;
		int pre = 0;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				for (; cur < str.length(); cur++)
				{
					char ch = str.charAt(cur);
					if (ch == ',' || ch == ';') 
					{
						mat[i][j] = Integer.parseInt(str.substring(pre, cur));
						pre = ++cur;
						break;
					}
				}
			}
		}
		System.out.println(toString(mat));
		return mat;
	}
	
	public static int[] matRowToVec(int[][] matrix, int row)
	{
		if (row >= matrix.length) throw new IllegalArgumentException("Row Number Exceeds Matrix Size");
		return Arrays.copyOf(matrix[row], matrix[row].length);
	}
	
	public static int[] matColToVec(int[][] matrix, int col)
	{
		if (matrix.length == 0) return null;
		if (col >= matrix[0].length) throw new IllegalArgumentException("Column Number Exceeds Matrix Size");
		int[] vec = new int[matrix.length];
		for (int i = 0; i < matrix.length; i++) vec[i] = matrix[i][col];
		return vec;
	}
	
	public static int dotProduct(int[] vec1, int[] vec2)
	{
		if (vec1.length != vec2.length) throw new IllegalArgumentException("Vector Not in Same Length");
		int res = 0;
		for (int i = 0; i < vec1.length; i++) res += vec1[i] * vec2[i];
		return res;
	}

	// Iterative O(N^3)
	public static int[][] multiply(int[][] mat1, int[][] mat2)
	{
		int r1 = mat1.length;
		int r2 = mat2.length;
		if (r1 == 0 || r2 == 0) return null;
		int c2 = mat2[0].length;
		
		int[][] res = new int[r1][c2];
		for (int r = 0; r < r1; r++)
		{
			for (int c = 0; c < c2; c++)
			{
				res[r][c] = dotProduct(matRowToVec(mat1, r), matColToVec(mat2, c));
			}
		}
		return res;
	}
	
	
	
}
