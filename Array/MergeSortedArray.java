/**
* Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
* 
* Note:
* You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

/*
复杂度
时间：O(n) 空间：O(1)
思路：三指针
maintain three pointer. iterating from end to start is because nums1[] space is used and it may overwrite some items in nums1[] that haven't been checked.  
*/

public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums1 == null || nums2 == null) return;
		int idx1 = m - 1;
		int idx2 = n - 1;
		int idx = n + m - 1;
		while (idx1 >= 0 && idx2 >= 0) {
			if (nums1[idx1] > nums2[idx2]) {
				nums1[idx--] = nums1[idx1--];
			} else {
				nums1[idx--] = nums2[idx2--];
			}
		}
		while (idx2 >= 0) {
			nums1[idx--] = nums2[idx2--];
		}
	}
}
