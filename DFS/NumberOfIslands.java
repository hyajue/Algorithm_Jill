package DFS;
/**
* Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
* 
* Example 1:
* 
* 11110
* 11010
* 11000
* 00000
* Answer: 1
* 
* Example 2:
* 
* 11000
* 11000
* 00100
* 00011
* Answer: 3
*/

/*
模型：迷宫问题 典型递归求解
每遇到'1'后, 把当前位置设为visited, 然后向四个方向递归搜索. 
*/
//也可以遇到'1'以后，将它设为'0'，防止再次访问。 这样可以节省一个二维visited空间
public class NumberOfIslands {
	public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    res += 1;
                    search(grid, visited, i, j);
                }
            }
        }
        return res;
    }
    private void search(char[][] grid, boolean[][] visited, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] != '1') return;
        visited[i][j] = true;
        search(grid, visited, i - 1, j);
        search(grid, visited, i, j - 1);
        search(grid, visited, i + 1, j);
        search(grid, visited, i, j + 1);
    }
}
