package my.data_structures;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * An implementation of Binary Search Tree with {@code int} keys, {@code <V>} values, 
 * and optional {@code int} weights (i.e. search frequencies) for optimal BST.
 * 
 * @author Jiale Hu
 * @param <V> value corresponding to keys
 */
public class BinarySearchTree<V> {

    /**
     * Node class for binary search tree with {@code int} as key and {@code <V>} as value,
     * with optional {@code int} weight. Default weight is 1)
     * 
     * @author Jiale Hu
     * @param <V> Object associated with given {@code int} key
     */
    public static class TreeNode<V> extends BinaryTrees.TreeNode {
	private V value;
	private int weight;

	public TreeNode(int key, V value) {
	    super(key);
	    this.value = value;
	    this.weight = 1;
	}
	
	public TreeNode(int key, V value, int weight) {
	    super(key);
	    this.value = value;
	    this.weight = weight;
	}

	public V getValue() {
	    return value;
	}
	
	public void setValue(V value) {
	    this.value = value;
	}

	public int getWeight() {
	    return weight;
	}

	public void setWeight(int weight) {
	    this.weight = weight;
	}
	
	@Override 
	public String toString() {
	    return key + "=(" + value + "," + weight + ")";
	}
    }
    
    private TreeNode<V> root;
    private int size;
    
    /**
     * Initialize BST.
     */
    public BinarySearchTree() {
	root = null;
	size = 0;
    }
    
    /**
     * Check if BST contains a key.
     * @param key to be checked
     * @return {@code true} if key is in the BST
     */
    public boolean containsKey(int key) {
        return searchNode(key) != null;
    }

    /**
     * Insert a key without value and weight into BST.
     * (Default weight is 1)
     * @param key to be inserted
     * @return {@code false} if key is already in BST
     */
    public boolean insert(int key) {
        return insert(key, null, 1);
    }

    /**
     * Insert a key and value without weight into BST.
     * (Default weight is 1)
     * @param key to be inserted
     * @param value to be inserted
     * @return {@code false} if key is already in BST
     */
    public boolean insert(int key, V value) {
        return insert(key, value, 1);
    }

    /**
     * Insert a key with value and weight into BST.
     * @param key to be inserted
     * @param value to be inserted
     * @param weight to be inserted
     * @return {@code false} if key is already in BST
     */
    @SuppressWarnings("unchecked")
    public boolean insert(int key, V value, int weight) {
        // First Node
        if (root == null) {
            root = new TreeNode<V>(key, value, weight);
            size++;
            return true;
        }
        // Traversal
        TreeNode<V> node = this.root;
        while (true) {
            if (key < node.key) {
        	if (node.left == null) {
        	    node.left = new TreeNode<V>(key, value, weight);
        	    break;
        	}
        	node = (TreeNode<V>) node.left;
            } else if (key > node.key) {
        	if (node.right == null) {
        	    node.right = new TreeNode<V>(key, value, weight);
        	    break;
        	}
        	node = (TreeNode<V>) node.right;
            }
            else return false;
        }
        size++;
        return true;
    }

    /**
     * Search for a key in BST.
     * @param key to be searched
     * @return value corresponding to the key, or {@code null} if key or value does not exist in BST
     */
    public V search(int key) {
        return searchNode(key).value;
    }

    /**
     * Replace value of a key in BST.
     * @param key to be replaced
     * @param newValue to replace existing value with the key
     * @return {@code false} if the key does not exist in BST
     */
    public boolean replace(int key, V newValue) {
        TreeNode<V> node = searchNode(key);
        if (node == null) return false;
        node.setValue(newValue);
        return true;
    }

    /**
     * Replace value of a key in BST.
     * @param key to be replaced
     * @param newValue to replace existing value with the key
     * @param newWeight to replace existing weight with the key
     * @return {@code false} if the key does not exist in BST
     */
    public boolean replace(int key, V newValue, int newWeight) {
        TreeNode<V> node = searchNode(key);
        if (node == null) return false;
        node.setValue(newValue);
        node.setWeight(newWeight);
        return true;
    }

    /**
     * Set weight corresponding to a key in BST.
     * @param key to be replaced
     * @param weight to be set for the key
     * @return {@code false} if the key does not exist in BST
     */
    public boolean setWeight(int key, int weight) {
        TreeNode<V> node = searchNode(key);
        if (node == null) return false;
        node.setWeight(weight);
        return true;
    }

    /**
     * @param key of {@link TreeNode} to be searched
     * @return {@link TreeNode}
     */
    @SuppressWarnings("unchecked")
    private TreeNode<V> searchNode(int key) {
        TreeNode<V> node = this.root;
        while (node != null) {
            if (key < node.key) node = (TreeNode<V>) node.left;
            else if (key > node.key) node = (TreeNode<V>) node.right;
            else break;
        }
        return node;
    }
    
    /**
     * Get keys of the BST as a {@code Set<Integer>}.
     * @return {@code Set<Integer>} of keys
     */
    public Set<Integer> keySet() {
	Set<Integer> set = new HashSet<>();
	keySet_traverse(root, set);
	return set;
    }
    
    @SuppressWarnings("unchecked")
    private void keySet_traverse(TreeNode<V> node, Set<Integer> set) {
	if (node == null) return;
	keySet_traverse((TreeNode<V>) node.left, set);
	set.add(node.key);
	keySet_traverse((TreeNode<V>) node.right, set);
    }

    /**
     * Check if the BST is empty.
     * @return {@code true} if the size of BST is zero
     */
    public boolean isEmpty() {
	return size == 0;
    }
    
    /**
     * @return size of the BST
     */
    public int size() {
        return size;
    }

    /**
     * Check if BST is balanced.
     * (a binary tree in which the left and right subtrees of every node differ in height by no more than 1)
     * @return {@code true} if BST is balanced
     */
    public boolean isBalanced() {
        return BinaryTrees.isBalanced(root);
    }

    /**
     * Balance BST.
     * (a binary tree in which the left and right subtrees of every node differ in height by no more than 1)
     */
    public void balance() {
	// Get a vector of nodes inorder
	Vector<TreeNode<V>> inorder = new Vector<>();
	balance_traverse(root, inorder);
	root = balance_rebuild(inorder, 0, inorder.size()-1);
    }
    
    @SuppressWarnings("unchecked")
    private void balance_traverse(TreeNode<V> node, Vector<TreeNode<V>> nodes) {
	if (node == null) return;
	balance_traverse((TreeNode<V>) node.left, nodes); 
        nodes.add(node); 
        balance_traverse((TreeNode<V>) node.right, nodes); 
    }
    
    private TreeNode<V> balance_rebuild(Vector<TreeNode<V>> nodes, int start, int end) {
	if (start > end) return null;
	int mid = (start + end) / 2;
	TreeNode<V> node = nodes.get(mid);
	node.left = balance_rebuild(nodes, start, mid-1);
	node.right = balance_rebuild(nodes, mid+1, end);
	return node;
    }
    
    /**
     * Calculate current total search cost (weight multiply by level of searches) 
     * based on weights (i.e. search frequencies).
     * @return minimum search cost of BST
     */
    public int BSTcost() {
	return BSTcost_traverse(root, 1);
    }
    
    @SuppressWarnings("unchecked")
    private int BSTcost_traverse(TreeNode<V> node, int level) {
	if (node == null) return 0;
	int leftCost = BSTcost_traverse((TreeNode<V>) node.left, level + 1);
	int rightCost = BSTcost_traverse((TreeNode<V>) node.right, level + 1);
	return leftCost + rightCost + node.getWeight() * level;
    }
    
    /**
     * Check if the BST is optimal.
     * @return {@code true} if current BST cost equals to the optimal BST cost
     */
    public boolean isOptimal() {
	return BSTcost() == optimalBSTcost();
    }
    
    /**
     * Make BST optimal by minimizing search time based on weights (i.e. search frequencies).
     */
    public void optimalBST() {
	// Get a vector of nodes inorder
	Vector<TreeNode<V>> inorder = new Vector<>();
	balance_traverse(root, inorder);
	// Get DP
	int[][] dp = optimalBST_dp();
	int len = dp.length;
	// Reconstruct from DP
	root = optimalBST_rebuild(inorder, dp, 0, len-1);
    }
    
    /**
     * Calculate minimum total search cost (weight multiply by level of searches) 
     * based on weights (i.e. search frequencies).
     * @return minimum search cost of BST
     */
    public int optimalBSTcost() {
	int[][] dp = optimalBST_dp();
	if (dp.length == 0) return 0;
	int len = dp[0].length;
	return dp[0][len-1];
    }
    
    // O(n^3)
    private int[][] optimalBST_dp() {
	// Get a vector of nodes inorder
	Vector<TreeNode<V>> inorder = new Vector<>();
	balance_traverse(root, inorder);
	// Initialize DP memory
	int len = inorder.size();
	int[][] dp = new int[len][len];
	// Build solution (0 <= i <= j < len, j = i + s)
	for (int s = 0; s < len; s++) {
	    for (int i = 0; i < len; i++) {
		if (i + s >= len) break;
		// Sum of weight from i to j
		int weightSum = 0;
		for (int k = i; k <= i+s; k++) {
		    weightSum += inorder.get(k).getWeight();
		}
		// Update DP (i <= root <= j)
		int min = Integer.MAX_VALUE;
		for (int r = i; r <= s+i; r++) {
		    int leftW = (r > i) ? dp[i][r-1] : 0;
		    int rightW = (r < i+s) ? dp[r+1][i+s] : 0;
		    min = Math.min(min, weightSum + leftW + rightW);
		}
		dp[i][i+s] = min;
	    }
	}
	return dp;
    }
    
    private TreeNode<V> optimalBST_rebuild(Vector<TreeNode<V>> inorder, int[][] dp, int i, int j) {
	// Sum of weight from i to j
	int weightSum = 0;
	for (int k = i; k <= j; k++) {
	    weightSum += inorder.get(k).getWeight();
	}
	// Reconstruct solution from DP
	for (int r = i; r <= j; r++) {
	    int leftW = (r > i) ? dp[i][r-1] : 0;
	    int rightW = (r < j) ? dp[r+1][j] : 0;
	    if (dp[i][j] == weightSum + leftW + rightW) {
		TreeNode<V> node = inorder.get(r);
		node.left = optimalBST_rebuild(inorder, dp, i, r-1);
		node.right = optimalBST_rebuild(inorder, dp, r+1, j);
		return node;
	    }
	}
	return null;
    }
    
    /**
     * Print the BST with keys in 2D.
     */
    public void printKey() {
	BinaryTrees.print(root);
    }
    
    /**
     * @return inorder traversal of BST in {@code String}
     */
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append('{');
	toString_traverse(root, sb);
	if (sb.length() > 1) sb.deleteCharAt(sb.lastIndexOf(","));
	sb.append('}');
	return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    private void toString_traverse(TreeNode<V> node, StringBuilder sb) {
	if (node == null) return;
	toString_traverse((TreeNode<V>) node.left, sb);
	sb.append(node.toString()).append(',');
	toString_traverse((TreeNode<V>) node.right, sb);
    }
    
    // Testing
//    public static void main(String[] a) {
//
//	BinaryTrees.TreeNode root = BinaryTrees.deserialize("1,2,*,3,*,*,4,5,*,*,6,7");
//	BinaryTrees.print(root);
//	System.out.println(BinaryTrees.isBalanced(root));
//	
//	BinarySearchTree<String> bst = new BinarySearchTree<String>();
//	System.out.println("is balanced: " + bst.isBalanced());
//	System.out.println(bst);
//	bst.printKey();
//	System.out.println(bst.insert(1, "one", 5));
//	System.out.println("is balanced: " + bst.isBalanced());
//	bst.balance();
//	bst.optimalBST();
//	bst.printKey();
//	
//	System.out.println(bst.insert(1, "one", 5));
//	System.out.println(bst.insert(2, "two", 40));
//	System.out.println(bst.insert(3, "three", 8));
//	System.out.println(bst.insert(4, "four", 4));
//	System.out.println(bst.insert(5, "five", 10));
//	System.out.println(bst.insert(6, "six", 10));
//	System.out.println(bst.insert(7, "seven", 23));
//	bst.printKey();
//	System.out.println("is balanced: " + bst.isBalanced());
//	System.out.println("BST cost: " + bst.BSTcost());
//	
//	System.out.println("containsKey 5: " + bst.containsKey(5));
//	System.out.println("search 5: " + bst.search(5));
//	System.out.println("replace 5: " + bst.replace(5, "five", 10));
//	
//	bst.balance();
//	bst.printKey();
//	System.out.println("is balanced: " + bst.isBalanced());
//	System.out.println("BST cost: " + bst.BSTcost());
//	
//	System.out.println("optimal BST cost: " + bst.optimalBSTcost());
//	
//	bst.optimalBST();
//	bst.printKey();
//	System.out.println("BST cost: " + bst.BSTcost());
//	System.out.println("isOptimal: " + bst.isOptimal());
//	
//	bst = new BinarySearchTree<String>();
//	System.out.println(bst.insert(1, "one", 20));
//	System.out.println(bst.insert(2, "two", 5));
//	System.out.println(bst.insert(3, "three", 17));
//	System.out.println(bst.insert(4, "four", 10));
//	System.out.println(bst.insert(5, "five", 20));
//	System.out.println(bst.insert(6, "six", 3));
//	System.out.println(bst.insert(7, "seven", 25));
//	bst.printKey();
//	System.out.println("BST cost: " + bst.BSTcost());
//	
//	bst.balance();
//	bst.printKey();
//	System.out.println("BST cost: " + bst.BSTcost());
//	
//	bst.optimalBST();
//	bst.printKey();
//	System.out.println("BST cost: " + bst.BSTcost());
//	System.out.println("isOptimal: " + bst.isOptimal());
//	System.out.println(bst.keySet());
//	
//	System.out.println(bst);
//    }

}
