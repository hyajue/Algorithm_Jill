package BFS;
/**
* Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
* 
* For example:
* Given binary tree [3,9,20,null,null,15,7],
*     3
*    / \
*   9  20
*     /  \
*    15   7
* return its level order traversal as:
* [
*   [3],
*   [9,20],
*   [15,7]
* ]
*/

/*
复杂度
时间:O(n) 空间：O(n)
思路：BFS
*/
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> subRes = new ArrayList<Integer>();
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                subRes.add(cur.val);
                if(cur.left != null)
                    queue.offer(cur.left);
                if(cur.right != null)
                    queue.offer(cur.right);
            }
            res.add(subRes);
        }
        
        return res;
    }
}
