package TwoPointers;
/**
* Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
* 
*     For example, given array S = {-1 2 1 -4}, and target = 1.
* 
*     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

/*
复杂度
时间O(n^2) 空间O(1)
思路：三指针->双向夹逼
与3Sum套路一样 这里换成维护一个最小距离 当找到三个数能使与目标的距离更近时 更新最小距离
*/

public class ThreeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return Integer.MIN_VALUE;
        int diff = Integer.MAX_VALUE;
        int res = 0;
        
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while(l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == target) return sum;
                else if(sum < target){
                    if(target - sum < diff){
                        diff = target - sum;
                        res = sum;
                    }
                    l++;
                }else{
                    if(sum - target < diff){
                        diff = sum - target;
                        res = sum;
                    }
                    r--;
                }
            }   
        }
        return res;
    }
}
