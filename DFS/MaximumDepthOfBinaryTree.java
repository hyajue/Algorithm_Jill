package DFS;
/**
* Given a binary tree, find its maximum depth.
* 
* The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
//@Method1: recurvise
public class MaximumDepthOfBinaryTree {
	  public int maxDepth(TreeNode root) {
	    if (root == null) return 0;
	    int leftMax = maxDepth(root.left);
	    int rightMax = maxDepth(root.right);
	    return Math.max(leftMax, rightMax) + 1;
	  }
	}

//@Method2: iterative -- level order
public class MaximumDepthOfBinaryTree {
	public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                    queue.add(cur.right);
            }
            depth++;
        }
        return depth;
    }
}
