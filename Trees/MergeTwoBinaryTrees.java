/**
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
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
Let's create a recursive solution.

If both trees are empty then we return empty.
Otherwise, we will return a tree. The root value will be t1.val + t2.val, except these values are 0 if the tree is empty.
The left child will be the merge of t1.left and t2.left, except these trees are empty if the parent is empty.
The right child is similar.
*/

public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) return null;
        int val = t1 == null ? t2.val : t2 == null ? t1.val : t1.val + t2.val;
        // int val = (t1 == null ? 0 : t1.val) + ( t2 == null ? 0 : t2.val);
        TreeNode newNode = new TreeNode(val);
        //注意这里t1和t2某一个可能是null。如果不处理，t1.left可能会导致nullPointerException
        newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null :t2.left);
        newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null :t2.right);
        return newNode;
    }
}