package DFS;

//1. Recursive
//递归解法急速判断左右两边子树哪个depth最小，要注意如果有个节点只有一边孩子时，不能返回0，要返回另外一半边的depth。
public class MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        int min = Math.min(left, right);
        return 1 + (min > 0 ? min : Math.max(left, right));
    }
}
//2. iterative-> level order
public class MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                if(cur.left == null && cur.right == null)
                    return depth;
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                	queue.add(cur.right);
            }
        }
        return depth;
    }
}