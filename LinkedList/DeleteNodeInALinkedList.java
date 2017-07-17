package LinkedList;
/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 *
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 */
/*
 * Time: O(1)   Space: O(1).
 * 
 * Since we do not have access to the node before the one we want to delete, 
 * we cannot modify the next pointer of that node in any way. 
 * Instead, we have to replace the value of the node we want to delete with the value in the node after it, 
 * and then delete the node after it.
 */
public class DeleteNodeInALinkedList {
//	public void deleteNode(ListNode node) {
//        if(node == null) return;
//        if(node.next == null) node = null;
//        else{
            node.val = node.next.val;
            node.next = node.next.next;
//        }
    }
}
