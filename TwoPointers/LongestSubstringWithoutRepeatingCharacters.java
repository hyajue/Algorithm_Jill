package TwoPointers;
/**
* Given a string, find the length of the longest substring without repeating characters.
* 
* Examples:
* 
* Given " b  a  c  a  d e", the answer is "abc", which the length is 3.
*              left
				          right
* Given "bbbbb", the answer is "b", with the length of 1.
* 
* Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
/*
复杂度
时间 O(n) 空间O(n)
思路：双指针(或叫移动窗口法)
*/
/*
@Method1： HashMap
维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动 
维护一个HashMap, 记录某个字符对应的遍历过的最后一次出现的位置。
正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，如果发现重复字符，则说明当前窗口中的串已经不满足要求，
此时更新左窗口位置。那么左窗口取 max(当前位置 和上次出现该重复字符+1)，并检查res长度是否需要更新
因为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。空间复杂度为HashMap的size,是O(n)
*/
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int left = 0, right = 0;
        int res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                left = Math.max(left, map.get(c) + 1);
            }
            res = Math.max(res, right - left + 1);
            map.put(c, right++);
        }
        return res;
    }
}

/*@Method2: ASCII 256
 * 需要注意的是，map[当前字符] 应该对应 字符位置+1， 用来区分当没有 map[当前字符]==0 表示没出现过该字符
 */
public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0) return 0;
    int[] map = new int[256];
    int left = 0, right = 0;
    int res = 0;
    while(right < s.length()){
        char c = s.charAt(right);
        left = Math.max(left, map[c]);
        res = Math.max(res, right - left + 1);
        map[c] = right + 1;
        right++;
    }
    return res;
}

// @Method3: HashSet
public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0) return 0;
    Set<Character> set = new HashSet<Character>();
    int right = 0, left = 0;
    int res = 0;
    while(right < s.length()){
        char c = s.charAt(right);
        if(!set.contains(c)){
            set.add(c);
            res = Math.max(res, right - left + 1);
            right++;
        }else{ //一直挪动左指针到 第一个非右指针对应字符位置
            set.remove(s.charAt(left++));
        }
    }
    return res;
}
