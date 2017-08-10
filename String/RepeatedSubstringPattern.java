/**
* Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
* 
* Example 1:
* Input: "abab"
* 
* Output: True
* 
* Explanation: It's the substring "ab" twice.
* Example 2:
* Input: "aba"
* 
* Output: False
* Example 3:
* Input: "abcabcabcabc"
* 
* Output: True
* 
* Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

/*
复杂度
时间：O(n^2) 空间：O(n)
思路：
挨个试,尝试的长度从[len/2, 1]
*/

public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if(s == null || s.length() < 2) return false;
        int len = s.length();
        for(int i = len / 2; i >= 1; i--){
            if(len % i == 0){
                String temp = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < len / i; j++)
                    sb.append(temp);
                if(sb.toString().equals(s))
                    return true;
            }
        }
        return false;
    }
}

/*
复杂度
时间：O(n) 空间：O(n)
思路： KMP
利用kmp算法,构建next数组.
如果满足下面两个条件则返回true:
1. next[len] != 0: 最后一位的字符最起码要跟前面能构成前缀后缀子串
2. next[len] % (len - next[len]) == 0: len - dp[len]是一个子字符串的长度，
那么重复字符串的长度和肯定是一个子字符串的整数倍
*/

public class RepeatedSubstringPattern {
  public boolean repeatedSubstringPattern(String s) {
    int i = 1;
    int j = 0;
    int len = s.length();
    int[] next = new int[len+1];
    while (i < len) {
      if (s.charAt(i) == s.charAt(j)) {
          next[++i] = ++j;
      } else if (j == 0) {
          ++i;
      } else {
          j = next[j];
      }      
    }
    return next[len] != 0 && (next[len] % (len - next[len]) == 0);
  }
}