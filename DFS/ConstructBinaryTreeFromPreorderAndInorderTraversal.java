package DFS;
/**
* Given preorder and inorder traversal of a tree, construct the binary tree.
* 
* Note:
* You may assume that duplicates do not exist in the tree.
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
假设树的先序遍历是12453687，中序遍历是42516837。这里最重要的一点就是先序遍历可以提供根的所在，
而根据中序遍历的性质知道根的所在就可以将序列分为左右子树。比如上述例子，我们知道1是根，
所以根据中序遍历的结果425是左子树，而6837就是右子树
接下来根据切出来的左右子树的长度又可以在先序便利中确定左右子树对应的子序列（先序遍历也是先左子树后右子树）
根据这个流程，左子树的先序遍历和中序遍历分别是245和425，右子树的先序遍历和中序遍历则是3687和6837
重复以上方法，可以继续找到根和左右子树，直到剩下一个元素 可以看出这是一个比较明显的递归过程 
对于寻找根所对应的下标，我们可以先建立一个HashMap，以免后面需要进行线行搜索 
这样每次递归中就只需要常量操作就可以完成对根的确定和左右子树的分割
算法最终相当于一次树的遍历，每个结点只会被访问一次，所以时间复杂度是O(n)
空间我们需要建立一个map来存储元素到下标的映射，所以是O(n)
注意条件里说明不能有重复元素 如果有 则结果树不唯一 
*/ 

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null)
            return null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        TreeNode root = helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
        return root;
    }
    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> map){ // 12453687  42516837
        if(preL > preR || inL > inR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int idx = map.get(preorder[preL]);
        int leftLen = idx - inL;
        root.left = helper(preorder, preL+1, preL+idx-inL, inorder, inL, idx-1, map);
        root.right = helper(preorder, preL+idx-inL+1, preR, inorder, idx+1, inR, map);
        return root;
    }
}
