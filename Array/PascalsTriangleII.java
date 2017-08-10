/**
* Given an index k, return the kth row of the Pascal's triangle.
* 
* For example, given k = 3,
* Return [1,3,3,1].
* 
* Note:
* Could you optimize your algorithm to use only O(k) extra space?
*/

/*
复杂度
时间:O(n) 空间：O(1)

思路：每次循环时，向第0位插入一个1.
从第一位开始直到第size - 1位，更新当前位置的值为res.get(i) + res.get(i+1)
*/

public class Solution { 
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if(rowIndex < 0) return res;
        
        for(int i = 0; i <= rowIndex; i++){
            res.add(0, 1);
            for(int j = 1; j < res.size() - 1; j++){
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }
}