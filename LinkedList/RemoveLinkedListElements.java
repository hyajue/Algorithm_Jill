package LinkedList;
/**
* Remove all elements from a linked list of integers that have value val.
* 
* Example
* Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
* Return: 1 --> 2 --> 3 --> 4 --> 5
*/
/*
复杂度
时间：O(n) 空间：O(1)
思路：顺序遍历+哑头
声明一个哑头,连到头上面,之后从哑头开始依次遍历,找到符合条件的节点就跳过
*/ 
public class RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
	    if (head == null) return head;
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    head = dummy;
	    while (head.next != null) {
				if (head.next.val == val) {
					head.next = head.next.next;
				} else {
					head = head.next;
				}
			}
	    return dummy.next;		
	  }
}
