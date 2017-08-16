/**
* Given a set of distinct integers, nums, return all possible subsets.
* 
* Note: The solution set must not contain duplicate subsets.
* 
* For example,
* If nums = [1,2,3], a solution is:
* 
* [
*   [3],
*   [1],
*   [2],
*   [1,2,3],
*   [1,3],
*   [2,3],
*   [1,2],
*   []
* ]
*/

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        //Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> subRes, int[] nums, int idx) {
        
        res.add(new ArrayList<>(subRes));
        if(idx == nums.length) return;
        
        for(int i = idx; i < nums.length; i++) {
            subRes.add(nums[i]);
            dfs(res, subRes, nums, i + 1);
            subRes.remove(subRes.size() - 1);
        }
    }
}