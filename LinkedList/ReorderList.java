package LinkedList;
/*
复杂度
时间 O(n) 空间O(1)
思路：双指针+翻转+合并+排序
主函数reorderList()：
对于长度为0或1的链表，返回；找到中点mid；分割链表并翻转后半段为tail；与前半段head合并
找中点findMid()：
快慢指针，当fast == null || fast.next == null时，返回慢指针
翻转reverse()：
建立空链表结点pre，先存head.next为temp，令head指向pre，令pre等于head，再令temp为head。
这样翻转就会令链表的首元素head移动到尾部，并让pre移动到所有已翻转结点的头部。
当head移动到最后一个元素null，pre正好移动到整个链表的头部。返回pre，翻转完毕。
合并merge()：
//Method 1
建立新链表结点dummy，复制到新链表结点cur。用index判断当前结点是奇数还是偶数。
偶数则加入n1的结点，n1后移；否则加入n2的结点，n2后移。
每加入一个结点后，cur后移。最后若n1或n2有剩余结点，则加入cur.next。返回dummy.next。
//Method 2
l1的长度 >= l2。所以只要做一个循环，l2不为null，将l1截断，下一个连l2，依次后移
*/
public class ReorderList {
	public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode mid = findMid(head);
        ListNode node2 = reverse(mid.next);
        mid.next = null;
        merge(head, node2);
    }
    private ListNode findMid(ListNode head){ 
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
    //@method 2
    private void merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        dummy.next = l1;
        while(l2 != null){
            ListNode next = l1.next;
            ListNode new2 = l2.next;
            l1.next = l2;
            l2.next = next;
            l2 = new2;
            l1 = next;
        }
    }
}
//Method 1
private void merge(ListNode l1, ListNode l2){
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    int idx = 0;
    while(l1 != null && l2 != null){
        if(idx % 2 == 0){
            cur.next = l1;
            l1 = l1.next;
        }else{
            cur.next = l2;
            l2 = l2.next;
        }
        idx++;
        cur = cur.next;
    }
    if(l1 != null)
        cur.next = l1;
    if(l2 != null)
        cur.next = l2;
}
