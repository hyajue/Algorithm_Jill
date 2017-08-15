/**
* Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
* 
* get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
* put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
* 
* Follow up:
* Could you do both operations in O(1) time complexity?
* 
* Example:
* 
* LRUCache cache = new LRUCache( 2 /* capacity */ );
* 
* cache.put(1, 1);
* cache.put(2, 2);
* cache.get(1);       // returns 1
* cache.put(3, 3);    // evicts key 2
* cache.get(2);       // returns -1 (not found)
* cache.put(4, 4);    // evicts key 1
* cache.get(1);       // returns -1 (not found)
* cache.get(3);       // returns 3
* cache.get(4);       // returns 4
*/

/*
复杂度
时间 O(1) for both get and put 空间O(n)
思路: map + doubly linked list
get(key) -- O(1) 很明显用一个hashmap来实现O(1)的查询操作
哈希表无序->无法做到least recent updated item deletion->考虑用队列
队列无法做到移动非首位的元素->当移动任意元素时,需要考虑前驱和后续的连接->考虑用双向链表

//tail <-> 1 <-> 2 <-> 3 <->  head: remove 1
*/

public class LRUCache {
    int capacity;
    int size;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        map = new HashMap<Integer, ListNode>();
        //head和tail会最先被push出去
        head = new ListNode();
        tail = new ListNode();
        head.prev = tail;
        tail.next = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            ListNode cur = map.get(key);
            promote(cur);
            return cur.val;
        }else
            return -1;
    }
    
    public void put(int key, int value) {
        ListNode cur = map.get(key);
        if(cur != null){
            cur.val = value;
            promote(cur);
        }else{
            cur = new ListNode(key, value);
            size++;
            putToHead(cur);
            if(size > capacity){
                //如果加入节点后超出容量 则删除最后一个节点(最不常用节点)
                removeLast();
                size--;
            }
        }
        //不论是否是新加入还是更新原链表 都要更新map
        map.put(key, cur);
    }
    
    private void promote(ListNode cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        putToHead(cur);
    }
    
    private void putToHead(ListNode cur) {
        head.prev.next = cur;
        cur.prev = head.prev;
        head.prev = cur;
        cur.next = head;
    }
    
    private void removeLast() {
        ListNode last = tail.next;
        last.next.prev = tail;
        tail.next = last.next;
        //不仅要删除链表中的数据 也要删除相应的map的数据
        map.remove(last.key);
    }
    
    private class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;
        
        ListNode(int key, int val){
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
		ListNode() {
			this(0, 0);
		}
    }
}

//@Method 2: LinkedHashMap 
/*
AccessOrder: 如果为true，则按照访问顺序；如果为false，则按照插入顺序。
In the constructor, the third boolean parameter specifies the ordering mode. 
If we set it to true, it will be in access order. 

By overriding removeEldestEntry in this way, we do not need to take care of it ourselves.
It will automatically remove the least recent one when the size of map exceeds the specified capacity.
(https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-)
*/ 

import java.util.LinkedHashMap;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            @Override 
            protected boolean removeEldestEntry(Map.Entry eldest){
                return size() > CAPACITY;
            }
        };
    }
    
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        map.put(key, value);
    }
}  

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */