package DP;
/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */

/*
Time: O(nlogn)
思路：binary search+dp
*/
/*
 Time: O(n^2) Space: O(n)
 思路：dp
 在第i位，从0开始比到i-1，如果前面的那个数比nums[i]小，则考虑更新dp[i]
 */
public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length <= 1) return nums.length;
        int len = nums.length;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++)
            dp[i] = 1;
        
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(dp[i] < dp[j] + 1)
                        dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for(int n : dp)
            max = Math.max(max, n);
        
        return max;
    }
}
