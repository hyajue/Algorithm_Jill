package LinkedList;
/**
* Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
* 
* For example,
* Given 1->2->3->3->4->4->5, return 1->2->5.
* Given 1->1->1->2->3, return 2->3.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
复杂度
时间O(n) 空间O(1)
思路：双指针
双指针操作，最后返回dummy.next，以防止head结点即duplicate的情况返回错误的结果。
令pre = dummy, cur = head，用cur进行查重操作，pre是cur的前结点。
当cur和cur.next等值的时候，cur循环后移至，直到cur跟下一个节点值不同，用pre指向新的cur.next即可。
*/ 
public class RemoveDuplicatesFromSortedListII {
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null){
            while(cur.next != null && cur.next.val == cur.val)
                cur = cur.next;
            if(pre.next != cur)
                pre.next = cur.next;
            else
                pre = pre.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
