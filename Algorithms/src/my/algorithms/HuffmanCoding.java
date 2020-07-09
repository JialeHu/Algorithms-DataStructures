package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import my.data_structures.BinaryTrees;
import my.data_structures.BinaryTrees.TreeNode;
import my.data_structures.Pair;

public class HuffmanCoding {

    public static void main(String[] args) throws FileNotFoundException {
	/*
	 * [number_of_symbols] [weight of symbol #1] [weight of symbol #2]
	 */
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/huffman.txt"));
	Scanner sc = new Scanner(file);
	int numSymbols = sc.nextInt();
	List<Integer> weights = new ArrayList<>();
	while (sc.hasNextInt()) {
	    weights.add(sc.nextInt());
	}
	sc.close();
	System.out.println(weights);
	System.out.println(weights.size() + " " + numSymbols);

	TreeNode root = huffmanCoding(weights);
	System.out.println("maxLength = maxDepth - 1 = " + (BinaryTrees.maxDepth(root) - 1));
	System.out.println("minLength = minDepth - 1 = " + (BinaryTrees.minDepth(root) - 1));
	System.out.println("treeSize = " + BinaryTrees.sizeOf(root));

    }

    public static TreeNode huffmanCoding(List<Integer> weights) {
	PriorityQueue<Pair<Integer, TreeNode>> heap = new PriorityQueue<>(weights.size(),
		(p1, p2) -> Integer.compare(p1.get1(), p2.get1()));
	weights.stream().forEach(weight -> {
	    heap.offer(new Pair<Integer, TreeNode>(weight, null));
	});
	helper(heap);
	return heap.peek().get2();
    }

    private static void helper(PriorityQueue<Pair<Integer, TreeNode>> heap) {
	if (heap.size() == 1)
	    return;

	Pair<Integer, TreeNode> smaller = heap.poll();
	Pair<Integer, TreeNode> larger = heap.poll();
	int weightSum = smaller.get1() + larger.get1();
	TreeNode node = new TreeNode(weightSum);
	node.left = (smaller.get2() == null) ? new TreeNode(smaller.get1()) : smaller.get2();
	node.right = (larger.get2() == null) ? new TreeNode(larger.get1()) : larger.get2();
	heap.offer(new Pair<Integer, TreeNode>(weightSum, node));

	helper(heap);
    }

}
