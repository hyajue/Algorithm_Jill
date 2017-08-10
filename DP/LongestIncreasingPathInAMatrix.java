/**
* Given an integer matrix, find the length of the longest increasing path.
* 
* From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
* 
* Example 1:
* 
* nums = [
*   [9,9,4],
*   [6,6,8],
*   [2,1,1]
* ]
* Return 4
* The longest increasing path is [1, 2, 6, 9].
* 
* Example 2:
* 
* nums = [
*   [3,4,5],
*   [3,2,6],
*   [2,2,1]
* ]
* Return 4
* The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

/*
solution 1:
记忆化搜索。
设cache[i][j]为当前点出发最大上升路径的值。初始设置为0，表示该点未知，需要更新。
再次碰到的时候只需要返回该值即可

DFS+DP - time complexity: O(mn)
*/

public class Solution {
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        //Calculate and memorize the longest distance for this cell, so we don't need to calculate it again in the future.
        int[][] cache = new int[row][col];
        int max = 1;
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int len = dfs(matrix, cache, i, j, row, col);
                max = Math.max(len, max);
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int[][] cache, int i, int j, int row, int col){
        //exit: when the cell has already been caculated.
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, cache, x, y, row, col);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}