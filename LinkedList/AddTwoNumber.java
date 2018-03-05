/**
* You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
* 
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* 
* Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
* Output: 7 -> 0 -> 8
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
时间:O(n) 空间O(n)
思路：双指针 线性遍历
维护一个进位和一个当前位 两个指针分别指向链表1和链表2的头 每次加完和之后生成一个新的节点并把当前位赋值给新节点
链表1和2如果有剩余的就直接接在新链表后面
*

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddTwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode cur = dummy;
        int carry = 0;
        int digit = 0;
        
        while(ptr1 != null || ptr2 != null || carry != 0) {
            int sum = 0;
            if(ptr1 != null) {
                sum += ptr1.val;
                ptr1 = ptr1.next;
            }
            if(ptr2 != null) {
                sum += ptr2.val;
                ptr2 = ptr2.next;
            }
            sum += carry;
            digit = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(digit);
            cur = cur.next;
        }
        return dummy.next;
    }
}