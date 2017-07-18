package BinarySearch;

import java.util.Stack;

/**
* Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
* 
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
* 
* Follow up:
* What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
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
//Binary Search
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if(count >= k)
            return kthSmallest(root.left, k);
        else if(count + 1 < k)
            return kthSmallest(root.right, k - count - 1);
        return root.val;
    }
    private int countNodes(TreeNode root){
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

/*
复杂度
时间 O(N) 空间 O(N)
思路:中序遍历
因为左节点小于根节点小于右节点，二叉搜索树的一个特性就是中序遍历的结果就是树内节点从小到大顺序输出的结果
这里采用迭代 我们就可以在找到第k小节点时马上退出
follow up: 
如果我们频繁的操作该树，并且频繁的调用kth函数，有什么优化方法使时间复杂度降低至O(h)？h是树的高度。
根据提示，我们可以在TreeNode中加入一个rank成员，这个变量记录的是该节点的左子树中节点的个数，
其实就是有多少个节点比该节点小。这样我们就可以用二叉树搜索的方法来解决这个问题了。这个添加rank的操作可以在建树的时候一起完成。
*/
// iterative
public class KthSmallestElementInABST {
	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            k--;
            if(k == 0) return cur.val;
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return 0; //never hit if k is valid
    }
}

//recursive
public class Solution {
    private static int res = -1;
    private static int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return res;
    }
    private void helper(TreeNode root){
        if(root.left != null){
            helper(root.left);
        }
        count--;
        if(count == 0){ 
            res = root.val;
            return;
        }
        if(root.right != null){
            helper(root.right);
        }
    }
}
