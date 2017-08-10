package String;
/**
* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
* 
* Example:
* 
* Input: "babad"
* 
* Output: "bab"
* 
* Note: "aba" is also a valid answer.
* Example:
* 
* Input: "cbbd"
* 
* Output: "bb"
*
* follow up: what if multiple results exist? return all longest results.  
*/
/*
Method 1
复杂度
时间O(n^2) 空间O(n)
思路:中心扩散法
对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，直到不是回文串为止。
假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)
follow up: 维护一个集合 如果当前回文长度等于最长 就把该回文加入集合 如果更长 就清空集合 加入当前回文
*/
public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
        if(s==null || s.length()==0) return "";
        int maxLen = 0;
        String res = "";
        for(int i = 0; i < s.length(); i++){
            String str1 = checkPalindrome(s, i, i);
            String str2 = checkPalindrome(s, i, i + 1);
            if(maxLen < str1.length()){
                maxLen = str1.length();
                res = str1;
            }
            if(maxLen < str2.length()){
                maxLen = str2.length();
                res = str2;
            }
        }
        return res;
    }
    private String checkPalindrome(String s, int left, int right){
        if(left > right) return ""; // dcaacb
        
        while(left >= 0  && right < s.length()){
            if(s.charAt(left) != s.charAt(right))
                break;
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
/*复杂度
时间O(n^2) 空间(n^2)
思路：dp
建立boolean二维数组dp[i][j]，存放字符串s从第i到第j位是否为回文的布尔值；
然后从字符串s的最后一个字符开始，向前进行分析。
比如说，从后向前，分析到第i位，开始判断dp[i][j]，若为true，说明从第j位向前到第i位的子串是一个回文串
*/
public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int maxStart = 0;
        int maxLen = 0;
        
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = j - i > 1 ? dp[i+1][j-1] : true;
                if(dp[i][j] && j - i + 1 > maxLen){
                    maxStart = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }
}
/*
solution 2
复杂度
时间O(n) 空间O(n)
思路:Manacher Algorithm 
经典计算连续下标回文算法
*/
