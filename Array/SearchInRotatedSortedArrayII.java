package Array;
/**
* Follow up for "Search in Rotated Sorted Array":
* What if duplicates are allowed?
* 
* Would this affect the run-time complexity? How and why?
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
* 
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
* 
* Write a function to determine if a given target is in the array.
* 
* The array may contain duplicates.
*/

/*
复杂度：
时间：O(n) 空间:O(1)
思路：
with duplication, we may not get which side is sorted by comparing nums[mid] with nums[left] or nums[right]
nums[mid] may equal to nums[left] or nums[right] 
left pointer has to move if nums[left] == nums[mid], until they are not equal
worst case left pointer has to move through the whole array -> T(n) = O(n)
*/

public class SearchInRotatedSortedArrayII {
	public boolean search(int[] nums, int target) {
        if(nums == null || nums.length ==0) return false;
        int left = 0, right =  nums.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(target == nums[mid]) return true;
            
            if(nums[mid] < nums[right]){
                //right side ordered
                if(target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }else if(nums[mid] > nums[right]){
                //left side ordered
                if(target < nums[mid] && target >= nums[left])
                    right = mid - 1;
                else
                    left = mid + 1;
            }else{
                right--;
            }
        }
        return false;
    }
}
