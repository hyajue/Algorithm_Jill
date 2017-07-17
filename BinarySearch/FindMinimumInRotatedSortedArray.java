package BinarySearch;
/**
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
* 
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
* 
* Find the minimum element.
* 
* You may assume no duplicate exists in the array.
*/

/*
主要思路还是跟Search in Rotated Sorted Array差不多，还是通过左边界和中间的大小关系
来得到左边或者右边有序的信息，如果左半边有序，那么左半边最小就是左边第一个元素，可以和当前最小相比取小的，
然后走向右半边。否则，那么就是右半半边第一个元素，然后走向左半边. 这样子每次可以截掉一半元素，
所以最后复杂度等价于一个二分查找，是O(logn)，空间上只有四个变量维护二分和结果，所以是O(1)
左边界和中间是相等的情况，这道题目不会发生，因为元素没有重复
*/
public class FindMinimumInRotatedSortedArray {
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
