package my.data_structures;

import java.util.Arrays;

/**
 * This class contains {@code static} methods related to binary tree.
 * 
 * @author Jiale Hu
 */
public class BinaryTrees {
    /**
     * Prevent External Instantiation, Allow Inheritance
     */
    protected BinaryTrees() {
    }

    /**
     * Node class for binary tree with {@code int} as key/value.
     * 
     * @author Jiale Hu
     */
    public static class TreeNode {
	public final int key;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int key) {
	    this.key = key;
	}

	@Override
	public String toString() {
	    return BinaryTrees.toString(this);
	}
    }

    /**
     * @param root {@link TreeNode} of input binary tree
     * @return serialized {@code String} of input binary tree by pre-order traversal
     */
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
	sb.append(root.key).append(",");
	serialize_traverse(root.left, sb);
	serialize_traverse(root.right, sb);
    }

    /**
     * @param data {@code String} representation of binary tree generated from
     *             {@code serialize()}
     * @return root {@link TreeNode} of deserialized binary tree
     */
    public static TreeNode deserialize(String data) {
	String[] vals = data.split(",");
	int[] idx = new int[] { 0 };
	return deserialize_build(vals, idx);
    }

    private static TreeNode deserialize_build(String[] vals, int[] idx) {
	if (idx[0] >= vals.length)
	    return null;
	String str = vals[idx[0]++];
	if (str.equals("*"))
	    return null;
	TreeNode node = new TreeNode(Integer.parseInt(str));
	node.left = deserialize_build(vals, idx);
	node.right = deserialize_build(vals, idx);
	return node;
    }

    /**
     * @param root {@link TreeNode} of input binary tree
     * @return number of nodes in the binary tree
     */
    public static int sizeOf(TreeNode root) {
	if (root == null)
	    return 0;
	return sizeOf(root.left) + sizeOf(root.right) + 1;
    }

    /**
     * @param root {@link TreeNode} of input binary tree
     * @return maximum depth (i.e. height) of the binary tree
     */
    public static int maxDepth(TreeNode root) {
	return maxDepth_traverse(root, 0);
    }

    private static int maxDepth_traverse(TreeNode node, int num) {
	if (node == null)
	    return num;
	return Math.max(maxDepth_traverse(node.left, num + 1), maxDepth_traverse(node.right, num + 1));
    }

    /**
     * @param root {@link TreeNode} of input binary tree
     * @return minimum depth (i.e. height) of the binary tree
     */
    public static int minDepth(TreeNode root) {
	return minDepth_traverse(root, 0);
    }

    private static int minDepth_traverse(TreeNode node, int num) {
	if (node == null)
	    return num;
	return Math.min(minDepth_traverse(node.left, num + 1), minDepth_traverse(node.right, num + 1));
    }
    
    /**
     * @param root {@link TreeNode} of input binary tree
     * @return {@code true} if the left and right subtrees of every node differ in height by no more than 1
     */
    public static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private static int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        if (left == -1) return -1;
        int right = height(root.right);
        if (right == -1) return -1;
        if (left-right > 1 || right-left > 1) return -1;
        return Math.max(left,right) + 1;
    }

    /**
     * Print input binary tree in 2D.
     * 
     * @param root {@link TreeNode} of input binary tree
     */
    public static void print(TreeNode root) {
	int depth = maxDepth(root);
	if (depth == 0) {
	    System.out.println("[]");
	    return;
	}
	int width = (1 << depth) - 1; // 2^depth - 1
	
	String[][] output = new String[depth * 2 - 1][width];
	for (String[] row : output)
	    Arrays.fill(row, " ");
	print_traverse(root, output, 0, 0, width);

	String str = new String();
	for (String[] row : output)
	    str = str.concat(Arrays.toString(row)).concat(System.lineSeparator()).replaceAll(",", "");
	System.out.print(str);
    }

    private static void print_traverse(TreeNode root, String[][] output, int d, int i, int e) {
	if (root == null)
	    return;
	int mid = i + (e - i) / 2;
	output[d][mid] = String.valueOf(root.key);

	if (root.left != null)
	    output[d + 1][mid - 1] = "/";
	if (root.right != null)
	    output[d + 1][mid + 1] = "\\";

	print_traverse(root.left, output, d + 2, i, mid);
	print_traverse(root.right, output, d + 2, 1 + mid, e);
    }

    /**
     * @param root {@link TreeNode} of input binary tree
     * @return {@code String} representation of pre-order traversal of input binary
     *         tree
     */
    public static String toString(TreeNode root) {
	String str = serialize(root);
	return "[" + str.substring(0, str.length() - 1) + "]";
    }

}
