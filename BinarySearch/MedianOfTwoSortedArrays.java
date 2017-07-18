package BinarySearch;
/**
* There are two sorted arrays nums1 and nums2 of size m and n respectively.
* 
* Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
* 
* Example 1:
* nums1 = [1, 3]
* nums2 = [2]
* 
* The median is 2.0
* Example 2:
* nums1 = [1, 2]
* nums2 = [3, 4]
* 
* The median is (2 + 3)/2 = 2.5
*/
/*
复杂度
时间:O(log(n+m)) 空间:O(1)
思路:分治
要求O(log(m+n))时间复杂度，首先想分治法。这类问题可以总结为"Top K问题"
对于这两个数组，设我们的函数是find(A, B, K),
首先，分别取A[K/2-1] 和B[K - (K)/2 -1]比较(记K-K/2为K')，分如下三种情况：

1.A[K/2-1] < B[K'-1], 则A[0...(K)/2-1]的所有值都可以忽略了，因为：
这表示A[0]到A[k/2-1]的元素都在A和B合并之后的前k小的元素中。
换句话说，A[k/2-1]不可能大于两数组合并之后的第k小值，所以我们可以将其抛弃。

假设不能忽略，即A∪B[K-1]就在A[0...K/2-1]里面，取最大值A[K/2-1]为A∪B[K-1]。
由此则B中必定有K'个元素小于A[K/2-1]，而又因为A[K/2-1] < B[K'-1]，故假设不成立。
2. A[K/2-1] > B[K'-1]，同理B[0...K'-1]的值均可忽略了。
3. A[K/2-1] = B[K'-1]，这就是要找的值了，终止。
假设发生了情况1(情况2也类似)，则我们可以知道：
A∪B[K-1]一定在A(K/2-1...len(A))或B中了，因为步骤1已经去掉了的(K-1)/2个元素（注：它们不是最小元素的，但的确小于A∪B[K-1]），于是现在应该是寻找下标大小是第K'的元素了。
设A = A[(K/2)..len(A)), B = B, K = K - K/2
递归到第一步，当其中一个数组长度为0，则另一个取下标K-1即可;另一种情况是，当K为1时，取min(A[0], B[0])
*/
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 0) return 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        //平均数。奇数偶数通用。注意这里指的是第x个数[1, 2,.. ，而不是位置[0，1，2，..
        return (double) (findK(nums1, 0, nums2, 0, (len + 1) / 2) + findK(nums1, 0, nums2, 0, (len + 2) / 2)) / 2;
    }
    private int findK(int[] nums1, int s1, int[] nums2, int s2, int k){
        int len1 = nums1.length - s1;
        int len2 = nums2.length - s2;
        //to ensure len1<=len2 always
        if(len1 > len2) 
            return findK(nums2, s2, nums1, s1, k);
        if(len1 == 0)
            return nums2[s2 + k - 1];
        if(k == 1)
            return Math.min(nums1[s1], nums2[s2]);
        
        int p1 = Math.min(len1, k / 2);
        int p2 = k - p1;
        if(nums1[s1 + p1 - 1] < nums2[s2 + p2 - 1])
            return findK(nums1, s1 + p1, nums2, s2, k - p1);
        else if(nums1[s1 + p1 - 1] > nums2[s2 + p2 - 1])
            return findK(nums1, s1, nums2, s2 + p2, k - p2);
        else return nums1[s1 + p1 - 1];
    }
}
