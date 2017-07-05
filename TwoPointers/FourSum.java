package TwoPointers;
/**
* Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
* 
* Note: The solution set must not contain duplicate quadruplets.
* 
* For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
* 
* A solution set is:
* [
*   [-1,  0, 0, 1],
*   [-2, -1, 1, 2],
*   [-2,  0, 0, 2]
* ]
*/

/*
solution 1
复杂度
时间O(n^3) 空间O(1)
思路：N指针 N=4 
3sum的扩展 外层再加一个循环->做N次3Sum 
*/
public class FourSum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 4) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j = i + 1; j< nums.length - 2; j++){
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                int l = j + 1, r = nums.length - 1;
                while(l < r){
                    if(nums[i] + nums[j] + nums[l] + nums[r] == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while(l < r && nums[l] == nums[l + 1]) l++;
                        while(l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    }else if(nums[i] + nums[j] + nums[l] + nums[r] < target)
                        l++;
                    else
                        r--;
                }
            }
        }
        return res;
    }
}
