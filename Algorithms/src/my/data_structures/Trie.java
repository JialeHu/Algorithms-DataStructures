package my.data_structures;

class Trie {
    
    class TrieNode {
        private TrieNode[] links;
        private boolean isEnd;
        
        public TrieNode() {
            links = new TrieNode[26];
            isEnd = false;
        }
        
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
        
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }
        
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }
        
        public void setEnd() {
            isEnd = true;
        }
        
        public boolean isEnd() {
            return isEnd;
        }
    }
    
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) node.put(ch, new TrieNode());
            node = node.get(ch);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return true;
    }
}
