package StackandQueue;
/**
* Given a binary tree, return the postorder traversal of its nodes' values.
* 
* For example:
* Given binary tree {1,#,2,3},
*    1
*     \
*      2
*     /
*    3
* return [3,2,1].
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

// PostOrder: 左， 右， 根

/*
解法2
复杂度
时间：O(n) 空间:O(logN)
思路：栈
在弹栈的时候需要分情况讨论：
1.如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
2.如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯
*/

public class BinaryTreePostorderTraversal {
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root = null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode peekNode = stack.peek();
                //有右子树，且右子树还没被访问过
                if(peekNode.right != null && prev != peekNode.right){
                    root = peekNode.right;
                }else{
                    res.add(peekNode.val);
                    stack.pop();
                    prev = peekNode; //上一个访问节点
                }
            }
        }
    }
}

/*
解法1
复杂度
时间：O(n) 空间:O(logN)
思路：递归
*/
 
public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
		helper(root, res);
		return res;
  }
	
	private void helper(TreeNode root, List<Integer> res) {
		if (root == null) return;
		helper(root.left, res);
		helper(root.right, res);
		res.add(root.val);
	}

