package Trees;
/**
* Given a complete binary tree, count the number of nodes.
* 
* Definition of a complete binary tree from Wikipedia:
* In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
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
 @Method1:
 recursive
 Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). 
 So overall O(log(n)^2).
 第一层高度为0，root为null高度为-1， 以此类推
 树高度为h
 统计左右子树高度。如果高度一致h-1，那么证明左边子树是个full BT，个数是2^h-1,加1(root)，加右子树个数
 如果高度不一致，那么右子树是个高度为h-2的full BT,个数是2^(h-1),加1，加左子树个数
 */
public class CountCompleteTreeNode {
	public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 : 
            height(root.right) == h - 1 ?
                (1 << h) + countNodes(root.right) :
                (1 << h - 1) + countNodes(root.left);        
    }
    private int height(TreeNode root){
        return root == null ? -1 : 1 + height(root.left);
    }
}
//@Method 1: iterative
//with the benefit that I don't recompute h in every step
public class Solution {
    public int countNodes(TreeNode root) {
        int h = height(root);
        int res = 0;
        while(root != null){
            if(height(root.right) == h - 1){
                res += 1 << h;
                root = root.right;
            }else{
                res += 1 << h - 1;
                root = root.left;
            }
            h--; //少了一层了！！！！
        }
        return res;
    }
    private int height(TreeNode root){
        return root == null ? -1 : 1 + height(root.left);
    }
}

/*@Method 2:
  每一步查看这棵树是不是full BT，如果是，返回2^h - 1
  不是的话，1 + 左边个数 + 右边个数 
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int left = countLeft(root.left);
        int right = countRight(root.right);
        if(left == right)
            return (1 << left + 1) - 1;
        else
            return 1 + countNodes(root.left) + countNodes(root.right);
    }
    private int countLeft(TreeNode root){
        return root == null ? 0 : 1 + countLeft(root.left);
    }
    private int countRight(TreeNode root){
        return root == null ? 0 : 1 + countRight(root.right);
    }
}


