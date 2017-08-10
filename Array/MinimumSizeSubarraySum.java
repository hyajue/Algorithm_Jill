/**
* Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
* 
* For example, given the array [2,3,1,2,4,3] and s = 7,
* the subarray [4,3] has the minimal length under the problem constraint.
* 
* click to show more practice.
* 
* More practice:
* If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

/*
复杂度
时间：O(n) 空间O(1)
思路：双指针
维护一个窗口,使得窗口中元素的和小于s
当元素和小于s的时候,移动右指针并把当前数加入当前和,当加完之后大于等于s时,就移动左指针,将当前数字减去.每次移动左指针之前,都更新最小距离.
*/

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int start = 0, end = 0;
        int len = Integer.MAX_VALUE;
        int sum = 0;
        
        while(start < nums.length && end < nums.length){
            while(end < nums.length && sum < s){
                //跳出循环的时候，sum已经大于s了。end在第一个不满足循环条件的位置
                sum += nums[end++];
            }
            while(start <= end && sum >= s){
                //所以len = end - start
                len = Math.min(len, end - start);
                sum -= nums[start++];
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}

/*
Time: O(nlogn) Space: O(n)
思路：二分查找法
建立一个比原数组长一位的sums数组，其中sums[i]表示nums数组中[0, i - 1]的和，
然后我们对于sums中每一个值sums[i]用二分查找法找到子数组的右边界位置，
使该子数组之和大于sums[i] + s，然后我们更新最短长度的距离即可
*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] sums = new int[nums.length + 1];
        for(int i = 1; i < sums.length; i++)
            sums[i] = sums[i-1] + nums[i-1];
        
        int len = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int end = bs(i + 1, sums.length - 1, sums[i] + s, sums);
            //如果直接过界了。那么直接exit
            if(end == sums.length) break;
            //从i+1到end的长度
            len = Math.min(end - i, len);
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
    private int bs(int l, int r, int index, int[] sums){
        if(l > r) return sums.length;
        while(l <= r){
            int mid = (r - l) / 2 + l;
            //这样保证l出循环的时候肯定sums[l] > index.
            if(sums[mid] >= index)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }
}

