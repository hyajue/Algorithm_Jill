package TwoPointers;
/**
* Given an array of integers, return indices of the two numbers such that they add up to a specific target.
* 
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* 
* Example:
* Given nums = [2, 7, 11, 15], target = 9,
* 
* Because nums[0] + nums[1] = 2 + 7 = 9,
* return [0, 1].
*/

/* solution 1: hash map + one pointer  
   maintain a hashMap to keep track of index of nums[i] by <nums[i], index[i]> 	
   a pointer iterate through the array to find if -nums[i] exists in hash map
   T(n) = O(n), S(n) = O(n)   
*/
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        int[] res = {-1, -1};
        if(nums == null || nums.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[] {map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
