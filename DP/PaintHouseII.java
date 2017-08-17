package DP;
/**
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 */
/*
 Time: O(m*n) Space: O(1)
 这道题让我们用k种颜色，这道题不能用之前那题的解法，会TLE。
这题的解法的思路还是用DP，但是在找不同颜色的最小值不是遍历所有不同颜色，而是用min1和min2来记录之前房子的最小和第二小的花费的颜色，
如果当前房子颜色和min1相同，那么我们用min2对应的值计算，反之我们用min1对应的值，这种解法实际上也包含了求次小值的方法 
 */
public class PaintHouseII {
	public int minCostII(int[][] costs) {
		if(costs == null || costs.length == 0)
			return 0;
		// min1 is the index of the 1st-smallest cost till previous house
	    // min2 is the index of the 2nd-smallest cost till previous house
		int min1 = -1;
		int min2 = -1;
		//一遍循环，一次性解决
		for(int i = 0; i < costs.length; i++) {
			//last1, last2来记录上层循环的最小位置
			int last1 = min1;
			int last2 = min2;
			//重置min1, min2
			min1 = -1;
			min2 = -1;
			//一遍找到min1和min2，同时更新costs[i][...]
			for(int j = 0; j < costs[0].length; j++) {
				if(j == last1) {
					costs[i][j] += last2 < 0 ? 0 : costs[i-1][last2]; 
				}else{  //不存在last1的时候，即i=0的时候, costs[0][j]就是它自己
					costs[i][j] += last1 < 0 ? 0 : costs[i-1][last1];
				}
				if(min1 < 0 || costs[i][j] < costs[i][min1]) {
					//在有multiple按顺序的值的时候，做法是依次向后类推
					min2 = min1;
					min1 = j;
				}else if(min2 < 0 || costs[i][j] < costs[i][min2]) {
					min2 = j;
				}
			}
		}
		return costs[costs.length-1][min1];
	}
}
