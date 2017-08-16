/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete at most two transactions.
* 
* Note:
* You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
solution 1: 
复杂度：
时间O(n) 空间O(n)
思路：
维护两个数组，left和right，left[i]表示从0到i天的最大获益，right[i]表示从i到最后一天的最大获益。
求left的方法等同于解BestTimeToBuyAndSellStock一的方法，
求right的方法是求left的对称解法：
求left时，记录前i天的最低价minPrice与最大获益max，求left[i]：考虑要在第i天卖出，那么买进的时间必然是在0到i之间（闭区间），
这个时候只需要比较prices[i]-minPrice和max就可以求出截止到第i天的最大获益，然后根据需要更新minPrice
求right时，记录从第i天往后的最高价maxPrice与最大获益max，求right[i]：考虑要再第i天买进，那么卖出时间必然是在i到最后一天之间（闭区间），
这个时候只需要比较maxPrice-prices[i]和max就可以求出从第i天开始的最大获益，然后根据需要更新maxPrice
对于left和right的构造算法复杂度都是O(n)
构造完left和right之后，只要求left[i]+right[i]的最大值就行了。
典型错误想法：将两个获益最大区间的获益相加 -> 因为会产生未卖先买的冲突
*/

public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int max = 0;
        
        int minPrice = prices[0];
        for(int i = 1; i < len; i++) {
            if(prices[i] < minPrice) {
                minPrice = prices[i];
                left[i] = left[i - 1];
            }else {
                left[i] = Math.max(left[i - 1], prices[i] - minPrice);
            }
        }
         
        int maxPrice = prices[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            if(prices[i] > maxPrice) {
                maxPrice = prices[i];
                right[i] = right[i + 1];
            }else {
                right[i] = Math.max(right[i + 1], maxPrice - prices[i]);
            }
        }
        
        for(int i = 0; i < len; i++) {
            max = Math.max(max, left[i] + right[i]);
        }
        return max;
    }
}