package DP;
/**
* The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
* 
* Determine the maximum amount of money the thief can rob tonight without alerting the police.
* 
* Example 1:
*      3
*     / \
*    2   3
*     \   \ 
*      3   1
* Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
* Example 2:
*      3
*     / \
*    4   5
*   / \   \ 
*  1   3   1
* Maximum amount of money the thief can rob = 4 + 5 = 9.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
solution 1: tree shape DP
for a given root, we can choose rob or not rob:
rob_root = max(rob_L + rob_R , no_rob_L + no_nob_R + root.val)
no_rob_root = rob_L + rob_R
*/
public class HouseRobberIII {
	public int rob(TreeNode root) {
        if(root == null) return 0;
        return dp(root)[0];
    }
    private int[] dp(TreeNode root){
        int[] dp = {0, 0};
        if(root == null) return dp;
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        //no root, what's the value
        dp[1] = left[0] + right[0];
        //at root, what's the maximum
        dp[0] = Math.max(dp[1], left[1] + right[1] + root.val);
        return dp;
    }
}
