package Trees;
/**
* Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
* 
* According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
* 
*         _______6______
*        /              \
*     ___2__          ___8__
*    /      \        /      \
*    0      _4       7       9
*          /  \
*          3   5
* For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
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
复杂度
时间：O(h) 空间：O(h)
思路：二分dfs
3种情况：
第一种情况是两个节点是在公共祖先的左右两侧,
第二种情况是都在公共祖先的左侧,
第三种情况是都在公共祖先的右侧,
如果是第二，第三种情况的话，公共祖先就在给定的两个点中比较上面的那一个
*/

public class LowestCommonAncestorOfABinarySearchTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;
        return left == null ? right : left;
    }
}

//@Method2 : iterative
//利用BST特性。左边永远比root小，右边永远比root大

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        while(true){
            if(root.val < p.val && root.val < q.val)
                root = root.right;
            else if(root.val > p.val && root.val >q.val)
                root = root.left;
            else
                break;
        }
        return root;
    }
}
