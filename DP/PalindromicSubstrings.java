package DP;
/**
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
 */
/*
 @Method 1: 中心expand法
 Time: O(n^2) Space: O(1)
 用一个全局变量来存结果
 */
public class PalindromicSubstrings {
	int count = 0;
    public int countSubstrings(String s) {
        if(s == null) return 0;
        for(int i = 0; i < s.length(); i++){
            isPalindrome(s, i, i);
            isPalindrome(s, i, i + 1);
        }
        return count;
    }
    private void isPalindrome(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            count++;
            l--;
            r++;
        }
    }
}

//@MEthod 2: dp
public class Solution {
    public int countSubstrings(String s) {
        if(s == null) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = j - i > 1 ? dp[i+1][j-1] : true;
                if(dp[i][j])
                    res++;
            }
        }
        return res;
    }
}
