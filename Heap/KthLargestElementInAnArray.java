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
//QuickSort
/*
The basic idea is to use Quick Select algorithm to partition the array with pivot:

Put numbers < pivot to pivot's left
Put numbers > pivot to pivot's right
Then

if indexOfPivot == k, return A[k]
else if indexOfPivot < k, keep checking left part to pivot
else if indexOfPivot > k, keep checking right part to pivot
*/
/*
Time complexity = O(n) Space: O(1)
Discard half each time: n+(n/2)+(n/4)..1 = n + (n-1) = O(2n-1) = O(n), because n/2+n/4+n/8+..1=n-1.
 */
public class Solution {
    //QuickSort
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0) return -1;
        // nums.length - k个数 要比他小
        return quickSort(0, nums.length - 1, nums, nums.length - k); 
    }

    private int quickSort(int l, int r, int[] nums, int k){
        if(l > r) return -1;
        int pivot = nums[r]; // Take A[end] as the pivot
        int ptr = l;
        for(int i = l; i < r; i++){
            if(nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(ptr++, i, nums);
        }
        swap(ptr, r, nums); // Finally, swap A[end] with A[left]
        
        if(ptr == k) return nums[ptr];
        else if(ptr < k) // Check right part
            return quickSort(ptr + 1, r, nums, k);
        else // Check left part
            return quickSort(l, ptr - 1, nums, k);
    }
    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
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
