/**
* A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
* 
* Return a deep copy of the list.
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
solution 1
复杂度
时间：O(n) 空间O(1)
思路：双指针+三次扫描
fist pass: copy each node and connect copy next to original 
second pass: copy random field to copied node: cur.next.random = cur.random.next
third pass: get a deep copy from mixed list 
*/ 
 
public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode cur = head;
        // pass 1: copy each node and connect newNode to next of original one
        while(cur != null){
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        // pass 2: copy random field to newNode 
        cur = head;
        while(cur != null && cur.next != null){
            if(cur.random != null){
                // note that cur.random is original, cur.random.next is copy. 
                // so we point copy's random to random's copy 
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // pass 3: get a deep copy from mixed list
        cur = head;
        RandomListNode newHead = cur.next;
        while(cur != null){
            RandomListNode node = cur.next;
            cur.next = node.next;
            if(node.next != null)
                node.next = node.next.next;
            cur = cur.next;
        }
        return newHead;
    }
}


/*
solution 2
复杂度
时间：O(n) 空间：O(n) 
思路：HaspMap
maintain a HashMap: <key, value> = <original, copy>
first pass: generate HashMap
second pass: copy random field

Note: 在copy的时候，并不是直接将newnode = cur，而需要新建一个有cur.label的值的node，
将它跟前一个节点连在一起。
*/ 

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(head, newHead);
        RandomListNode cur = head.next;
        RandomListNode pre = newHead;
        
        
        while(cur != null){
            //copy值，而不是直接等于那个点
            RandomListNode node = new RandomListNode(cur.label);
            map.put(cur, node);
            pre.next = node;
            pre = node;
            cur = cur.next;
        }
        
        cur = head;
        RandomListNode node = newHead;
        while(cur != null){
            node.random = map.get(cur.random);
            node = node.next;
            cur = cur.next;
        }
        
        return newHead;
    }
}