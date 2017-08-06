package Trees;
/**
* Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
* 
* According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
* 
*         _______3______
*        /              \
*     ___5__          ___1__
*    /      \        /      \
*    6      _2       0       8
*          /  \
*          7   4
* For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
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

//@Method 1: recursive 
/*
复杂度
时间：O(h) 空间：O(h)
思路：二分dfs
3种情况：
第一种情况是两个节点是在公共祖先的左右两侧,
第二种情况是都在公共祖先的左侧,
第三种情况是都在公共祖先的右侧,
如果是第二，第三种情况的话，公共祖先就在给定的两个点中比较上面的那一个
*/ 
public class LowestCommonAncestorOfABinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;
        return left == null ? right : left;
    }
}

/*
Method 2: iterative
用Deque和hashmap
hashmap用来记录node和其parent node的对应
deque用来记录一个遍历的顺序.当p和q都遍历到了，则停止遍历。
此时用一个hashset来记录p以及其parent。最后遍历q和它的parent，遍历到当前node包含在hashset里为止。
这就是q和p的LCA
*/
/**
java.util.Deque接口是java.util.Queue接口的子接口。
它代表的队列包含从队列两端添加和删除元素。
"Deque" 是 "Double Ended Queue"的简称。

Deque的实现类:
java.util.ArrayDeque
java.util.LinkedList

LinkedList是一个标准的deque/queue实现。
ArrayDeque内部使用数组保存元素，如果元素数量超过了内部数组的大小，内部将产生一个新的数组，然后将数据转移过去，用来满足需求，换句话说，ArrayQeque自身有扩容功能
*/
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);
        //确保已经遍历过p和q
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode node = stack.pop();
            if(node.left != null){
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if(node.right != null){
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestor = new HashSet<>();
        while(p != null){
            ancestor.add(p);
            p = parent.get(p);
        }
        while(!ancestor.contains(q)){
            q = parent.get(q);
        }
        return q;
    }
}
