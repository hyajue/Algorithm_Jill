package DP;
/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/*
solution 1:
复杂度：
时间O(n) 空间O(n)
由于乘法会出现负值 负负得正 所以需要记录最小的负值 
下面举个例子帮助理解：
	[1,  -1,   -2,   4]
idx  0    1     2    3
max  1    -1    
min  1   -1    2 
*/	 
public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
        int res = nums[0];
        int min = nums[0], max = nums[0];
        for(int i = 1; i < nums.length; i++){
            int temp = max;
            max = Math.max(Math.max(nums[i] * temp, min * nums[i]), nums[i]);
            min = Math.min(Math.min(nums[i] * temp, min * nums[i]), nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
