/**
* Sort a linked list using insertion sort.
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
时间 O(n^2) 空间O(1)
思路：插入排序 
做一个空链表，然后不断加入原链表中的最小元素即可。
cur是原链表head的指针，不断向后扫描；pre是空链表dummy的指针，用pre.next与cur所指向的结点进行比较，
一旦发现排列好的新链表中有大于cur的结点，就把cur放在pre.next，然后进行下一轮循环：cur.next作为原链表新的cur，pre返回新链表起点dummy
最后，当cur = null，即遍历完整个原链表之后，新链表排序完成。返回dummy.next即可。
*/ 


class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next  == null)
            return head;
        ListNode cur = head;
        ListNode dummy = new ListNode(-1);
        
        while(cur != null) {
            ListNode pre = dummy;
            while(pre.next != null && pre.next.val < cur.val) {
                    pre = pre.next;
            }
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}


//TLE:
//Shouldn't be creating new nodes, should try to use existing nodes, and re-do the link.
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next  == null)
            return head;
        ListNode cur = head;
        ListNode dummy = new ListNode(-1);
        
        while(cur != null) {
            int key = cur.val;
            ListNode pre = dummy;
            while(pre.next != null) {
                if(pre.next.val < cur.val)
                    pre = pre.next;
            }
            ListNode next = pre.next;
            pre.next = new ListNode(cur.val);
            pre.next.next = next;
            cur = cur.next;
        }
        return dummy.next;
    }
}