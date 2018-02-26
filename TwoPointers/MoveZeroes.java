/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
**/

class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if(nums.length == 0 || nums == null) return;
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while(j < nums.length)
            nums[j++] = 0;
    }
}


// Method 2:
int pos = 0;
while(int num : nums) {
	if(num != 0) nums[pos++] = num;
}
while(pos < nums.length) {
	nums[pos++] = 0;
}

//Method 3:
//1 run, and all solved

for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[j];
                nums[j++] = nums[i];
                nums[i] = temp;
            }
        }