/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
* 
* Example 1:
* Input: [7, 1, 5, 3, 6, 4]
* Output: 5
* 
* max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
* Example 2:
* Input: [7, 6, 4, 3, 1]
* Output: 0
* 
* In this case, no transaction is done, i.e. max profit = 0.
*/

/*
Kadane's Algorithm
Time: O(n) Space: O(1)
想法跟max subarray problem一样

if the interviewer twists the question slightly by giving the difference array of prices, Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.

Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
*/

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int global = 0;
        int local = 0;
        for(int i = 1; i < prices.length; i++){
            local = Math.max(0, local + prices[i] - prices[i-1]);
            global = Math.max(global, local);
        }
        return global;
    }
}

/*
solution 2: 
复杂度：
时间O(n), 空间O(1)
整体-局部最优法
维护一个在i之前的最小值min，如果prices[i]比他小，则更新min，
否则就比较prices[i] - min和max的大小
*/

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int localMax = 0;
	    int globalMax = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min)
                min = prices[i];
            else {
                localMax = prices[i] - min;
                globalMax = Math.max(globalMax, localMax);
            }
        }
        return globalMax;
    }
}