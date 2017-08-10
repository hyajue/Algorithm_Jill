package LinkedList;
/**
* Reverse a singly linked list.
* 
* Hint:
* A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//solution 1 : recursive
//复杂度
//时间 O(n) 空间O(n) 
public class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }
    private ListNode reverse(ListNode cur, ListNode prev){
        if(cur == null) return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return reverse(next, cur);
    }
}
/*
solution 2 
复杂度
时间 O(n) 空间O(1) 
思路：双指针迭代
Assume that we have linked list 1 → 2 → 3 → Ø, 
we would like to change it to Ø ← 1 ← 2 ← 3
*/ 
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
