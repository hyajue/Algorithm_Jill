package DFS;
/**
* Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
* 
* For example:Given a binary tree {1,2,3,4,5},
* 
*     1
*    / \
*   2   3
*  / \
* 4   5
* return the root of the binary tree [4,5,2,#,#,3,1].
* 
*    4
*   / \
*  5   2
*     / \
*    3   1  
* 	 
*/

/*
solution 1
复杂度
时间O(n) 空间O(n)
思路：递归
关于upside down:
   root      -->     left   
  /    \            /    \
left   right     right   root
其实就是三个节点顺时针旋转 直观滴想 整个树upside down后的根是原树的最左边的叶节点
对给定的例子分析，将最左左子树4-2-5上下颠倒后 4作为新根 与1,3构成新子树结构 4-1-3 此时出现问题
把4旋转到树根后 如何解决1 3及其子树 和4的子树的连接问题 根据所给的例子 意思是把1-3连接到4最右孩子的上面
而且3变成左孩子 1变成右孩子 
*/
public class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if(root == null) return root;
		TreeNode parent = root;
		TreeNode left = root.left;
		TreeNode right = root.right;
		if(left != null) {
			TreeNode newRoot = upsideDownBinaryTree(left);
			left.left = right;
			left.right = parent;
			return newRoot;
		}
		//左右都为空。直接return root
		return root;
	}
}

/*
solution 2
复杂度 
时间O(n) 空间O(1)
思路：迭代 
与解法1思路完全一样 只是表达方式换成迭代法
*/
public class BinaryTreeUpsideDown {
	  public TreeNode upsideDownBinaryTree(TreeNode root) {
		  if(root == null) return root;
		  TreeNode cur = root;
		  TreeNode prev = null;
		  TreeNode next = null;
		  TreeNode right = null;
		  
		  while(cur != null) {
			  next = cur.left;
			  
			  cur.left = right;
			  right = cur.right;
			  prev = cur;
			  cur = next;
		  }
		  return prev;
	  }
}

