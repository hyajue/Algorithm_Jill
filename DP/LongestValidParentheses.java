package DP;
/**
* Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
* 
* For "(()", the longest valid parentheses substring is "()", which has length = 2.
* 
* Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

/*
solution 1 
复杂度
时间O(n) 空间O(n)
思路：dp
从后向前，一点一点计算。假设dp[i]是从下标i开始到字符串结尾最长括号对长度，s[i]是字符串下标为i的括号。如果s[i-1]是左括号，
如果r = i + d[i] + 1是右括号的话，那d[i-1] = d[i] + 1 + d[r+1](如果r+1 < len)
如果不是则为0。如果s[i-1]是右括号，因为不可能有右括号开头的括号对，所以d[i-1] = 0。
*/
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        int len = s.length();
        int[] dp = new int[len];
        int max = 0;
        
        for(int i = len - 2; i >= 0; i--){
            if(s.charAt(i) == '('){
                int right = i + dp[i+1] + 1;
                if(right < len && s.charAt(right) == ')'){
                    dp[i] = dp[i+1] + 2;
                    if(right + 1 < len)
                        dp[i] += dp[right+1];
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

/*
solution 2
复杂度
时间O(n)  空间O(n)
思路：stack

stack里面存放还没配好对的那些'('的index， left存放最近的一次未配对')'指向的位置
是'(‘的时候push
是’)’的时候，说明可能配对了:
	1. Stack是空的，更新left = i
	2. Stack不为空, pop一个')'。如果此时stack为空了，那么i - left为当前有效距离，跟max比较更新。
		如果stack不为空，那么 i - stack.peek()为当前有效距离，跟max比较更新
*/

public class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        //存'('的位置
        Stack<Integer> stack = new Stack();
        int max = 0;
        int left = -1; //最后一个不满足条件的‘)’所在位置
        
        for(int i = 0; i < s.length(); i++){
            //如果是'(', 压栈当前位置
            if(s.charAt(i) == '(')
                stack.push(i);
            else{
                if(stack.isEmpty())
                    left = i;
                else{
                    stack.pop();
                    if(stack.isEmpty())
                        max = Math.max(max, i - left);
                    else
                        max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}

/*
follow up: can you do it without using extra space?
比如((()())，先遍历一遍将所有的()替换成00，得到((0000)，再遍历一遍，替换所有的(00...00)这种形式字符串为000...000，
这里我们得到(000000，直到遍历完无法替换更多括号。如果所有符号都是0，说明是有效的。这样的时间复杂度是O(N)。
*/
