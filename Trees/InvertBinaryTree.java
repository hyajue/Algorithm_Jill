package Trees;
/**
* Invert a binary tree.
* 
*      4
*    /   \
*   2     7
*  / \   / \
* 1   3 6   9
* 
* to
* 
*      4
*    /   \
*   7     2
*  / \   / \
* 9   6 3   1
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
时间：O(n) 空间：O(n)
思路：recursive, postorder traversal
递归的终止条件是当遇到空节点或叶子节点时，不再交换，直接返回该节点。
对于其他的节点，我们分别交换它的左子树和右子树，然后将交换过后的左子树赋给右节点，右子树赋给左节点。
可以采用后序遍历的自下而上的交换，如果用先序遍历的话就是自上而下的交换。
*/
public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
/*
复杂度
时间：O(n) 空间：O(n)
思路：BFS, level order traversal
用queue一层层创建新的节点
*/ 
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return root;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            TreeNode temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;
            if(cur.left != null)
                queue.offer(cur.left);
            if(cur.right != null)
                queue.offer(cur.right);
        }
        return root;
    }
}