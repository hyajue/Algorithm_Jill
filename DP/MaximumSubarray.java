package DP;
/**
* Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
* 
* For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
* the contiguous subarray [4,-1,2,1] has the largest sum = 6
*/

/*
solution 1:
复杂度：时间O(n) 空间O(1)
思路:dp
dp[i-1]是否大于0， 是，则加上。否，则dp[i] = nums[i]
*/
public class Solution {
    public int maxSubArray(int[] nums) {
        int local = nums[0];
        int global = nums[0];
        for(int i = 1; i < nums.length; i++){
            local = Math.max(local + nums[i], nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}
//时间O(n) 空间O(n)
public class MaximumSubarray {
	public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
