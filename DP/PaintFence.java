package DP;
/**
There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts such that no more than two adjacent fence posts have the same color. Return the total number of ways you can paint the fence.
 */
/*
那么我们首先来分析一下，如果n=0的话，说明没有需要刷的部分，直接返回0即可
如果n为1的话，那么有几种颜色，就有几种刷法，所以应该返回k
如果n为2，要不然俩柱子颜色相同k, 要不然俩柱子颜色不同k * k-1
n>2的情况：
1)如果当前柱子跟前一个柱子颜色不同的话，则有 (k-1)*前一个柱子的方法数
2)如果当前柱子跟前一个柱子颜色相同的话，那么他跟n-2的柱子比，有k-1个方法，所以k-1 * dp[n-2]
两种情况相加
dp[n] = (k-1)*dp[n-1] + (k-1)*dp[n-2]
 */
//Time: O(n), Space: O(1)
public class PaintFence {
	public int numWays(int n, int k) {
		if(n == 0 || k == 0) return 0;
		if(n == 1) return k;
		if(n == 2) return k * (k-1) + k;
		
		int w1 = k;
		int w2 = k * (k - 1) + k;
		int w3 = 0;
		for(int i = 3; i <= n; i++) {
			w3 = (k - 1) * (w1 + w2);
			w1 = w2;
			w2 = w3;
		}
		return w3;
	}
}
// Space: O(n)
public class PaintFence {
	public int numWays(int n, int k) {
		if(n == 0 || k == 0) return 0;
		if(n == 1) return k;
		if(n == 2) return k * (k-1) + k;
		int[] dp = new int[n+1];
		dp[1] = k;
		dp[2] = k * (k - 1) + k;
		for(int i = 3; i <= n; i++) {
			dp[i] = (k - 1) * (dp[i-1] + dp[i-2]);
		}
		return dp[n];
	}
}
