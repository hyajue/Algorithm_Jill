package TwoPointers;
/**
* Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
* 
* Note: The solution set must not contain duplicate triplets.
* 
* For example, given array S = [-1, 0, 1, 2, -1, -4],
* 
* A solution set is:
* [
*   [-1, 0, 1],
*   [-1, -1, 2]
* ]
*/
/*
复杂度
时间 O(n^2) 空间O(n)
思路： 三指针 
利用2Sum为subrutine + 排序后指针相向移动 
因为数组元素可能有重复 所以要先排序O(nlogn)
*/
//需要注意的是如何排除重复。 可以设置在满足三数相加为0的情况下，进行查重。否则没有必要进行查重。
public class ThreeSum {
public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length-2; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1;
            int r = nums.length - 1;
            int sum = 0 - nums[i];
       	 	while(l < r){
            	if(nums[l] + nums[r] == sum){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                	while(l < r && nums[l] == nums[l + 1]) l++;
                	while(l < r && nums[r] == nums[r - 1]) r--;
                	//指针向中间移动
                    l++;
                    r--;
            	}else if(nums[l] + nums[r] < sum)
                    l++;
                else
                	r--;
        	}    
        }
        return res;
    }
}
