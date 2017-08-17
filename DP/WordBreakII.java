/**
* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
* 
* Return all such possible sentences.
* 
* For example, given
* s = "catsanddog",
* dict = ["cat", "cats", "and", "sand", "dog"].
* 
* A solution is ["cats and dog", "cat sand dog"].
*/

/*
solution 1
复杂度
时间O(n*m) 空间O(n)
思路：
要返回所有可能的组合。所以现在dp[i]里面应该存可以使长度为i所有可能的String里的最后一个word。
dp有两种写法，一个就是直接写成数组：List[]的形式，不能形成的dp[i] = null。还有一个是用个hashmap：Map<Integer, List>，
用hashmap的好处是如果s很长而且用dict能组合成的长度不是很多的话，map用的空间相对少。dp结束之后，第二步就是通过dp里面保存的word，一步一步回溯找到所有结果
*/

/*
@1
这里注意的是，因为给的wordDict是个list，那么在dict不长的情况下，可以考虑forEach遍历dict
用到sting.startsWith()来判断是否跟开头一致。这种情况下，要判断subList中的str是否为"" / str.isEmpty(), 是的话，不需要后面加空格了
@2
还有一种解法，当dict是个Set的时候，可以选择从后切，将前面的部分backtrack
好处是：不需要考虑这个string的后半部分是不是空集情况，少一个判断
*/

//used HashMap to save the previous results to prune duplicated branches
public class WordBreakII {
    Map<String, List<String>> map = new HashMap<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(map.containsKey(s))
            return map.get(s);
        
        List<String> res = new ArrayList<String>();
        //s是non empty，所以不存在s==null
        if(s.length() == 0) {
            //保证一个string如果可以正确的break到结尾，要返回一个非空res
            res.add("");
            return res;
        }
        
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                List<String> subRes = wordBreak(s.substring(word.length()), wordDict);
                for(String str : subRes) {
                    //因为str有可能代表末尾 "",所以需要判断是否添加word后面的空格
                    res.add(word + (str.isEmpty() ? "" : " ") + str);
                }
            }
        }
        map.put(s, res);
        //也可能存在根本不满足上述循环的情况，那么直接res为空集
        return res;
    }   
}


//@ 2
public class Solution {
    HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i);
            if(wordDict.contains(t)) {
                List<String> temp = wordBreak(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) {
                    for(int j = 0 ; j < temp.size() ; j++) {
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s , res);
        return res;
    }
}