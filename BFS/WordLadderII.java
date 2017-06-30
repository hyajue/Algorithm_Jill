package BFS;
/**
* Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
* 
* Only one letter can be changed at a time
* Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
* For example,
* 
* Given:
* beginWord = "hit"
* endWord = "cog"
* wordList = ["hot","dot","dog","lot","log","cog"]
* Return
*   [
*     ["hit","hot","dot","dog","cog"],
*     ["hit","hot","lot","log","cog"]
*   ]
* Note:
* Return an empty list if there is no such transformation sequence.
* All words have the same length.
* All words contain only lowercase alphabetic characters.
* You may assume no duplicates in the word list.
* You may assume beginWord and endWord are non-empty and are not the same.
* UPDATE (2017/1/20):
* The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
*/

/*
复杂度
时间：
bfs:每个节点只入队一次:n*26^lenOfWord
dfs:26^lenOfWord
思路：bfs+dfs
要返回所有的结果，考虑dfs,因为用BFS相对于DFS的劣势就是不方便存储结果.但是直接应用DFS复杂度会很高，因为只要知道结尾就好了，不用继续往下搜。
所以问题就转化成怎样用DFS的同时又可以限制DFS的深度，所以可以BFS与DFS结合 
先用BFS搜到结尾字符串，然后把中途所有的字符串及其跟起始字符的edit distance存在一个map里。这样的话，下一步就可以从结尾字符串开始DFS，只有Map内的字符串才考虑继续DFS，直至搜到起始字符。
注意：
为什么不从起始字符串开始DFS直至搜到结尾字符串，而是反过来？
想像一个图，如果从起始字符串开始搜，到最后一层的话会有很多无效搜索，因为最后那层只需要找到结尾字符串，那么多无效的搜索到最一层太浪费时间。
反之，如果我们从结尾字符串开始DFS, 我们把起始层控制在一个字符串，整个图先越来越宽，然后越来越窄直到起始字符串，而非一直越来越宽直到结尾字符串那层。
*/

public class WordLadderII {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(wordList == null || wordList.size() == 0) return res;
        Set<String> dict = new HashSet<String>();
        for(String word : wordList){
            dict.add(word);
        }
        
        Map<String, Integer> dist = new HashMap<String, Integer>();
        getDist(beginWord, endWord, dict, dist);
        if(!dist.containsKey(endWord)) return res;
        List<String> path = new ArrayList<String>();
        path.add(endWord);
        getAllMinPath(res, path, beginWord, endWord, dist);
        return res;
    }
    private void getDist(String beginWord, String endWord, Set<String> dict, Map<String, Integer> dist){
        if(beginWord == null || endWord == null) return;
        dist.put(beginWord, 1);
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for(int i=0; i<cur.length(); i++){
                char[] curArr = cur.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                    curArr[i] = c;
                    String temp = new String(curArr);
                    //剪枝
                    if(dict.contains(temp)){
                        //BFS结束
                        if(temp == endWord){
                            dist.put(endWord, dist.get(cur)+1);
                            return;
                        }
                        //剪枝 同一个词只入队一次
                        if(!dist.containsKey(temp)){
                            dist.put(temp, dist.get(cur)+1);
                            queue.offer(temp);
                        }
                    }
                }
            }
        }
    }
    private void getAllMinPath(List<List<String>> res, List<String> path, String beginWord, String cur, Map<String, Integer> dist){
        if(cur.equals(beginWord)){
            Collections.reverse(path);
            res.add(new ArrayList<String>(path));
            Collections.reverse(path);
            return;
        }
        for(int i=0; i< cur.length(); i++){
            char[] curArr = cur.toCharArray();
            for(char c = 'a'; c <= 'z'; c++){
                curArr[i] = c;
                String next = new String(curArr);
                //剪枝 新生成的词只有满足下面两个条件才继续深搜：
				//1.该词在哈希表里面存在 2.该词跟上一个词距离为1
                if(dist.containsKey(next) && dist.get(cur) - dist.get(next) == 1){
                    path.add(next);
                    getAllMinPath(res, path, beginWord, next, dist);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
