/*
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
*/

/**
 * dp[i][j] = a[i] == b[j] ? dp[i + 1][j + 1] : 0;
 * dp[i][j] : max lenth of common subarray start at a[i] & b[j];
 */

class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] A, int[] B) {
        if(A.length == 0 || B.length == 0) return 0;
        int m = A.length;
        int n = B.length;
        int max = 0;
        int[][] dp = new int[m+1][n+1];
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                max = Math.max(max, dp[i][j] = A[i] == B[j] ? 1 + dp[i+1][j+1] : 0);
            }
        }
        return max;
    }
}