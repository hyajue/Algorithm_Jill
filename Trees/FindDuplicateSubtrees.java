package Trees;
/**
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.
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
Note: Post Order traversal
因为code本身需要将root.left和root.left全部process完才可以, 所以是postorder
叶子加#，保证不会出现顺序一样，但是在树左孩子或者右孩子不一样的情况
*/
public class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(root == null) return res;
        postorder(root, new HashMap<String, Integer>(), res);
        return res;
    }
    private String postorder(TreeNode root, HashMap<String, Integer> map, List<TreeNode> res){
        if(root == null) return "#";
        String serial = root.val + "," + postorder(root.left, map, res) + "," + postorder(root.right, map, res);
        if(map.getOrDefault(serial, 0) == 1) res.add(root);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}
