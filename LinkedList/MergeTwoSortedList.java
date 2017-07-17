package LinkedList;
/**
* Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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
@Method 1 : iterative
复杂度
时间 O(N) 空间 O(1)
思路:顺序插入
该题就是简单的把两个链表的节点拼接起来，我们可以用一个Dummy头，将比较过后的节点接在这个Dummy头之后。
最后如果有没比较完的，说明另一个list的值全比这个list剩下的小，而且拼完了，所以可以把剩下的直接全部接上去。
*/
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1 == null) 
            cur.next = l2;
        if(l2 == null)
            cur.next = l1;
        return dummy.next;
    }
}

//@Method 2: recursive
public class MergeTwoSortedList {
	if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
        l1.next =  mergeTwoLists(l1.next, l2);
        return l1;
    }else{
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
