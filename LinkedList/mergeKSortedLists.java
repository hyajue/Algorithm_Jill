package LinkedList;
/*
Merge k sorted linked lists and return it as one sorted list. 
Analyze and describe its complexity.
*/
//Method 1: PriorityQueue Olog(nlogn)
public ListNode mergeKLists(List<ListNode> lists) { // running time O(nlogn)
    if(lists==null || lists.size()==0) return null;
    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
        @Override
        public int compare(ListNode l1, ListNode l2){
            return l1.val - l2.val;
        }
    });
    for(ListNode list : lists){
        if(list != null)
            queue.offer(list);
    }
    ListNode dummy  = new ListNode(-1);
    ListNode cur = dummy;
    while(! queue.isEmpty()){
        cur.next = queue.poll();
        cur = cur.next;
        if(cur.next != null){
            queue.offer(cur.next);
        }
    }
    return dummy.next;
}

//@Method 2: Recursive.
public class mergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }
    public ListNode mergeKLists(ListNode[] lists, int start, int end){
        if(start == end){
           return lists[start]; 
        }else if(start < end){
            int mid = start + (end - start) / 2;
            ListNode left = mergeKLists(lists, start, mid);
            ListNode right = mergeKLists(lists, mid+1, end);
            return mergeTwo(left, right);
        }else
            return null;
    }
    public ListNode mergeTwo(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwo(l1.next, l2);
            return l1;
        }else{
            l2.next =  mergeTwo(l1, l2.next);
            return l2;
        }
    }
}
