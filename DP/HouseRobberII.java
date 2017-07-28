package DP;
/**
* Note: This is an extension of House Robber.
* 
* After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
* 
* Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/
/*
DP with circle.
the first last last element cannot be present together
So we call DP twice, and return the larger result.
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(robBetween(nums, 0, n - 2), robBetween(nums, 1, n - 1));
    }
    private int robBetween(int[] nums, int start, int end){
        int noRob = 0;
        int yesRob = nums[start];
        for(int i = start + 1; i <= end; i++){
            int localYes = noRob + nums[i];
            noRob = Math.max(noRob, yesRob);
            yesRob = localYes;
        }
        return Math.max(yesRob, noRob);
    }
}
