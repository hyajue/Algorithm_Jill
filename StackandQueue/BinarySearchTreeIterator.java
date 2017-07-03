/**
* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
* 
* Calling next() will return the next smallest number in the BST.
* 
* Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
* 
* Challenge
* Extra memory usage O(1)
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
复杂度
时间O(1) 空间O(n)
思路：stack 
建立一个栈，先将最左边的结点从大到小压入栈,这样迭代器构造完后，栈顶元素就是最小的
hasNext()->判断栈是否为空
next()->每次pop出的栈顶元素，该元素肯定不会有左节点，但有可能有右节点，如果有，将右节点压入栈中，
而且还要继续将右节点下面的所有左节点压入栈中，以保证栈顶元素是最小的
*/ 
 
public class BSTIterator {
	
	private Stack<TreeNode> stack; 
	
	public BSTIterator(TreeNode root) {
		stack = new Stack<TreeNode>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}
	
	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();  
	}
	
	/** @return the next smallest number */
	public int next() {
		TreeNode cur = stack.pop();
		TreeNode ptrCur = cur;
		if (cur.right != null) {
			cur = cur.right;
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
		}
		return ptrCur.val;
	}
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */ 