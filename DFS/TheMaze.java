package DFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.*;

/**
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.
*/

/**
Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
//BFS:
public class TheMaze {
	class Point{
		int x, y;
		public Point(int _x, int _y) {
			x = _x;
			y = _y;
		}
	}
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		if(maze == null || start == null || destination == null)
			return false;
		if(maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1)
			return false;
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(start[0], start[1]));
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		visited[strat[0]][start[1]] = true;
		int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int i = p.x;
			int j = p.y;
			for(int k = 0; k < dir.length; k++) {
				int ii = i, jj = j;
				while(ii >= 0 && ii < maze.length && jj >= 0 && jj < maze[0].length && maze[ii][jj] != 1) {
					ii += dir[k][0];
					jj += dir[k][1];
				}
				ii -= dir[k][0];
				jj -= dir[k][1];
				if(visited[ii][jj]) continue;
				visited[ii][jj] = true;
				if(ii == destination[0] && jj == destination[1])
					return true;
				queue.offer(new Point(ii, jj));
			}
		}
		return false;
	}
}
//DFS: 
public class TheMaze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		if(maze == null || start == null || destination == null)
			return false;
		if(maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1)
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return dfs(maze, visited, start, destination);
	}
	//Method 3: BiPredict, lambda
	private boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
		if(Arrays.equals(start, destination)) return true;
		int i = start[0];
		int j = start[1];
		if(visited[i][j])
			return false;
		visited[i][j] = true;
		BiPredicate<Integer, Integer> roll = (rowInc, colInc) -> {
			int ii = i, jj = j;
			while(ii >= 0 && ii <= maze.length - 1 && jj >= 0 && jj <= maze[0].length - 1 && maze[ii][jj] != 1) {
				ii += rowInc;
				jj += colInc;
			}
			return dfs(maze, visited, new int[] {ii - rowInc, jj - colInc}, destination)
		};
		if(roll.test(1, 0)) return true;
		if(roll.test(-1, 0)) return true;
		if(roll.test(0, 1)) return true;
		if(roll.test(0, -1)) return true;
		
		return false;
	}
	//Method 1: general
	private boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
		if(Arrays.equals(start, destination))
			return true;
		int i = start[0];
		int j = start[1];
		//处理上一步遗留的边界问题
		if(i < 0 || i > maze.length || j < 0 || j > maze[0].length || visited[i][j])
			return false;
		visited[i][j] = true;
		int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		for(int k = 0; k < directions.length; x++) {
			int ii = i, jj = j;
			while(ii >= 0 || ii <= maze.length - 1 && jj >= 0 && jj <= maze[0].length - 1 && maze[ii][jj] != 1) {
				ii += directions[k][0];
				jj += directions[k][1];
			}
			if(dfs(maze, visited, new int[] {ii - directions[k][0], jj - directions[k][1]}, destination)) return true;
		}
		return false;
	}
	//Method 2: straight-forward
	private boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
		if(Arrays.equals(start, destination))
			return true;
		
		int i = start[0];
		int j = start[1];
		if(visited[i][j]) return false;
		visited[i][j] = true;
		
		// left
		if(j > 0 && maze[i][j-1] != 1) {
			int k = j - 1;
			//这样可以保证k的位置就是最后一个有效的位置
			while(k > 0 && maze[i][k-1] != 1)
				k--;
			if(dfs(maze, visited, new int[] {i, k}, destination))
				return true;
		}
		// right
		if(j < maze[0].length - 1 && maze[i][j+1] != 1) {
			int k = j + 1;
			while(k < maze[0].length - 1 && maze[i][k+1] != 1)
				k++;
			if(dfs(maze, visited, new int[] {i, k}, destination))
				return true;
		}
		// up
		if(i > 0 && maze[i-1][j] != 1) {
			int k = i - 1;
			while(k > 0 && maze[k-1][j] != 1)
				k++;
			if(dfs(maze, visited, new int[] {k, j}, destination))
				return true;
		}
		//down
		if(i < maze.length - 1 && maze[i+1][j]) {
			int k = i + 1;
			while(k < maze.length - 1 && maze[i+1][j])
				k++;
			if(dfs(maze, visited, new int[] {k, j}, destination))
				return true;
		}
		return false;
	}
}
