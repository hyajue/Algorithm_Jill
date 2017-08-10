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

//Divide and Conquer
/*
这个分治法的思想就类似于二分搜索法，我们需要把数组一分为二，分别找出左边和右边的最大子数组之和，
然后还要从中间开始向左右分别扫描，求出的最大值分别和左右两边得出的最大值相比较取最大的那一个
*/
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}
