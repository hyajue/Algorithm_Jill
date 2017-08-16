/**
* Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
* 
* 
* Example 1:
* 
* Input: k = 3, n = 7
* 
* Output:
* 
* [[1,2,4]]
* 
* Example 2:
* 
* Input: k = 3, n = 9
* 
* Output:
* 
* [[1,2,6], [1,3,5], [2,3,4]]
*/

/*
复杂度
时间 O(9!) 空间 O(9) 递归栈空间
思路
II的简化版，设想一个[1,2,3,4,5,6,7,8,9]的数组，同样一个元素只能取一次，但是已经预先确定没有重复了。所以可以省去跳过重复元素的部分。
两个无法得到正解的情况是：
在k为0，而target不为0时，当然已经无法得到正解，return。
在k不为0而target却已经小于等于0的情况下，此时仍要加入其它数以令k=0，而要加入的数都是1到9的正整数，所以已无法满足令target为0的条件，return。
*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0 || n <= 0) return res;
        backtrack(k, n, 1, res, new ArrayList<Integer>());
        return res;
    }
    private void backtrack(int k, int target, int i, List<List<Integer>> res, List<Integer> temp) {
        if(target < 0 || k < 0)
            return;
        if(target == 0 && k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        
        for(int j = i; j <= 9; j++) {
            temp.add(j);
            backtrack(k - 1, target - j, j + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}

