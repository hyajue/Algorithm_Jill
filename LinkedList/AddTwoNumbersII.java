package LinkedList;
/**
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
//@Method1: Stack
//Time: O(n) Space: O(n)
public class AddTwoNumbersII {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        while(l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode(0);
        int sum = 0;
        while(!s1.isEmpty() || !s2.isEmpty()){
            if(!s1.isEmpty()) sum += s1.pop();
            if(!s2.isEmpty()) sum += s2.pop();
            head.val = sum % 10;
            ListNode newHead = new ListNode(sum / 10);
            newHead.next = head;
            head = newHead;
            sum /= 10;
        } 
	//Save some extra space!!
        return head.val == 0 ? head.next : head;
    }
}


//
ListNode head = new ListNode(-1);
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int sum = 0;
            if(!s1.isEmpty()) {
                sum += s1.pop();
            }
            if(!s2.isEmpty()) {
                sum += s2.pop();
            }
            head.val = (carry + sum) % 10;
            carry = (carry + sum) / 10;
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            head = newHead;
        }
        return head.next;
