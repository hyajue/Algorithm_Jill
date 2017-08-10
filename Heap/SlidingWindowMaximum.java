/**
* Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
* 
* For example,
* Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
* 
* Window position                Max
* ---------------               -----
* [1  3  -1] -3  5  3  6  7       3
*  1 [3  -1  -3] 5  3  6  7       3
*  1  3 [-1  -3  5] 3  6  7       5
*  1  3  -1 [-3  5  3] 6  7       5
*  1  3  -1  -3 [5  3  6] 7       6
*  1  3  -1  -3  5 [3  6  7]      7
* Therefore, return the max sliding window as [3,3,5,5,6,7].
* 
* Note: 
* You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
* 
* Follow up:
* Could you solve it in linear time?
*/
/*
思路: Deque
Time: O(n) Space: O(k)
当我们遇到新的数时，将新的数和双向队列的末尾比较，如果末尾比新数小，则把末尾扔掉，
直到该队列的末尾比新数大或者队列为空的时候才住手。
这样，我们可以保证队列里的元素是从头到尾降序的，由于队列里只有窗口内的数，所以他们其实就是窗口内第一大，第二大，第三大...的数。

保持队列里只有窗口内数的方法和上个解法一样，也是每来一个新的把窗口最左边的扔掉，然后把新的加进去。然而由于我们在加新数的时候，已经把很多没用的数给扔了，这样队列头部的数并不一定是窗口最左边的数。

我们队列中存的是那个数在原数组中的下标，这样我们既可以直到这个数的值，也可以知道该数是不是窗口最左边的数。
*/
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k <= 0) return new int[]{};
        int[] res = new int[nums.length - k + 1];
        //双向队列，可以用linkedList声明。
        Deque<Integer> dq = new LinkedList<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            //只要dq不为空，从前抛出不在窗口内的
            if(!dq.isEmpty() && dq.peek() == i - k)
                dq.poll();
            //只要dq不为空，不断从后抛出比nums[i]小的。保证队列是desc的
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();
            //存入新数
            dq.offer(i);
            //队列头部就是该窗口内第一大的
            if(i + 1 >= k)
                res[i - k + 1] = nums[dq.peek()];
        }
        return res;
    }
}


/*
复杂度
时间:O(nlogK) 空间:O(k)
思路：heap
维护一个大小为k的最大堆,依次将原数组数据入堆,每次堆顶的元素即为窗口中的最大值.
注意当k == nums.length的情况：当pq.sise() == k时就要更新结果数组
*/

public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) return new int[]{};
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, (a, b) -> (b - a));    
		int[] res = new int[nums.length-k+1];
		for (int i = 0; i < nums.length; i++) {
			pq.offer(nums[i]);
			if (pq.size() == k) {
				res[i+1-k] = pq.peek();
				pq.remove(nums[i+1-k]);
			}
		}
		return res;
  }
}