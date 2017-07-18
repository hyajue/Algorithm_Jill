package BinarySearch;
/**
* A peak element is an element that is greater than its neighbors.
* 
* Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
* 
* The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
* 
* You may imagine that num[-1] = num[n] = -∞.
* 
* For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
*/

/*
复杂度
时间：O(logN) 空间：O(1)
思路：二分法
每次更新left或者right的标准是要保证剩下的半边一定要包含至少一个peak element 
要做到这一点，只需要比较num[mid]和num[mid+1], 如果中间元素大于其相邻后续元素，
则中间元素左侧(包含该中间元素）必包含一个局部最大值 如果中间元素小于其相邻后续元素，
则中间元素右侧（不含该中间元素）必包含一个局部最大值 对于更新l和r的时候是否要包括中间元素要想清楚，要考虑中间元素是否可能是解
*/
public class FindPeakElement {
	public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}

//recursive
public class Solution {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length <= 1) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    private int helper(int[] nums, int l, int r){
        if(l == r) return l;
        int mid = (r - l) / 2 + l;
        if(nums[mid] > nums[mid + 1])
            return helper(nums, l, mid);
        else
            return helper(nums, mid + 1, r);
    }
}
