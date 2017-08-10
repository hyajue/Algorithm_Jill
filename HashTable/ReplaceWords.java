package HashTable;
/**
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000
 */

//HashSet
public class ReplaceWords {
	public String replaceWords(List<String> dict, String sentence) {
        if(sentence == null) return "";
        String[] token = sentence.trim().split(" ");
        Set<String> set = new HashSet<String>();
        for(String s : dict)
            set.add(s);
        StringBuilder sb = new StringBuilder();
        for(String s : token){
            String prefix = "";
            for(int i = 1; i <= s.length(); i++){
                prefix = s.substring(0, i);
                if(set.contains(prefix))
                    break;
            }
            sb.append(" " + prefix);
        }
        return sb.deleteCharAt(0).toString();
    }
}
//Trie树
public class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        String[] tokens = sentence.trim().split(" ");
        TrieNode trie = buildTrie(dict);
        return replaceWords(tokens, trie);
    }
    private String replaceWords(String[] tokens, TrieNode root){
        StringBuilder sb = new StringBuilder();
        for(String word : tokens){
            sb.append(getShortestReplacement(word, root));
            sb.append(" ");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
    private String getShortestReplacement(String word, final TrieNode root){
        StringBuilder sb = new StringBuilder();
        TrieNode temp = root;
        for(char c : word.toCharArray()){
            sb.append(c);
            if(temp.children[c - 'a'] == null)
                return word;
            else{
                if(temp.children[c - 'a'].isWord)
                    return sb.toString();
                temp = temp.children[c - 'a'];
            }
        }
        return word; //word比任何字典都短
    }
    private TrieNode buildTrie(List<String> dict){
        TrieNode root = new TrieNode(' ');
        for(String word : dict){
            TrieNode temp = root;
            for(char c : word.toCharArray()){
                if(temp.children[c - 'a'] == null){
                    temp.children[c - 'a'] = new TrieNode(c);
                }
                temp = temp.children[c - 'a'];
            }
            temp.isWord = true;
        }
        return root;
    }
    public class TrieNode{
        char val;
        TrieNode[] children;
        boolean isWord;
        
        public TrieNode(char val){
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
}

//java 8 + trie
public class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie(26);
        dict.forEach(s -> trie.insert(s));
        List<String> res = new ArrayList<String>();
        Arrays.stream(sentence.split(" ")).forEach(str -> res.add(trie.getShortestPrefix(str)));
        return res.stream().collect(Collectors.joining(" "));
    }
    class Trie{
        private int R;
        private TrieNode root;
        public Trie(int R){
            this.R = R;
            root = new TrieNode();
        }
        public String getShortestPrefix(String word){
            int len = getShortestPrefix(root, word, -1);
            return len < 1 ? word : word.substring(0, len);
        }
        private int getShortestPrefix(TrieNode root, String word, int res){
            if(root == null || word.isEmpty()) return 0;
            if(root.isWord) return res + 1;
            return getShortestPrefix(root.next[word.charAt(0) - 'a'], word.substring(1), res + 1);
        }
        public void insert(String word){
            insert(root, word);
        }
        private void insert(TrieNode root, String word){
            if(word.isEmpty()){
                root.isWord = true;
                return;
            }
            if(root.next[word.charAt(0) - 'a'] == null)
                root.next[word.charAt(0) - 'a'] = new TrieNode();
            insert(root.next[word.charAt(0) - 'a'], word.substring(1));
        }
        private class TrieNode{
            private TrieNode[] next = new TrieNode[R];
            private boolean isWord;
        }
    }
}
