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
Method 1.
This method also works for those who are not BSTs. 
The idea is to use a hashtable to save the values of the nodes in the BST. 
Each time when we insert the value of a new node into the hashtable, we check if the hashtable contains k - node.val.

Time Complexity: O(n), Space Complexity: O(n).
*/

public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<Integer>();
        return dfs(root, k, set);
    }
    private boolean dfs(TreeNode root, int k, Set<Integer> set){
        if(root == null) return false;
        if(set.contains(k - root.val))
            return true;
        set.add(root.val);
        return dfs(root.left, k, set) || dfs(root.right, k, set);
    }
}

/*
The idea is to use a sorted array to save the values of the nodes in the BST 
by using an inorder traversal. 
Then, we use two pointers which begins from the start and end of the array to find if there is a sum k.

Time Complexity: O(n), Space Complexity: O(n).
*/
public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while(l < r){
            if(list.get(l) + list.get(r) == k)
                return true;
            else if(list.get(l) + list.get(r) < k)
                l++;
            else
                r--;
        }
        return false;
    }
    private void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}



