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
            //因为可能出现重复，所以只有这样才能判断leftside ordered
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

//一种好一些的写法如下：

		//If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
            //any of the two sides won't change the result but can help remove duplicate from
            //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
