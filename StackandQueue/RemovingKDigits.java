package StackandQueue;
/**
* Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
* 
* Note:
* The length of num is less than 10002 and will be ≥ k.
* The given num does not contain any leading zero.
* Example 1:
* 
* Input: num = "1432219", k = 3
* Output: "1219"
* Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
* Example 2:
* 
* Input: num = "10200", k = 1
* Output: "200"
* Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
* Example 3:
* 
* Input: num = "10", k = 2
* Output: "0"
* Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/
/*
 Time: O(n^2) Space:O(n)
 思路：greedy + stack
 顺序遍历数组，保证在k>0的情况下，如果遍历到的c要比stack的最顶端的数字小，那么k--，
 并且将stack最顶端替换成当前c，否则就将stack往后expand
 corner case有四种
 1. k = 0    2. num.length() == k
 3. 11111111 (数字相等，无从去除)。所以stack数组长度要跟num长度一样
 4. 10121   k = 1 (生成的结果最开头是0)
 */

public class RemovingKDigits {
	public String removeKdigits(String num, int k) {
        if(k == 0) return num;
        if(num.length() == k) return "0";
        int len = num.length() - k;
        int top = 0;
        //防止corner case 11111  10111  k=2
        //使用char数组，而非stack来表示stack，是为了最后计算结果的时候，不需要将stack反向弹出再reverse
        char[] stack = new char[num.length()];
        
        //顺序遍历数组，保证在k>0的情况下，如果遍历到的c要比stack的最顶端的数字小，那么k--，并且将stack最顶端替换成当前c，否则就将stack往后expand
        for(char c : num.toCharArray()){
            while(top > 0 && stack[top-1] > c && k > 0){
                top--;
                k--;
            }
            stack[top++] = c;
        }
        
        //删除stack最前面的0.
        int i = 0;
        while(i < top && stack[i] == '0')
            i++;
        
        return i == len ? "0" : new String(stack, i, len - i);
    }
}
