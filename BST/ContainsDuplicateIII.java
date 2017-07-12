package BST;
/**
 * Given an array of integers, 
 * find out whether there are two distinct indices i and j in the array 
 * such that the absolute difference between nums[i] and nums[j] is at most t 
 * and the absolute difference between i and j is at most k.
 */
/*
 * @Method1 ： TreeSet
 * 思路：sliding window来维护两元素距离最多为k, TreeSet来存元素
 * TreeSet数据结构（Java）使用红黑树实现，是平衡二叉树的一种。

	该数据结构支持如下操作：
	1. floor()方法返set中≤给定元素的最大元素；如果不存在这样的元素，则返回 null。
	2. ceiling()方法返回set中≥给定元素的最小元素；如果不存在这样的元素，则返回 null。

Maintaining the tree of size k will result in time complexity O(NlgK)
Simple Query (ceiling, floor, etc) in time complexity O(logN)
 */
public class ContainsDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length < 2 || k < 1 || t < 0) return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if((set.floor(n) != null && n <= set.floor(n) + t) || (set.ceiling(n) != null && n + t >= set.ceiling(n)))
               return true;
            set.add(n);
            if(i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}


/*思路：滑动窗口+ bucket sort
Time: O(n), Space: O(n)


想法是，因为两元素最多不能相差超过t,所以设置t+1个桶来存放数据。
如果： | nums[i] - nums[j] | <= t   式a
等价： | nums[i] / t - nums[j] / t | <= 1   式b
推出： | floor(nums[i] / t) - floor(nums[j] / t) | <= 1   式c
等价： floor(nums[j] / t) ∈ {floor(nums[i] / t) - 1, floor(nums[i] / t), floor(nums[i] / t) + 1} 式d

Suppose we have consecutive buckets covering the range of nums with each bucket a width of (t+1). 
If there are two item with difference <= t, one of the two will happen:
(1) the two in the same bucket
(2) the two in neighbor buckets

一个难点就是，在整数数组里可能有负数。num/t shrinks everything to 0.
这样在java（C和C++）中，num/t+1会使得[-t-1,0]的所有的数都映射到0号桶。
对此，将每一个数组元素的起点调整为Integer.MIN_VALUE,即每个元素改为num-Integer.MIN_VALUE,桶号都为正数
*/
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if(nums.length < 2 || k < 1 || t < 0) return false;
    Map<Long, Long> map = new HashMap<Long, Long>();
    for(int i = 0; i < nums.length; i++){
        long curNum = (long) nums[i] - Integer.MIN_VALUE; //
        long bucket = curNum / ((long) t + 1);
        if(map.containsKey(bucket) 
           || (map.containsKey(bucket-1) && curNum - map.get(bucket-1) <= t) 
           || (map.containsKey(bucket+1)) && map.get(bucket+1) - curNum <= t)
            return true;
        map.put(bucket, curNum);
        if(i >= k){
            long lastBucket = ((long) nums[i-k] - Integer.MIN_VALUE) / ((long) t + 1);
            map.remove(lastBucket);
        }
    }
    return false;
}