package TwoPointers;
/**
* Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
* 
* Note: Do not modify the linked list.
* 
* Follow up:
* Can you solve it without using extra space?
*/
/*
复杂度
时间：O(n) 空间:(1)
思路：双指针 追击问题
Linked List Cycle的扩展，就是在确定是否有cycle之后还要返回cycle的起始点的位置
设整个链表长L，环入口与相遇点距离为x，起点到环入口点的距离为a, 环长为r, fast指针在环内已循环了n圈
a + x = nr
r = L - a
a + x = (n – 1)r + r = (n-1)r + L - a
a = (n-1)r + (L – a – x)
(L – a – x)为相遇点到环入口点的距离，由此可知，从链表头到环入口点等于(n-1)循环内环+相遇点到环入口点，于是我们从链表头、与相遇点分别设一个指针，
每次各走一步，两个指针必定相遇，且相遇第一点为环入口点
*/ 
public class LinkedListCycleII {
	public ListNode detectCycle(ListNode head) {
	    if (head == null || head.next == null) return null;
	    ListNode slow = head;
		ListNode fast = head;
	    while(fast != null && fast.next != null) {
			fast = fast.next.next;
		    slow = slow.next;
		    if (fast == slow) break;
		}
		if (fast == null || fast.next == null) return null; // no cycle
		slow = head;
		while(slow != fast) {
		    slow = slow.next;
		    fast = fast.next;
		}
		return slow;
	}
}
