package TwoPointers;
/**
* Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
* 
* The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
* 
* You may assume that each input would have exactly one solution and you may not use the same element twice.
* 
* Input: numbers={2, 7, 11, 15}, target=9
* Output: index1=1, index2=2
*/

/*
复杂度
时间O(n) 空间O(1)
思路：
因为数组已经排好序了，用两个指针分别从头尾开始找就可以了。
如果头尾和大于target，说明尾太大，小于target说明头太小。
根据大小移动指针即可。
*/
public class TwoSumII {
	public int[] twoSum(int[] numbers, int target) {
        int[] res = {-1, -1};
        if(numbers == null || numbers.length == 0)
            return res;
        int left = 0;
        int right = numbers.length - 1;
        while(left < right){
            if(numbers[left] + numbers[right] == target)
                return new int[] {left + 1, right + 1};
            if(numbers[left] + numbers[right] < target){
                left++;
            }else{
                right--;
            }
        }
        return res;
    }
}
