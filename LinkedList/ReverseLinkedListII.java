/**
* Reverse a linked list from position m to n. Do it in-place and in one-pass.
* 
* For example:
* Given 1->2->3->4->5->NULL, m = 2 and n = 4,
* 
* return 1->4->3->2->5->NULL.
* 
* Note:
* Given m, n satisfy the following condition:
* 1 ≤ m ≤ n ≤ length of list.
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
时间 O(n) 空间O(n)
思路： 双指针迭代+dummy head 
先移动到翻转起点的前一个节点 以及被翻转的第一个节点
eg: pre -> 1 -> 2 -> 3 -> 4
          cur
    pre -> 2 -> 1 -> 3 -> 4
               cur
    pre -> 3 -> 2 -> 1 -> 4 
                    cur
把sublist[m,n]翻转过来 然后调整关键节点指针 
有关链表的问题 搞一个dummy head比较方便 不需要考虑一些corner cases
*/

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        
        for(int i = 1; i < m; i++)
            pre = pre.next;
        
        ListNode cur = pre.next;
        
        for(int i = m; i < n; i++){
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }
}