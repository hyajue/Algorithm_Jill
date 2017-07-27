package DP;
/**
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
* 
* Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
DP: global-local max method 
*/
public class HouseRobber {
	public int rob(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int noRob = 0;
        int yesRob = 0;
        for(int i = 0; i < nums.length; i++){
            int yesLocal = nums[i] + noRob;//这轮抢，i-1的noRob+当前nums[i]
            noRob = Math.max(noRob, yesRob); //这轮不rob，那么noRob应该为i-1次 抢与不抢的最大值
            yesRob = yesLocal; //更新这轮的值
        }
        return Math.max(yesRob, noRob);
    }
}
