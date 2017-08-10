package LinkedList;
/**
Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
 */

/*
Time: O(n)  Space: O(1)
思路是遍历链表，找到第一个不为9的数字，如果找不这样的数字，说明所有数字均为9，那么在表头新建一个值为0的新节点，进行加1处理，然后把右边所有的数字都置为0即可
 */
public class PlusOneLinkedList {
	public ListNode plusOne(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head;
		ListNode ptr = dummy; //最低的一位不为9的位置
		while(cur != null) {
			if(cur.val != 9)
				ptr = cur;
			cur = cur.next;
		}
		ptr.val++;
		cur = ptr.next;
		while(cur != null) {
			cur.val = 0;
			cur = cur.next;
		}
		return dummy.val == 1 ? dummy : dummy.next;
	}
}
//recursive
//With recursion, we can visit list in reverse way! 
public class PlusOneLinkedList {
	public ListNode plusOne(ListNode head) {
		public ListNode plusOne(ListNode head) {
		    if( DFS(head) == 0){
		        return head;
		    }else{
		        ListNode newHead = new ListNode(1);
		        newHead.next = head;
		        return newHead;
		    }
		}

		public int DFS(ListNode head){
		    if(head == null) return 1;
		    
		    int carry = DFS(head.next);
		    
		    if(carry == 0) return 0;
		    
		    int val = head.val + 1;
		    head.val = val%10;
		    return val/10;
		}
	}
}
