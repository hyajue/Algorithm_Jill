package BinarySearch;
/**
* Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
* 
* Your algorithm's runtime complexity must be in the order of O(log n).
* 
* If the target is not found in the array, return [-1, -1].
* 
* For example,
* Given [5, 7, 7, 8, 8, 10] and target value 8,
* return [3, 4].
*/

/*
复杂度
时间O(logN) 空间O(1)
思路：二分查找
二分朝找到相等时向一个方向继续夹逼，如果向右夹逼，最后就会停在右边界，而向左夹逼则会停在左边界，如此用停下来的两个边界就可以知道结果，只需要两次二分查找。
*/
public class SearchForARange {
	public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if(nums == null || nums.length == 0) return res;
        //左边界.ll停在的位置会>=target
        int ll = 0, lr = nums.length - 1;
        while(ll <= lr){
            int mid = (lr - ll) / 2 + ll;
            if(nums[mid] < target)
                ll = mid + 1;
            else
                lr = mid - 1;
        }
        //右边界。rr停在的位置会<=target
        int rl = 0, rr = nums.length - 1;
        while(rl <= rr){
            int mid = (rr - rl) / 2 + rl;
            if(nums[mid] > target)
                rr = mid - 1;
            else
                rl = mid + 1;
        }
        if(ll <= rr){
            res[0] = ll;
            res[1] = rr;
        }
        return res;
    }
}
