package TwoPointers;
/**
* Given a linked list, determine if it has a cycle in it.
* 
* Follow up:
* Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
复杂度
时间：O(n) 空间:O(1)
思路：
比较直接容易想到的方法，是用一个hashset，然后把扫过的结点放入hashset中，如果出现重复则返回true -> S(n) = O(n)
如何不用额外空间来判断是否有cycle，用很典型的快慢指针大法。基本想法就是维护两个指针，一个走的快，一个走得慢，
当一个走到链表尾或者两个相遇时，能得到某个想要的结点，比如相遇点，中点等
假设两个指针fast和slow slow一倍速移动，fast两倍速移动 有一个链表，假设在cycle开始前有a个结点，
cycle长度是c，而我们相遇的点在cycle开始后b个结点。那么想要两个指针相遇，意味着要满足以下条件：
(1) a+b+mc=s; (2) a+b+nc=2s; 其中s是指针走过的步数，m和n是两个常数。
这里面还有一个隐含的条件，就是s必须大于等于a，否则还没走到cycle里面，两个指针不可能相遇。假设k是最小的整数使得a<=kc，
也就是说(3) a<=kc<=a+c。接下来我们取m=0, n=k，用(2)-(1)可以得到s=kc以及a+b=kc。
由此我们可以知道，只要取b=kc-a(由(3)可以知道b不会超过c)，那么(1)和(2)便可以同时满足，使得两个指针相遇在离cycle起始点b的结点上。
因为s=kc<=a+c=n，其中n是链表的长度，所以走过的步数小于等于n，时间复杂度是O(n)。并且不需要额外空间，空间复杂度O(1)
使用快慢指针 不仅能判断是否有环 还能确定环的位置
*/ 
 
public class LinkedListCycle {
  public boolean hasCycle(ListNode head) {
    if (head == null) return false;
	  ListNode fast = head;
	  ListNode slow = head;
	  //由于fast每次走两步,所以要判断fast.next非空,但fast.next能执行的前提是fast!=null
		while(fast != null && fast.next != null) {
	    fast = fast.next.next;
	    slow = slow.next;
	    if(fast == slow) {
	      return true;
	    }
	  }
	  return false;
  }
}
