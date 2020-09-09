package my.data_structures;

import java.util.HashMap;

/**
 * Least Frequently Used cache, when tie occurs, Least Recently Used element is removed.
 * @author Jiale Hu
 */
public class LFUCache {
    
    private class Node {
        int key, val, freq;
        Node prev, next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 0;
        }
        
        @Override
        public String toString() {
            return "(" + key + "=" + val + "=" + freq + ")";
        }
    }
    
    private class DLList {
        Node head, tail;
        int size;
        
        public DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        public void addFirst(Node node) {
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
            size++;
        }
        
        public void remove(Node node) {
            if (size == 0) return;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        public Node removeLast() {
            if (size == 0) return null;
            Node last = tail.prev;
            tail.prev = last.prev;
            tail.prev.next = tail;
            size--;
            return last;
        }
       
        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            Node node = head.next;
            while (node != tail) {
        	sb.append(node.toString());
        	node = node.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }
    
    private HashMap<Integer, DLList> freqs; // {frequency = DoubleLinkedList}
    private HashMap<Integer, Node> nodes; // {key = Node}
    private int capacity, minFreq;

    public LFUCache(int capacity) {
	if (capacity < 0) throw new IllegalArgumentException("Capacity must not be negative");
        this.nodes = new HashMap<>(capacity);
        this.freqs = new HashMap<>(capacity);
        this.freqs.put(0, new DLList());
        this.capacity = capacity;
        this.minFreq = 0;
    }
    
    public int get(int key) {
        if (!nodes.containsKey(key)) return -1;
        Node node = nodes.get(key);
        update(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (this.capacity == 0) return;

        // Replace Value
        if (nodes.containsKey(key)) {
            Node node = nodes.get(key);
            node.val = value;
            update(node);
            return;
        }
        
        // Remove Last Node
        if (nodes.size() == capacity) {
            removeEldest();
        }
        
        // Add New Node
        Node newNode = new Node(key, value);
        nodes.put(key, newNode);
        freqs.get(0).addFirst(newNode);
        minFreq = 0;
    }
    
    private void update(Node node) {
        DLList preList = freqs.get(node.freq);
        preList.remove(node);
        if (node.freq == minFreq && preList.size == 0) minFreq++;
        node.freq++;
        if (!freqs.containsKey(node.freq)) freqs.put(node.freq, new DLList());
        freqs.get(node.freq).addFirst(node);
    }
    
    private Node removeEldest() {
        DLList list = freqs.get(minFreq);
        Node rm = list.removeLast();
        nodes.remove(rm.key);
        if (list.size == 0) minFreq++;
        return rm;
    }
}
