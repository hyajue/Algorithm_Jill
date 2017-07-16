package DFS;
/**
* Given a 2D board and a list of words from the dictionary, find all words in the board.
* 
* Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
* 
* For example,
* Given words = ["oath","pea","eat","rain"] and board =
* 
* [
*   ['o','a','a','n'],
*   ['e','t','a','e'],
*   ['i','h','k','r'],
*   ['i','f','l','v']
* ]
* Return ["eat","oath"].
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
*/

/*
复杂度
时间：O(mn*4^k) 空间：O(nm), where k is the max length of word, nm is the board size
对每个点都要作为起始点dfs，对于每个起始点，拓展一次有四个可能性(四个邻居)，要拓展k次(word最大长度为k)
思路：trie + dfs
要用trie，拿trie来dfs
先建trie，然后在board里搜所有trie里的word
如果跟Word Search做法一样，还按照DFS回溯的方法，逐个检查每个word是否在board里，显然效率是比较低的。
我们可以利用Trie数据结构，也就是前缀树。然后dfs时，如果当前形成的单词不在Trie里，就没必要继续dfs下去了。
如果当前字符串在trie里，就说明board可以形成这个word。
这道题很好的体现了Trie的优势：不用Trie, 我们就得把String[] words里面的word一个一个去board里做dfs（以每个点为起点做一次），很耗时。
有了Trie，我们可以用Trie存着所有word，然后去board里做一次dfs（以每个点为起点做一次）
坑点：
有可能board里面有多份某一个word，dfs会把它们都找出来，但是result set只需要添加一次 
*/

public class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<String>();
	    Trie trie = new Trie();
			int row = board.length;
			int col = board[0].length;
	    boolean[][] visisted = new boolean[row][col];
	    
		  for (String str : words) {
				trie.insert(str);
			}
	        
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					explore(i, j, board, visisted, trie, new StringBuilder(), res);
				}
			}
			return res;
	  }
		
		private void explore(int x, int y, char[][] board, boolean[][] visisted, Trie trie, StringBuilder sb, List<String> res) {
		  if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visisted[x][y]) return;
	    sb.append(board[x][y]);
	    visisted[x][y] = true;
	    if (trie.startsWith(sb.toString())) {
				if (trie.search(sb.toString())) {
					if (!res.contains(sb.toString())) {
						res.add(sb.toString());
						/* 
						note that here it cannot return!!!
						假如aaa被添加之后，return了，导致另一个正确答案aaab不被添加，这跟以前的情况不同，以前都是答案等长，这里不一定
						*/
					}
				}
				
				int[] dx = {0, 0, 1, -1};
				int[] dy = {1, -1, 0, 0};
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visisted[nx][ny]) {
						explore(nx, ny, board, visisted, trie, sb, res);
					}
				} 
			}		
			
			visisted[x][y] = false;
			sb.deleteCharAt(sb.length()-1);
		}
		
		class Trie {
			private static final int R = 26;
			TrieNode root = new TrieNode();
			
			public void insert(String word) {
				TrieNode cur = root;
				for (int i = 0; i < word.length(); i++) {
					int idx = word.charAt(i) - 'a';
					if (cur.children[idx] == null) {
						cur.children[idx] = new TrieNode(word.charAt(i));
					}
					cur = cur.children[idx];
				}
				cur.isWord = true;
			}
			
			public boolean search(String word) {
				TrieNode cur = root;
				for (int i = 0; i < word.length(); i++) {
					int idx = word.charAt(i) - 'a';
					if (cur.children[idx] == null) return false;
					else {
						if (i == word.length() - 1) {
							return cur.children[idx].isWord;
						} else {
							cur = cur.children[idx];
						}
					}
				}
				return false;
			}
			
			public boolean startsWith(String word) {
				TrieNode cur = root;
				for (int i = 0; i < word.length(); i++) {
					int idx = word.charAt(i) - 'a';
					if (cur.children[idx] == null) return false;
					else {
						cur = cur.children[idx];
					}
				}
				return true;
			}
			
			private class TrieNode {
				private boolean isWord;
				private TrieNode[] children;
				private char c;
				
				public TrieNode() {
					this.isWord = false;
					this.children = new TrieNode[R];
				}
				
				public TrieNode(char c) {
					this.c = c;
					this.isWord = false;
					this.children = new TrieNode[R];
				}			
			}
		}
}
