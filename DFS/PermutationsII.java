/**
* Given a collection of numbers that might contain duplicates, return all possible unique permutations.
* 
* For example,
* [1,1,2] have the following unique permutations:
* [
*   [1,1,2],
*   [1,2,1],
*   [2,1,1]
* ]
*/

/*
复杂度
时间:NP 指数级 空间O(n)
思路：回溯法
与PermutationsI类似的模板 只是需要考虑去重问题，所以做以下处理：
1.对输入数组进行排序 确保重复元素相邻
2.循环递归开始时要判断是否重复
*/

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length==0) return res;
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
        return res;
    }
    public void backtrack(int[] nums, boolean[] used, List<Integer> temp, List<List<Integer>> res){
        if(temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        } else {
            for(int i = 0; i < nums.length; i++) {
                //第二种情况是排重 在i-1没被用，而i和i-1一样的时候，没必要访问i
                if(used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
                used[i] = true;
                temp.add(nums[i]);
                backtrack(nums, used, temp, res);
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
}