package DP;
/**
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].
 */
/* @Method 1:
Time: O(nlogn) Space: O(1)
思想：greedy
将数组尾升序排序，设置一个当前变量尾
只要下一个数组的变量头<=当前变量尾，往下挪一个数
如果变量头>当前变量尾，res++，往下挪一个数，并且更新当前变量尾
*/
public class MaximumLengthOfPairChain {
	public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
        int res = 0;
        int i = -1;
        while(++i < pairs.length){
            res++;
            int curEnd = pairs[i][1];
            while(i + 1 < pairs.length && pairs[i+1][0] <= curEnd)
                i++;
        }
        return res;
    }
}
/*
 @Method 2: DP
 将数组头升序排列，设置dp数组
 i从1到n，看从0到i-1的数组，是否其数尾<i的数头？并且dp[i] < dp[j]+1?
 是的话，更新
 最后找到最大的dp[i]
*/
public class Solution {
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        int i = 0, j = 0;
        int res = 0;
        //赋初值
        for(i = 0; i < n; i++)
            dp[i] = 1;
        
        for(i = 1; i < n; i++){
            for(j = 0; j < i; j++){
                if(pairs[i][0] > pairs[j][1] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
        }
        for(i = 0; i < n; i++)
            res = Math.max(res, dp[i]);
        
        return res;
    }
}