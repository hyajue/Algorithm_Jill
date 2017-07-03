package StackandQueue;
/**
* Given a binary tree, return the preorder traversal of its nodes' values.
* 
* For example:
* Given binary tree {1,#,2,3},
*    1
*     \
*      2
*     /
*    3
* return [1,2,3].
* 
* Note: Recursive solution is trivial, could you do it iteratively?
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
解法2
复杂度
时间：O(n) 空间:O(logN)
思路：栈
*/
public class BinaryTreePreorderTraversal {
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }else{
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }
}

/*
解法1
复杂度
时间：O(n) 空间:O(logN)
思路：递归
*/
public class BinaryTreePreorderTraversal {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
		helper(root, res);
		return res;
	}
	
	private void helper(TreeNode root, List<Integer> res) {
		if (root == null) return;
		res.add(root.val);
		helper(root.left, res);
		helper(root.right, res);
	}
}
