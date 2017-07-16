package DFS;
/**
* Given a 2D board and a word, find if the word exists in the grid.
* 
* The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
* 
* For example,
* Given board =
* 
* [
*   ['A','B','C','E'],
*   ['S','F','C','S'],
*   ['A','D','E','E']
* ]
* word = "ABCCED", -> returns true,
* word = "SEE", -> returns true,
* word = "ABCB", -> returns false.
*/
/*
复杂度
时间 O(m^2*n^2) 空间(n*m)
思路： 深搜
从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串
注意每次从一个元素出发时要重置访问标记（虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符可以复用）
follow up: what if char can be used mutiple times? 
*/
public class WordSearch {
	public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0) return true;
        if(board.length == 0 || board[0].length == 0) return false;
        int row = board.length;
        int column = board[0].length;
        boolean[][] visited = new boolean[row][column];
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, visited, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, boolean[][] visited, String word, int i, int j, int idx){
        if(idx == word.length())
            return true;
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j] || word.charAt(idx) != board[i][j])
            return false;
        visited[i][j] = true;
        boolean res = (dfs(board, visited, word, i + 1, j, idx + 1)
                      || dfs(board, visited, word, i - 1, j, idx + 1)
                      || dfs(board, visited, word, i, j + 1, idx + 1)
                      || dfs(board, visited, word, i, j - 1, idx + 1));
        visited[i][j] = false;
        return res;
    }
}
