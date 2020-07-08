package my.data_structures;

import java.util.Arrays;

public class BinaryTree
{
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int x) { val = x; }
		@Override
		public String toString() {return BinaryTree.toString(this);}
	}
	
	public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_traverse(root, sb);
        return sb.toString();
    }
	
	private static void serialize_traverse(TreeNode root, StringBuilder sb) {
	    if (root == null) {
	        sb.append("*,");
	        return;
	    }
	    sb.append(root.val).append(",");
	    serialize_traverse(root.left, sb);
	    serialize_traverse(root.right, sb);
	}

	public static TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        int[] idx = new int[]{0};
        return deserialize_build(vals, idx);
    }
	
	private static TreeNode deserialize_build(String[] vals, int[] idx) {
	    if (idx[0] >= vals.length) return null;
	    String str = vals[idx[0]++];
	    if (str.equals("*")) return null;
	    TreeNode node = new TreeNode(Integer.parseInt(str));
	    node.left = deserialize_build(vals, idx);
	    node.right = deserialize_build(vals, idx);
	    return node;
	}
	
	public static int sizeOf(TreeNode root) {
		if (root == null) return 0;
		return sizeOf(root.left) + sizeOf(root.right) + 1;
	}

	public static int maxDepth(TreeNode root) {
		return maxDepth_traverse(root, 0);
	}
	
	private static int maxDepth_traverse(TreeNode node, int num) {
		if (node == null) return num;    
        return Math.max(maxDepth_traverse(node.left, num+1), maxDepth_traverse(node.right, num+1));
	}
	
	public static int minDepth(TreeNode root) {
		return minDepth_traverse(root, 0);
	}
	
	private static int minDepth_traverse(TreeNode node, int num) {
		if (node == null) return num;    
        return Math.min(minDepth_traverse(node.left, num+1), minDepth_traverse(node.right, num+1));
	}
	
	public static void print(TreeNode root) {
		int depth = maxDepth(root);
        int width = (1 << depth) - 1; // 2^depth - 1
        
        String[][] output = new String[depth*2][width];
        for (String[] row : output) Arrays.fill(row, " ");
        print_traverse(root, output, 0, 0, width);
        
        String str = new String();
        for (String[] row : output) str = str.concat(Arrays.toString(row)).concat(System.lineSeparator()).replaceAll(",", "");
        System.out.println(str);
	}
	
	private static void print_traverse(TreeNode root, String[][] output, int d, int i, int e) {
        if (root == null) return;
        int mid = i + (e-i)/2;
        output[d][mid] = String.valueOf(root.val);
        
        if (root.left != null) output[d+1][mid - 1] = "/";
        if (root.right != null) output[d+1][mid + 1] = "\\";
        
        print_traverse(root.left, output, d+2, i, mid);
        print_traverse(root.right, output, d+2, 1+mid, e);
    }

	public static String toString(TreeNode root) {
		String str = serialize(root);
		return "[" + str.substring(0, str.length()-1) + "]";
	}
	
}

