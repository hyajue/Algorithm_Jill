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
        //Without this, it will throw nullpointer exception.
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


//Method: DFS

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if(root == null) return;
        // why <= not ==
        if(res.size() == level)
            res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }
}
