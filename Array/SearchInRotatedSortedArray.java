package Array;
/**
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
* 
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
* 
* You are given a target value to search. If found in the array return its index, otherwise return -1.
* 
* You may assume no duplicate exists in the array.
*/

/*
复杂度
时间(logN) 空间O(1)
思路：二分
二分查找Search Insert Position的变体. 因为rotate的缘故，切取一半的时候可能会出现误区，所以要做进一步的判断。具体来说，假设数组是nums，每次左边缘为l，右边缘为r，还有中间位置是m。
在每次迭代中，分三种情况：
（1）如果target==nums[m]，那么m就是要的结果，直接返回；
（2）如果nums[m]<nums[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
（3）如果nums[m]>=nums[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)
注意:
在这道题中假设了这个数组中不会出现重复元素。如果允许出现重复元素，那么还要对中间和边缘的相等的情况继续处理，进而影响到时间复杂度，
Search in Rotated Sorted Array II即为follow up
*/
public class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] == target) return mid;
            if(nums[mid] < nums[right]){
                //Right side is ordered
                if(target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }else{
                //Left side is ordered
                if(target < nums[mid] && target >= nums[left])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }
}
