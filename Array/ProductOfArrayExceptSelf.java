/**
* Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
* 
* Solve it without division and in O(n).
* 
* For example, given [1,2,3,4], return [24,12,8,6].
* 
* Follow up:
* Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/

/*
T(n) = O(n); S(n) = O(1)  
first pass to calculate res[i]: the product from [0->i)
second pass to calculate res[i]:the product from (i->nums.length-1] 
maintain one array res[] and two vairiables to store product left/right 
*/



public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++){
            res[i] = res[i-1] * nums[i-1];
        }
        
        int right = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            right *= nums[i+1];
            res[i] = res[i] * right;
        }
        return res;
    }
}