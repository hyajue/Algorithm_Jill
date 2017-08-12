package Design;
/**
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
* 
* Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
* 
* The encoded string should be as compact as possible.
* 
* Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
//@Method2: using stack
/*这种解法里，子树为空的支不加入String. 
 在Deserialize的时候维护一个当前循环的变量iteratorNode。维护一个parentNode。使得当前循环node永远在parentNode的左子树。
 按照顺序依次读取数据，如果该点val比iteratoNode的值小，则他是iteratorNode的左子树，压栈iteratorNode，并更新iteratorNode。
 如果改点val比iteratorNode大，那么看stack是否为空：
   stack不为空：弹出栈顶，parentNode，跟val比较。如果parent的值更大，那么这点就是iteratorNode的右孩子。 如果比parent的值还大，那么更新iteratorNode，继续弹出parentNode。
   Stack为空：该点是iteratorNode的右孩子。
 更新iteratorNode。
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //if(root == null) return "null";
        StringBuilder sb = new StringBuilder("");
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(cur == null) continue;
            sb.append(cur.val).append(",");
            stack.push(cur.right);//用stack，FILO
            stack.push(cur.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] nodes = data.split(",");
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        TreeNode iteratorNode = root;
        for(int i = 1; i < nodes.length; i++){
            TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
            if(node.val < iteratorNode.val){
                iteratorNode.left = node;
                stack.push(iteratorNode);
            }else{ 
                while(!stack.isEmpty()){
                    TreeNode parentNode = stack.pop();
                    if(node.val < parentNode.val){
                        iteratorNode.right = node;
                        stack.push(parentNode);
                        break;
                    }else{
                        iteratorNode = parentNode;
                    }
                }
                if(stack.isEmpty()){
                    iteratorNode.right = node;
                }
            }
            iteratorNode = node;
        }
        return root;
    }
}


/*@Method1: recursion
时间：O(n) 空间：O(n)

方法同 BinaryTree一样。缺点是多了结尾符 "null"
选择先序遍历,维护一个队列,反序列化时,先将各节点加入队列中, 然后在按照先序遍历的顺序构造BST
*/
public class SerializeAndDeserializeBST {
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String left = serialize(root.left);
        String right = serialize(root.right);
        sb.append(",").append(left).append(",").append(right);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<String>();
        String[] nodes = data.split(",");
        for(String node : nodes)
            queue.offer(node);
        return helper(queue);
    }
    private TreeNode helper(Queue<String> queue){
        if(queue == null || queue.size() == 0)
            return null;
        String cur = queue.poll();
	//!!!! String comparison
        if(cur.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }
}
//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));
