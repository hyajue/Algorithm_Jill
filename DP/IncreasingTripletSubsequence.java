/**
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/

/*
思路： Time: O(n) Space: O(n)
The main idea is keep two values when check all elements in the array: 
the minimum value min until now and the second minimum value secondMin from the minimum value's position until now. 
Then if we can find the third one that larger than those two values at the same time, it must exists the triplet subsequence and return true.

What need to be careful is: we need to include the condition that some value has the same value with minimum number, otherwise this condition will cause the secondMin change its value.
*/

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length <= 2) return false;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for(int num : nums){
            if(num <= first)
                first = num;
            else if(num <= second)
                second = num;
            else
                return true;
        }
        return false;
    }
}