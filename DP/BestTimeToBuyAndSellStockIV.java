/**
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete at most k transactions.
* 
* Note:
* You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
solution 2:
复杂度：
时间O(n) 空间O(k) 
最多可以进行k次交易的算法： 还是使用“局部最优和全局最优解法”
维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），
另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）
递推式： global[i][j] = max(local[i][j],global[i-1][j])，
就是取当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。
对于局部变量的维护： local[i][j] = max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），
第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，
而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件
上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)
*/

/*
这里我们需要两个递推公式来分别更新两个变量local和global，参见网友Code Ganker的博客，我们其实可以求至少k次交易的最大利润。我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：

local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
global[i][j] = max(local[i][j], global[i - 1][j])，

其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值后相比，两者之中取较大值，而全局最优比较局部最优和前一天的全局最优。

Note: 但这道题还有个坑，就是如果k的值远大于prices的天数，比如k是好几百万，而prices的天数就为若干天的话，上面的DP解法就非常的没有效率，应该直接用Best Time to Buy and Sell Stock II 买股票的最佳时间之二的方法来求解，所以实际上这道题是之前的二和三的综合体
*/

public class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        //如果k远大于就是如果k的值远大于prices的天数，比如k是好几百万，而prices的天数就为若干天的话，上面的DP解法就非常的没有效率
        if(prices.length <= k)
            return solveMaxProfit(prices);
        
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        
        for(int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i-1];
            //???为什么逆序 : 防止global[j - 1]已经被修改
            for(int j = k; j >= 1; j--) {
                local[j] = Math.max(local[j] + diff, global[j - 1] + (diff > 0 ? diff : 0));
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }
    private int solveMaxProfit(int[] prices) {
        int res = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1])
                res += prices[i] - prices[i-1];
        }
        return res;
    }
}