package DP;
/**
 * Given a string s, find the longest palindromic subsequence's length in s. 
 * You may assume that the maximum length of s is 1000.
 */
/*
 思路：
 Time: O(n^2)   Space:O(n^2)
 
dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
State transition:
dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
Initialization: dp[i][i] = 1
 */

// Method1: iterative
public class LongestPalindromicSubsequence {
	public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i + 1; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][len-1];
    }
}


