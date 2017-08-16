/**
* Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
* 
* Each number in C may only be used once in the combination.
* 
* Note:
* All numbers (including target) will be positive integers.
* The solution set must not contain duplicate combinations.
* For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
* A solution set is: 
* [
*   [1, 7],
*   [1, 2, 5],
*   [2, 6],
*   [1, 1, 6]
* ]
*/

/*
复杂度：
时间： NP 指数级 空间： O(n)
思路：回溯法
这个题目中单个元素用过就不可以重复使用 处理起来有点绕
虽然一个元素不可以重复使用，但是如果这个元素重复出现是允许的(如果选择集中有重复元素)，
但是为了避免出现重复的结果集，我们只对于第一次得到这个数进行递归，接下来就跳过这个元素了，因为接下来的情况会在上一层的递归函数被考虑到，这样就可以避免重复元素的出现
*/

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(res, new ArrayList<Integer>(), candidates, target, 0);
        return res;	
    }
    private void helper(List<List<Integer>> res, List<Integer> curList, int[] candidates, int target, int start) {
		if (target < 0) return;
		if (target == 0) {
			res.add(new ArrayList<Integer>(curList));
			return;
		} 
		for (int i = start; i < candidates.length; i++) {
			if (i > start && candidates[i] == candidates[i-1]) continue; // 去重
			curList.add(candidates[i]);
			helper(res, curList, candidates, target-candidates[i], i+1);
			curList.remove(curList.size()-1);
		}        
    }
}