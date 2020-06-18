package my.data_structures;

public class BinaryTree
{
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		@Override
		public String toString() {return BinaryTree.toString(this);}
	}
	
	public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        traverse(root, sb);
        return sb.toString();
    }
	
	public static TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        int[] idx = new int[]{0};
        return build(vals, idx);
    }
	
	public static String toString(TreeNode root) {
		String str = serialize(root);
		return "[" + str.substring(0, str.length()-1) + "]";
	}
    
    private static void traverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("*,");
            return;
        }
        sb.append(root.val).append(",");
        traverse(root.left, sb);
        traverse(root.right, sb);
    }
    
    private static TreeNode build(String[] vals, int[] idx) {
        if (idx[0] >= vals.length) return null;
        String str = vals[idx[0]++];
        if (str.equals("*")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = build(vals, idx);
        node.right = build(vals, idx);
        return node;
    }
	
}

