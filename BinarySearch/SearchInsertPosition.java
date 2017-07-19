package BinarySearch;
/**
* Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
* 
* You may assume no duplicates in the array.
* 
* Here are few examples.
* [1,3,5,6], 5 → 2
* [1,3,5,6], 2 → 1
* [1,3,5,6], 7 → 4
* [1,3,5,6], 0 → 0
*/

/*
复杂度
时间:O(logN) 空间O(1)
思路：二分查找
注意二分结束的条件:left <= right -> 如果数组不包含元素 则退出查找时，left一定指向比target大的最小元素
*/
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
        int res = -1;
        if(nums == null || nums.length == 0)
            return res;
        //左边界。左值>=target
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = (r - l) / 2 + l;
            if(nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
}
