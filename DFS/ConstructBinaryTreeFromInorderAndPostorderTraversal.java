package DFS;
/**
* Given inorder and postorder traversal of a tree, construct the binary tree.
* 
* Note:
* You may assume that duplicates do not exist in the tree.
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
和Construct Binary Tree from Preorder and Inorder Traversal思路完全一样 
这里的区别是要从中序遍历和后序遍历中构造出树，算法还是一样，只是现在取根是从后面取（因为后序遍历根是遍历的最后一个元素）
时间复杂度和空间复杂度也还是O(n)
*/ 
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null)
            return null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        TreeNode root = helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
        return root;
    }
    private TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int pL, int pR, Map<Integer, Integer> map){
        if(inL > inR || pL > pR) return null;
        TreeNode root = new TreeNode(postorder[pR]);
        int idx = map.get(root.val);
        int leftLen = idx - inL - 1;
        root.left = helper(inorder, inL, idx - 1, postorder, pL, pL + leftLen, map);
        root.right = helper(inorder, idx+1, inR, postorder, pL + leftLen + 1, pR - 1, map);
        return root;
    }
}
