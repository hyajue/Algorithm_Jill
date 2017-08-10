package BinarySearch;
/**
* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
* 
* Integers in each row are sorted from left to right.
* The first integer of each row is greater than the last integer of the previous row.
* For example,
* 
* Consider the following matrix:
* 
* [
*   [1,   3,  5,  7],
*   [10, 11, 16, 20],
*   [23, 30, 34, 50]
* ]
* Given target = 3, return true.
*/

/*
复杂度
时间O(logn) 空间O(1)
思路：二分查找
先从第一列二分查找目标值可能在哪里行 然后在可能的那一行再进行二分查找
*/
public class SearchA2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int up = 0, down = row - 1;
        
        while(up <= down){
            int mid = (down - up) / 2 + up;
            if(matrix[mid][0] == target) return true;
            if(matrix[mid][0] > target)
                down = mid - 1;
            else
                up = mid + 1;
        }
        //sanity check, make sure that the target is not < matrix[0][0]
        row = down;
        if(row < 0) return false;
		
        int left = 0, right = col - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(matrix[row][mid] == target) return true;
            if(matrix[row][mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        
        return false;    
    }
}
