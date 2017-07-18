package BinarySearch;
/**
* Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
* 
* Note that it is the kth smallest element in the sorted order, not the kth distinct element.
* 
* Example:
* 
* matrix = [
*    [ 1,  5,  9],
*    [10, 11, 13],
*    [12, 13, 15]
* ],
* k = 8,
* 
* return 13.
* Note: 
* You may assume k is always valid, 1 ≤ k ≤ n2.
*/

/*
solution 1
复杂度
时间：O((n*m)*logk) 空间：O(k)
思路：最大堆
用maxheap把全部元素放进 DESC heap里面，同时如果heap的size大于k就弹出，保持heap的size为k，
最后root的元素就是第k小
*/
public class KthSmallestElementInASortedMatrix {
	  public int kthSmallest(int[][] matrix, int k) {
	    // heap
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, (a, b) -> b - a);
	    for(int i = 0; i < matrix.length; i++) {
	      for(int j = 0; j < matrix[0].length; j++) {
	        maxHeap.offer(matrix[i][j]);
	        if(maxHeap.size() > k) maxHeap.poll();
	      }
	    }
	    return maxHeap.poll();
	  }
}
/*
solution 2
复杂度
时间：O(nlogm) while m = max - min. 空间：O(1)
思路：二分
*/
/*
 * 我们并不用对每一行都做二分搜索法，我们注意到每列也是有序的，
 * 我们可以利用这个性质，从数组的左下角开始查找:
 * 如果比目标值小，我们就向右移一位，而且我们知道当前列的当前位置的上面所有的数字都小于目标值，那么cnt += i+1，
 * 反之则向上移一位，这样我们也能算出cnt的值
 */

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        
        while(left <= right){
            int mid = (right - left) / 2 + left;
            int num = count(matrix, mid);            
            if(num < k)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
    private int count(int[][] matrix, int target){
        int n = matrix.length;
        int i = n - 1;
        int j = 0;
        int res = 0;
        while(i >=0 && j < n){
            if(matrix[i][j] <= target){
                res += i + 1;
                j++;
            }else{
                i--;
            }
        }
        return res;
    }
}