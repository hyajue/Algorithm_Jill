package Trees;
/**
* Two elements of a binary search tree (BST) are swapped by mistake.
* 
* Recover the tree without changing its structure.
* 
* Note:
* A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
 我们知道：二叉搜索树的中序遍历是一个升序序列，我们只要对一个二叉树进行中序遍历，然后检验其序列是否为升序便可以判断该二叉树是否为合法的二叉搜索树
 本题中二叉搜索树的两个节点数值发生了互换，那么正常的升序序列必然会出现局部降序，我们只要找到局部降序点并将对应数据进行交换，便可恢复二叉搜索树。
 关于局部降序有几种情况必须分析清楚：
      1、特殊情况：输入二叉搜索树根节点为空，或者只有一个节点，直接返回，无处理；
      2、输入二叉搜索树只有两个节点，直接互换，返回；
      3、输入二叉搜索树的节点数>=3，中序遍历序列出现局部降序的位置有两种情况：
        a.相邻，如[3,6,4]；b.不相邻，如[6,4,5,3],两种情况，
        最明显的区别就是前者只会出现一次降序点（4<6）；后者则有两个降序点(4<6,3<5)，且第一个降序点前一个数6是错误位置，后一个降序点后一个数3是错误位置，一定要注意。
 中序遍历，或者Morris Traversal
 */
/*
 @Method 1: Inorder Traversal
 Time: O(n) Space: O(logn)
 递归中序遍历,维护三个指针：两个是要被交换的节点first,second，一个是当前遍历到结点的前序节点pre 
如果当前遍历到的前序节点值大于等于当前节点则该节点存在问题，需要交换
注意如果是发现的第一个有问题的节点，那么需要交换的节点为pre；如果是第二次发现的有问题的节点，
那么需要被交换的节点是当前节点cur。
这个解法比较concise 其空间复杂度是O(logN) 因为是递归 
 */
//recursive
public class RecoverBinarySearchTree {
	TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        inOrder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        if(pre.val >= root.val){
            if(first == null)
                first = pre;
            second = root;
        }
        pre = root;
        inOrder(root.right);
    }
}
// iterative
public class Solution {
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                // TreeNode peekNode = stack.peek();
                if(pre.val >= cur.val){
                    if(first == null)
                        first = pre;
                    second = cur;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

/*
 @Method 2: Morris Traversal : Threaded Binary Tree
 该方法只需要O(1)空间，而且同样可以在O(n)时间内完成。 只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了

中序遍历原理：
1. 如果当前节点cur的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
3. 重复以上1、2直到当前节点为空。
*/
public class Solution {
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode first = null;
        TreeNode second = null;
        
        while(cur != null){
            if(pre != null && pre.val >= cur.val){
                if(first == null)
                    first = pre;
                second = cur;
            }
            if(cur.left != null){
                TreeNode temp = cur.left;
                while(temp.right != null && temp.right != cur)
                    temp = temp.right;
                if(temp.right == cur){
                    temp.right = null;
                    pre = cur;
                    cur = cur.right;
                }else{
                    temp.right = cur;
                    cur = cur.left;
                }
            }else{
                pre = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}




