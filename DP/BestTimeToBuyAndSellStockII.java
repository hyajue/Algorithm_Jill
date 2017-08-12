/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
复杂度:
时间:O(n) 空间O(1)
思路：贪心法
允许多次买卖 -> 尽可能低买高卖
*/
public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1])
                res += prices[i] - prices[i-1];
        }
        return res;
    }
}

/*
Peak Valley法
时间:O(n) 空间O(1)

思路：先找到低谷，再找到峰值
3 2 1 2 6 5 2 4 3
    v   p   v p

The key point is we need to consider every peak immediately following a valley to maximize the profit. In case we skip one of the peaks (trying to obtain more profit), we will end up losing the profit over one of the transactions leading to an overall lesser profit.
*/

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int i = 0;
        int res = 0;
        while(i < prices.length - 1){
            while(i < prices.length - 1 && prices[i] >= prices[i+1])
                i++;
            int valley = prices[i];
            while(i < prices.length - 1 && prices[i] <= prices[i+1])
                i++;
            int peak = prices[i];
            res += peak - valley;
        }
        return res;
    }
}

