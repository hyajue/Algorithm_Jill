package DFS;
/**
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
//注意： 数组可能是无序的
//Time: O(n), Space: O(1)
/*
 @Method 1: 
 难点，利用一个set来查重，
 */
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(nums == null || nums.length < 2) return new LinkedList(res);
        dfs(nums, res, new LinkedList<Integer>(), 0);
        return res;
    }
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> subRes, int idx){
        if(subRes.size() >= 2)
            res.add(new LinkedList<Integer>(subRes));
        Set<Integer> used = new HashSet<Integer>();
        for(int i = idx; i < nums.length; i++){
            if(idx > 0  && nums[i] < nums[idx-1]) continue; //skip on non-increase
            if(used.contains(nums[i])) continue; //skip duplicate
            used.add(nums[i]);
            subRes.add(nums[i]);
            dfs(nums, res, subRes, i + 1);
            subRes.remove(subRes.size() - 1);
            //used.remove(used.size() - 1);
        }
    }
}
//@Method 2: 用HashSet去重结果
public class IncreasingSubsequences {
	public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        if(nums == null || nums.length < 2) return new LinkedList(res);
        //Arrays.sort(nums);
        dfs(nums, res, new LinkedList<Integer>(), 0);
        return new LinkedList(res);
    }
    private void dfs(int[] nums, Set<List<Integer>> res, List<Integer> subRes, int idx){
        if(subRes.size() >= 2)
            res.add(new LinkedList<Integer>(subRes));
        //if(idx == nums.length) return;
        for(int i = idx; i < nums.length; i++){
            if(subRes.size() == 0 || subRes.get(subRes.size() - 1) <= nums[i]){
                subRes.add(nums[i]);
                dfs(nums, res, subRes, i + 1);
                subRes.remove(subRes.size() - 1);
            }
        }
    }
}
