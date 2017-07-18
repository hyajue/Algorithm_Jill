package DFS;
/**
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]

 */
//DFS
//Time: O(n), Space O(n)
//Just a simple pre-order traverse idea. 
//Use depth to expand result list size,
//And put the max value in the appropriate position.
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root, 0);
        return res;
    }
    private void helper(List<Integer> res, TreeNode root, int depth){
        //exit
        if(root == null) return;

        if(depth == res.size()){
            res.add(root.val);
        }else{
            res.set(depth, Math.max(root.val, res.get(depth)));
        }
        helper(res, root.left, depth + 1);
        helper(res, root.right, depth + 1);
    }
}

//BFS 
//Time: O(n), Space O(n)
public class FindLargestValueInEachTreeRow {
	public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                root = queue.poll();
                max = Math.max(root.val, max);
                if(root.left != null)
                    queue.offer(root.left);
                if(root.right != null)
                    queue.offer(root.right);
            }
            res.add(max);
        }
        return res;
    }
}
