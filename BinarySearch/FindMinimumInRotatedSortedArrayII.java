package BinarySearch;
/**
* Follow up for "Find Minimum in Rotated Sorted Array":
* What if duplicates are allowed?
* 
* Would this affect the run-time complexity? How and why?
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
* 
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
* 
* Find the minimum element.
* 
* The array may contain duplicates.
*/

/*
原来我们是依靠中间和边缘元素的大小关系，来判断哪一半是不受rotate影响，仍然有序的
而现在因为重复的出现，如果我们遇到中间和边缘相等的情况，我们就无法判断哪边有序，因为哪边都有可能有序
假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，
这样的我们判断左边缘和中心的时候都是3，我们并不知道应该截掉哪一半
解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能
所以最坏情况就会出现每次移动一步，总共移动n此，算法的时间复杂度变成O(n)
*/
public class FindMinimumInRotatedSortedArrayII {
	public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        int min = nums[0];
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] < nums[right]){
                //right side ordered
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }else if(nums[mid] > nums[right]){
                //left side ordered
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }else{
                min = Math.min(min, nums[mid]);
                right--;
            }
        }
        return min;
    }
}
