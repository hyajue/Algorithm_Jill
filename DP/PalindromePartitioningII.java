package DP;
/**
* Given a string s, partition s such that every substring of the partition is a palindrome.
* 
* Return the minimum cuts needed for a palindrome partitioning of s.
* 
* For example, given s = "aab",
* Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/*
复杂度
时间O(n^2) 空间(n^2)
思路：dp
建立boolean二维数组dp[i][j]，存放字符串s从第i到第j位是否为回文的布尔值；同时建立整型数组min[i]，
存放从最后一位到第i位都是回文串的最小切割次数。然后从字符串s的最后一个字符开始，向前进行分析。
比如说，从后向前，分析到第i位，开始判断dp[i][j]，若为true，说明从第j位向前到第i位的子串是一个回文串，
则min[i]就等于第j位的结果min[j]加1。然后让j继续增大，判断第i位到最后一位的范围内，有没有更长的回文串，
更长的回文串意味着存在更小的min[j]，用新的min[j+1]+1来替换min[i]
the current cut num (first cut s[i..j] and then cut the rest s[j+1...n-1]) is 1+d[j+1], 
*/

public class PalindromePartitioningII {
	public int minCut(String s) {
        if(s == null || s.length() <= 1) return 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int[] min = new int[len];
        for(int i = len - 1; i >= 0; i--){
            min[i] = len - 1 - i;
            for(int j = i; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = j - i > 1 ? dp[i+1][j-1] : true;
                if(dp[i][j]){
                    if(j == len - 1) 
                        min[i] = 0;
                    else
                        min[i] = Math.min(min[i], min[j+1] + 1); //
                }
            }
        }
        return min[0];
    }
}
