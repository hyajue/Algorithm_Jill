package Array;
/**
* Rotate an array of n elements to the right by k steps.
* 
* For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
* 
* Note:
* Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
*/
//@Method2: 三次反转
/*
复杂度
时间 O(N) 空间 O(1)
思路
通过三次反转，我们可以很巧妙的实现旋转数组。首先我们将整个数组反转，然后将前k个数字反转，然后再将后面剩下的数字反转，就得到目标数组了。
注意
反转数组最简单的方法是交换元素，而交换元素有至少三种方法（临时变量，相加相减，异或）
k可能大于数组长度，旋转不止一次，所以我们要先对k取余
follow up: 
如果是向左旋转k位而不是向右呢？
左旋时，我们是将其后k个单独反转，然后前面的单独反转。
*/

public class Solution {    
    public void rotate(int[] nums, int k) {
        if(nums.length < 2 || k < 1) return;
        k %= nums.length;
        reverse(nums, 0 , nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int l, int r){
        while(l < r){
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    private void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
// @Method1： 简单直接
//Time O(n)   Space O(k % nums.length)
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if(nums.length < 2 || k < 1) return;
        //k could be larger than nums.length
        int step = k % nums.length;
        int[] temp = new int[step];
        for(int i = 0; i < step; i++)
            temp[i] = nums[nums.length - step + i];
        for(int i = nums.length - step - 1; i >= 0; i--)
            nums[i + step] = nums[i];
        for(int i = 0; i < step; i++)
            nums[i] = temp[i]; 
    }
}
