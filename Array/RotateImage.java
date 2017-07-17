package Array;
/**
* You are given an n x n 2D matrix representing an image.
* 
* Rotate the image by 90 degrees (clockwise).
* 
* Follow up:
* Could you do this in-place?
*/

/*
solution 1
复杂度
时间O(n^2) 空间O(1)
思路：转置镜像法
step 1: transpose the matrix: matrix[i][j] <-> matrix[j][i](转置)
step 2: flip each row of transposed matrix(镜像)
*/
public class RotateImage {
	public void rotate(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int row = matrix.length;
        int col = matrix[0].length;
        
        for(int i = 0;  i < row; i++){
            //!!!!!!!!!!!Note: you only need to do half of the matrix!
            for(int j = i + 1; j < col; j++)
                //transpose
                swap(matrix, i, j);
            //flip by row
            flip(matrix, i);
        }
    }
    private void swap(int[][] matrix, int i, int j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }
    private void flip(int[][] matrix, int row){
        int l = 0, r = matrix[0].length - 1;
        while(l < r){
            int temp = matrix[row][l];
            matrix[row][l] = matrix[row][r];
            matrix[row][r] = temp;
            l++; r--;
        }
    }
}

/*Solution 2:
 * 
