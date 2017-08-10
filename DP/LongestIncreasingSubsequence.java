package DP;
/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */

/* @Method 1:
Time: O(nlogn) Space: O(n)
思路：binary search+dp
设定一个dp[]数组，来存放结果。eg: dp[i]表示，当有序数组长度为i+1时候，第i+1个数最小是什么。
顺序traverse该array，用二分法来搜索，当前数字，在dp数组里的位置。如果i跟dp数组的有效size一样长了，那么size++。
这表示，该数字num比dp[i]还要小，但是整体已经是i+1个数了。这时候size++，表示有效长度更新成了i+2
*/
/*
binarySearch(object[ ], int fromIndex, int endIndex, object key);
如果要搜索的元素key在指定的范围内，则返回搜索键的索引；否则返回-1或者"-"(插入点)。

eg：
1.该搜索键在范围内，但不在数组中，由1开始计数；
2.该搜索键在范围内，且在数组中，由0开始计数；
3.该搜索键不在范围内，且小于范围内元素，由1开始计数；
4.该搜索键不在范围内，且大于范围内元素，由1开始计数；
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length <= 1) 
            return nums.length;
        int size = 0;
        int[] dp = new int[nums.length];
        for(int num : nums){
            int i = Arrays.binarySearch(dp, 0, size, num);
            if(i < 0) i = - (i + 1);
            dp[i] = num;
            if(i == size) size++;
        }
        return size;
    }
}

/*
 Time: O(n^2) Space: O(n)
 思路：dp
 在第i位，从0开始比到i-1，如果前面的那个数比nums[i]小，则考虑更新dp[i]
 */
public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length <= 1) return nums.length;
        int len = nums.length;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++)
            dp[i] = 1;
        
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(dp[i] < dp[j] + 1)
                        dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for(int n : dp)
            max = Math.max(max, n);
        
        return max;
    }
}
