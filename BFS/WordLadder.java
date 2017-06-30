package BFS;
/**
* Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
* 
* Only one letter can be changed at a time.
* Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
* For example,
* 
* Given:
* beginWord = "hit"
* endWord = "cog"
* wordList = ["hot","dot","dog","lot","log","cog"]
* As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
* return its length 5.
* 
* Note:
* Return 0 if there is no such transformation sequence.
* All words have the same length.
* All words contain only lowercase alphabetic characters.
* You may assume no duplicates in the word list.
* You may assume beginWord and endWord are non-empty and are not the same.
* UPDATE (2017/1/20):
* The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
*/

/*
复杂度
时间：O(n*26^length(word)) 空间：O(n)
思路： BFS
求最短距离问题 首先考虑能否用BFS解
维护一个队列和一个Set,队列里放置单词满足以下条件：
1.由已有的单词变化一个字符得来
2.字典里存在
每次出队一个单词，变化每一个字符，如果字典里面有，则入队
Set里保存已经检查过的单词
*/

public class WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0) return 0;
        Set<String> dict = new HashSet<String>();
        for(String word : wordList){
            dict.add(word);
        }
        int level = 1;
        Set<String> isHave = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        isHave.add(beginWord);
        while(!queue.isEmpty()){
            int curSize = queue.size();
            //当前队列的大小，等于此层的节点数量。遍历完一层，level才+1
            for(int k=0; k<curSize; k++){
                String cur = queue.poll();
                for(int i = 0; i < cur.length(); i++){
                    char[] curArr = cur.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++){
                        curArr[i] = c;
                        String next = new String(curArr);
                        //
                        if(dict.contains(next) && !isHave.contains(next)){
                            if(next.equals(endWord)){
                                return level + 1;
                            }
                            queue.offer(next);
                            isHave.add(next);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
