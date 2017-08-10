/**
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. 
Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
*/
/*
@Method: HashMap
Time: O(n) Space: O(n)
Map.Entry<> entry : map.entrySet()
entry.getValue()
*/
public class Solution {
    public int findPairs(int[] nums, int k) {
        if(nums == null || nums.length < 2 || k < 0)
            return 0;
        //之所以不用set，是因为可能存在k=0
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(k == 0){
                if(entry.getValue() >= 2)
                    res++;
            }else{
                //注意看map的用法，以及何时用entry.getKey()
                if(map.containsKey(entry.getKey() + k))
                    res++;
            }
        }
        return res;
    }
}


/*
@Method 1: Two Pointers
Time: O(nlogn) Space: O(1)
思路：快慢指针分别从0和1开始走

note：不可以从左右两头往中间走，因为这个求的是 差 而不是 和
*/

public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        if(nums == null || nums.length < 2)
            return 0;
        int res = 0;
        Arrays.sort(nums);
        int l = 0, r = 1;
        while(l < nums.length && r < nums.length){
            if(l == r || nums[l] + k > nums[r]){
                r++;
            }else if(nums[l] + k < nums[r]){
                l++;
            }else{
                res++;
                l++;
                while(l < nums.length && nums[l] == nums[l - 1])
                    l++;
                //r needs to move one space forward
                r = Math.max(l + 1, r + 1);
            }
        }
        return res;
    }
}