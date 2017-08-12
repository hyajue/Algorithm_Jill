package LinkedList;
/**
* Given a singly linked list, determine if it is a palindrome.
* 
* Follow up:
* Could you do it in O(n) time and O(1) space?
*/

/*
复杂度
时间：O(O) 空间：O(1)
思路：
1. 找链表中点
2. 从中点之后翻转列表
3. 双指针一个指向头,另一个指向中点下一个,顺序对比.如果都一样,则原链表是回文列表

Note: 为什么要翻转后半段而不是前半段呢？ 这里涉及到一个one pass问题。
对于奇数的情况 比如 1-2-3-2-1  比较 1-2-3 和 1-2 比较好。 否则的话要额外增加很多步骤比较 3-2-1 和 2-1  
*/
public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //保证右侧永远是小于等于左侧的(奇数情况)
        slow = slow.next;
        slow = reverse(slow);
        fast = head;
        while(slow != null){
            if(slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
