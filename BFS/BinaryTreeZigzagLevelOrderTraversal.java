/**
* Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
* 
* For example:
* Given binary tree [3,9,20,null,null,15,7],
*     3
*    / \
*   9  20
*     /  \
*    15   7
* return its zigzag level order traversal as:
* [
*   [3],
*   [20,9],
*   [15,7]
* ]
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
思路：BFS
使用队列实现蛇形遍历，发现奇数行的遍历仍然可以按照广度优先遍历的方式实现，
而对于偶数行，只需要翻转一下
*/

class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean odd = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subRes = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                subRes.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
            }
            // 每层有的叶子数并不是 层数！！！！！
            //if(size % 2 == 0)

            //整体一层添加完，整体反转
            if(!odd)
                Collections.reverse(subRes);
            res.add(subRes);
            odd = !odd;
        }
        return res;
    }
}



//Method 2: DFS

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if(root == null) return;
        if(res.size() <= level)
            res.add(new LinkedList<>());
        //奇数层，每次向开头添加
        if(level % 2 == 1)
            res.get(level).add(0, root.val);
        else
            res.get(level).add(root.val);
        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }
}