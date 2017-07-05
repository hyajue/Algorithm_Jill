package TwoPointers;

import java.util.Arrays;

/**
* Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
* 
* For example, given nums = [-2, 0, 1, 3], and target = 2.
* 
* Return 2. Because there are two triplets which sums are less than 2:
* 
* [-2, 0, 1]
* [-2, 0, 3]
* Follow up:
* Could you solve it in O(n2) runtime?
*/
/*
复杂度
时间：O(n^2) 空间：O(1)
思路：三指针
维护三个指针i, j, k,先对输入数组排序,方便跳过重复元素
对i -> [0, len-3]:
  l = k + 1， r = len - 1; 
  l,r 双指针相对而行, 如果i，l，r指向的数相加小于target,则r取[l+1, r]之间的所有组合都满足要求,
  因为r最大,最大的可以,比它小的也肯定满足可以.更新cnt后直接把l指针右移即可.   
*/ 
public class ThreeSumSmaller {
	public int threeSumSmaller(int[] nums, int target) {
		int res = 0;
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 2; i++){
			int l = i + 1, r = nums.length - 1;
			while(l < r){
				if(nums[i] + nums[l] + nums[r] < target){
					res += r - l;
					l++;
				}else{
					r--;
				}
			}
		}
		return res;
	}
}
