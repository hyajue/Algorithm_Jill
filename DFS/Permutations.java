/**
* Given a collection of distinct numbers, return all possible permutations.
* 
* For example,
* [1,2,3] have the following permutations:
* [
*   [1,2,3],
*   [1,3,2],
*   [2,1,3],
*   [2,3,1],
*   [3,1,2],
*   [3,2,1]
* ]
*/

/*
复杂度
时间:NP 指数级 空间O(n)
思路：回溯法
N皇后模型 循环中递归解决问题 依次，每次取一个数，递归，然后把数还回去，回溯
*/

public class Permutations {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) return res;
    helper(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
    return res;    
  }
	
  private void helper(List<List<Integer>> res, List<Integer> curList, int[] nums, boolean[] used) {
	  if (curList.size() == nums.length) {
	    res.add(new ArrayList<Integer>(curList));
	    return;
	  } else {
	    for (int i = 0; i < nums.length; i++) {
	      if (!used[i]) {
					used[i] = true;
					curList.add(nums[i]);
					helper(res, curList, nums, used);
					curList.remove(curList.size() - 1);
					used[i] = false;
		    }
	    }
	  }
  }
}

//或者，用temp.contains()来判断
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length==0) return res;
        backtrack(nums, new ArrayList<Integer>(), res);
        return res;
    }
    private void backtrack(int[] nums, List<Integer> temp, List<List<Integer>> res) {
        if(temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i]))
                    continue;
                temp.add(nums[i]);
                backtrack(nums, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }
}