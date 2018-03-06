package LinkedList;
/**
* Write a program to find the node at which the intersection of two singly linked lists begins.
* 
* 
* For example, the following two linked lists:
* 
* A:          a1 → a2
*                    ->
*                      c1 → c2 → c3
*                    ->            
* B:     b1 → b2 → b3
* begin to intersect at node c1.
* 
* 
* Notes:
* 
* If the two linked lists have no intersection at all, return null.
* The linked lists must retain their original structure after the function returns.
* You may assume there are no cycles anywhere in the entire linked structure.
* Your code should preferably run in O(n) time and use only O(1) memory.
*/
/*
复杂度
时间 O(n) 空间O(1)
思路：双指针 追击问题
两个指针分别从两个链表头出发 依次每次走一步 无论谁走到null 下一个开始走另一个的开头 
相遇时，有：
LenA + LenShare + LenB = LenB + LenShare + LenA 
  lenA    LenShare
<------>|<-------->
   <--->|<-------->
  LenB    LenShare 
由于循环结束的条件为两指针相遇 故一定在交点处相遇 


Or, if there's no intersection,  lenA + lenB = lenB + lenA
Iteration ends when both ptrs point to null, and return null
*/ 
public class IntersectionOfTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	    if (headA == null || headB == null) return null;
			ListNode ptrA = headA;
			ListNode ptrB = headB;
			while (ptrA != ptrB) {
				ptrA = (ptrA == null) ? headB : ptrA.next;
				ptrB = (ptrB == null) ? headA : ptrB.next;
			}
			return ptrA;
	  }
}
