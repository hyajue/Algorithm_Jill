package LinkedList;
/**
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
 */
/*
 Time: O(n) Space: O(1)
 思路：oddList的头是head，tail是odd
 	  evenlist的头是evenHead，tail是even
 	  顺序遍历list，用even作为遍历的指针，将odd和even.next连在一起，even.next和odd.next连在一起
 	  遍历结束后，将odd和evenHead连在一起
 */
public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        ListNode odd = head;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
