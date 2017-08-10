package DFS;
/**
* Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
* 
* For example:
* Given the following binary tree,
*    1            <---
*  /   \
* 2     3         <---
*  \     \
*   5     4       <---
* You should return [1, 3, 4].
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
由于每层只选择一个节点，所以优先访问右侧的节点。
当List<Integet>的大小与层的深度一致时，说明已经加入了最右侧的节点，
该层的其他节点无需再加入
*/
public class BinaryTreeRightSIdeView {
	public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        dfs(res, root, 0);
        return res;
    }
    private void dfs(List<Integer> res, TreeNode root, int level){
        if(root == null) return;
        if(level == res.size())
            res.add(root.val);
        dfs(res, root.right, level + 1);
        dfs(res, root.left, level + 1);
    }
}

//BFS: (iterative)
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            res.add(q.peek().val);
            for(int i = 0; i < size; i++){
                TreeNode temp = q.poll();
                if(temp.right != null)
                    q.offer(temp.right);
                if(temp.left != null)
                    q.offer(temp.left);
            }
        }
        return res;
    }	
}
