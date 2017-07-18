package Heap;

/**
* Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
* 
* For example,
* Given [3,2,1,5,6,4] and k = 2, return 5.
* 
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
复杂度
时间：O(nlogK) 空间：O(K)
思路:heap
维护一个最小堆,堆中的元素大约k时,就出堆.当所有元素都过一遍堆时,堆顶元素即为第K大元素
*/
public class KthLargestElementInAnArray {
	public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0) return -1;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k + 1, (a, b) -> (a - b));
        for(int i = 0; i < nums.length; i++){
            queue.offer(nums[i]);
            if(queue.size() > k)
                queue.poll();
        }
        return queue.poll();
    }
}
