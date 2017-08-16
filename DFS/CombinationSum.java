/**
* Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
* 
* The same repeated number may be chosen from C unlimited number of times.
* 
* Note:
* All numbers (including target) will be positive integers.
* The solution set must not contain duplicate combinations.
* For example, given candidate set [2, 3, 6, 7] and target 7, 
* A solution set is: 
* [
*   [7],
*   [2, 2, 3]
* ]
*/

/*
复杂度：
时间： NP 指数级 空间： O(n)
思路：
先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题
*/

public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
	    //if (i > start && candidates[i] == candidates[i-1]) continue; // 去重
        curList.add(candidates[i]);
	    helper(res, curList, candidates, target-candidates[i], i);
	    curList.remove(curList.size()-1);
	  }
  }
} 