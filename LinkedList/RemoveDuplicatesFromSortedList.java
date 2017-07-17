package LinkedList;
/**
* Given a sorted linked list, delete all duplicates such that each element appear only once.
* 
* For example,
* Given 1->1->2, return 1->2.
* Given 1->1->2->3->3, return 1->2->3.
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
时间：O(n) 空间O(1)
思路:单指针扫面 
维护一个指针cur 如果cur下一个节点的值跟当前值一样 就跳过下一个节点;否则继续遍历
注意当发现下个节点值跟当前值相等时 仅修改cur.next 不移动cur 以便进行下一轮检查 
*/
public class RemoveDuplicatesFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
}
