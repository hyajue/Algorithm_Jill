
/**
* Given a collection of integers that might contain duplicates, nums, return all possible subsets.
* 
* Note: The solution set must not contain duplicate subsets.
* 
* For example,
* If nums = [1,2,2], a solution is:
* 
* [
*   [2],
*   [1],
*   [1,2,2],
*   [2,2],
*   [1,2],
*   []
* ]
*/

/*
复杂度
时间：O(2^n) 空间：O(2^n) 
思路：
N皇后类型.循环中递归求解
注意给定集合里面可能会有重复元素,所以在结果集中要进行查重处理：
1.首先对输入数组进行排序,方便后面查重
2.在某一层递归中,如果前后元素相等,则跳过后面的元素
*/

public class SubSetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> subRes, int[] nums, int idx) {
        
        res.add(new ArrayList<>(subRes));
        if(idx == nums.length) return;
        
        for(int i = idx; i < nums.length; i++) {
            if(i > idx && nums[i] == nums[i-1]) continue;
            subRes.add(nums[i]);
            dfs(res, subRes, nums, i + 1);
            subRes.remove(subRes.size() - 1);
        }
    }
}