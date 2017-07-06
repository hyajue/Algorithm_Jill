package TwoPointers;
/**
* Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
* 
* For example,
* S = "ADOBECODEBANC"
* T = "ABC"
* Minimum window is "BANC".
* 
* Note:
* If there is no such window in S that covers all characters in T, return the empty string "".
* 
* If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

/*
复杂度
时间O(n) 空间O(n)
思路：
此类window题型，我们都需要用两个指针，用一个map记录字符及其出现次数。设置一个count变量，表示map中出现的不同字符的个数。
设置左右指针，右指针一直向右移动。当当前字符属于map，更新map对应的值。当该值等于0，证明已经遍历了t里全部该字符，count--
当count==0的时候，证明当前字符串包含了t全部字符。开始移动左指针。
如果左指针所在字符在map里，更新数值加一。如果map对应的该值>0，那么count++，结束循环。
维护minLen和Len。
*/
public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
        if(s == null || t == null) return null;
        if(t.length() == 0 || s.length() < t.length()) return "";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        int count = map.size();
        int minLen = s.length() + 1;
        int minLeft = 0;
        int left = 0, right = 0;
        while(right < s.length()){
            char rC = s.charAt(right);
            if(map.containsKey(rC)){
                map.put(rC, map.get(rC) - 1);
                if(map.get(rC) == 0)
                    count--;
            }
            right++;
            while(count == 0){
                char lC = s.charAt(left);
                if(map.containsKey(lC)){
                    map.put(lC, map.get(lC) + 1);
                    if(map.get(lC) > 0)
                        count++;
                }
                if(right - left < minLen){
                    minLen = right - left;
                    minLeft = left;
                }
                left++;
            }
        }
        if(minLen == s.length() + 1) return "";
        return s.substring(minLeft, minLeft + minLen);
    }
}
