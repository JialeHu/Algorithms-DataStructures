package my.data_structures;

import java.util.Vector;

public class BinarySearchTree<V> {

    /**
     * Node class for binary search tree with {@code int} as key and <V> as value,
     * with optional {@code int} weight.
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
     * 
     */
    public BinarySearchTree() {
	root = null;
	size = 0;
    }
    
    public void balance() {
	Vector<TreeNode<V>> inorder = new Vector<>();
	balance_traversal(root, inorder);
	root = balance_rebuild(inorder, 0, inorder.size()-1);
    }
    
    @SuppressWarnings("unchecked")
    private void balance_traversal(TreeNode<V> node, Vector<TreeNode<V>> nodes) {
	if (node == null) return;
	balance_traversal((TreeNode<V>) node.left, nodes); 
        nodes.add(node); 
        balance_traversal((TreeNode<V>) node.right, nodes); 
    }
    
    private TreeNode<V> balance_rebuild(Vector<TreeNode<V>> nodes, int start, int end) {
	if (start > end) return null;
	int mid = (start + end) / 2;
	TreeNode<V> node = nodes.get(mid);
	node.left = balance_rebuild(nodes, start, mid-1);
	node.right = balance_rebuild(nodes, mid+1, end);
	return node;
    }
    
    public boolean isBalanced() {
	return BinaryTrees.isBalanced(root);
    }
    
    public boolean insert(int key) {
	return insert(key, null, 0);
    }
    
    public boolean insert(int key, V value) {
	return insert(key, value, 0);
    }
    
    @SuppressWarnings("unchecked")
    public boolean insert(int key, V value, int weight) {
	if (root == null) {
	    root = new TreeNode<V>(key, value, weight);
	    size++;
	    return true;
	}
	
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
    
    public V search(int key) {
	return searchNode(key).value;
    }
    
    public boolean replace(int key, V newValue) {
	TreeNode<V> node = searchNode(key);
	if (node == null) return false;
	node.setValue(newValue);
	return true;
    }
    
    public boolean replace(int key, V newValue, int newWeight) {
	TreeNode<V> node = searchNode(key);
	if (node == null) return false;
	node.setValue(newValue);
	node.setWeight(newWeight);
	return true;
    }
    
    public boolean setWeight(int key, int weight) {
	TreeNode<V> node = searchNode(key);
	if (node == null) return false;
	node.setWeight(weight);
	return true;
    }
    
    public boolean containsKey(int key) {
        return searchNode(key) != null;
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
    
    public int size() {
	return size;
    }
    
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
	toString_traversal(root, sb);
	sb.deleteCharAt(sb.lastIndexOf(","));
	sb.append('}');
	return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    private void toString_traversal(TreeNode<V> node, StringBuilder sb) {
	if (node == null) return;
	toString_traversal((TreeNode<V>) node.left, sb);
	sb.append(node.toString()).append(',');
	toString_traversal((TreeNode<V>) node.right, sb);
    }
    
    public static void main(String[] a) {

	BinaryTrees.TreeNode root = BinaryTrees.deserialize("1,2,*,3,*,*,4,5,*,*,6,7");
	BinaryTrees.print(root);
	System.out.println(BinaryTrees.isBalanced(root));
	
	BinarySearchTree<String> bst = new BinarySearchTree<String>();
	System.out.println(bst.insert(1));
	System.out.println(bst.insert(3));
	System.out.println(bst.insert(1));
	System.out.println(bst.insert(0));
	System.out.println(bst.insert(5));
	System.out.println(bst.insert(2));
	System.out.println(bst.insert(-2));
	System.out.println(bst.insert(-5));
	bst.printKey();
	System.out.println(bst.containsKey(5));
	System.out.println(bst.search(5));
	System.out.println(bst.replace(5, "five"));
	System.out.println(bst.isBalanced());
	
	bst.balance();
	bst.printKey();
	System.out.println(bst.isBalanced());
	
	System.out.println(bst);
    }

}
