package TwoPointers;
/**
* Follow up for "Remove Duplicates":
* What if duplicates are allowed at most twice?
* 
* For example,
* Given sorted array nums = [1,1,1,2,2,3],
* 
* Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
*/

/*
复杂度
时间O:(n) 空间O(1)
思路：
maintain a counter: 
	when current item == last item, counter++
	skip current item if counter >= 3: item already repeats two times 
	reset counter = 1 if current item is different than last one
*/

public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] nums) {
        if(nums.length < 3) return nums.length;
        int count = 1;
        int idx = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i-1]){
                count++;
                if(count <= 2)
                   nums[++idx] = nums[i];
            }else{
                nums[++idx] = nums[i];
                count = 1;
            }
        }
        return idx + 1;
    }
}
