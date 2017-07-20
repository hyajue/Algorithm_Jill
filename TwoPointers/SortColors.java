package TwoPointers;
/**
* Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
* 
* Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
* 
* Note:
* You are not suppose to use the library's sort function for this problem.
* 
* Follow up:
* A rather straight forward solution is a two-pass algorithm using counting sort.
* First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
* 
* Could you come up with an one-pass algorithm using only constant space?
*/
/*
复杂度
时间：O(n) 空间O(1)
思路：三路快排
Using 3-way quick sort to achieve one-pass algo
pick '1' as pivot:
[less than 1, ==1, bigger than 1] 
index counting sort is also a solution, which can be used in many other questions.
*/
public class Solution { 
    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int i = 0; //nums[0,..,i) == 0
        int j = 0; //nums[i,...,j) ==1
        int k = nums.length - 1; //
        int temp = 0;
        while(j <= k){
            if(nums[j] == 0){
                swap(i, j, nums); //相当于0和前面的1换位置,无需再读换完的1了。所以j也要++
                i++;
                j++; 
            }else if(nums[j] == 2){
                swap(j, k, nums);
                k--;
            }else
                j++;
        }
    }
    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//2 pass. Counting sort
public class SortColors {
	public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int i = 0, j = nums.length - 1;
        for(int k = 0; k < nums.length; k++){
            if(nums[k] == 0)
                i++;
            else if(nums[k] == 2)
                j--;
        }
        for(int k = 0; k < i; k++)
            nums[k] = 0;
        for(int k = i; k <= j; k++)
            nums[k] = 1;
        for(int k = j + 1; k < nums.length; k++)
            nums[k] = 2;
    }
}
