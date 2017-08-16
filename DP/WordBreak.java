/**
* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
* 
* For example, given
* s = "leetcode",
* dict = ["leet", "code"].
* 
* Return true because "leetcode" can be segmented as "leet code".
*/

/*
复杂度：
时间：O(n*m) m = length of wordDict 空间O(n)
思路：
这种找一个词由多个词组成的题，是拿dp或者dfs来解，dp本质上其实也是dfs。这道题要判断输入的词能否由字典里的单词组成，那么可以用个boolean的dp数组。
initialize dp[s.length() + 1], dp[0] = true
dp function: dp[i] = dp[j] & (s[j, i] in dict)
result: dp[s.length()]
第二步的dp function，两种找的方法，一个是j从0到i - 1循环，还有一种是traverse整个dict，j = i - word.length()。
当字典很大，s不长的时候，用第一种，当字典不大，但是s很长的时候用第二种。
这题现在给的dict是个list不是set没法O(1)判断s[j, i] in dict，所以用第二种来写。
*/

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length() + 1];
        res[0] = true;
        
        //@Method 2:
        for(int i = 1; i <= s.length(); i++) {
            for(String str : wordDict) {
                int j = i - str.length();
                if(j >= 0 && s.substring(j, i).equals(str) && res[j]) {
                    res[i] = true;
                    break;
                }
            }
        }

        //@Method 1:
        // Set<String> dict = new HashSet<>();
        // for(String str : wordDict)
        //     dict.add(str);
        // for(int i = 1; i <= s.length(); i++) {
        //     for(int j = 0; j < i; j++) {
        //         if(res[j] && dict.contains(s.substring(j, i))) {
        //             res[i] = true;
        //             break;
        //         }
        //     }
        // }
        
        // return res[s.length()];
    }
}