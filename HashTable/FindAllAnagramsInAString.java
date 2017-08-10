package HashTable;
/**
* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
* 
* Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
* 
* The order of output does not matter.
* 
* Example 1:
* 
* Input:
* s: "cbaebabacd" p: "abc"
* 
* Output:
* [0, 6]
* 
* Explanation:
* The substring with start index = 0 is "cba", which is an anagram of "abc".
* The substring with start index = 6 is "bac", which is an anagram of "abc".
* Example 2:
* 
* Input:
* s: "abab" p: "ab"
* 
* Output:
* [0, 1, 2]
* 
* Explanation:
* The substring with start index = 0 is "ab", which is an anagram of "ab".
* The substring with start index = 1 is "ba", which is an anagram of "ab".
* The substring with start index = 2 is "ab", which is an anagram of "ab".
*/
/*
复杂度
时间：O(n) 空间：O(n)
思路：双指针滑动窗口
*/
/* @Method1： 字母对应的int[26]数组
ASCII一般是128个
只要右标志位没有到s的最右边，就进行大循环。
对右标志位记录的s中的字母进行判断，看p中有没有，这里就是用那个表示p中字母数量的数组来进行判断的，找到了，就把表示要判断的字符串长度减一，不管有没有找到，都要把数量数组减少，右标志位右移，这是为了之后进行判断，因为我们要找的的字符串始终处于左和右的标志位的中间。
如果要找的字符串的长度减少到0了，说明我们在左右标志位中间找到了p字符串长度的重组字，这时候就可以把左标志位，也就是开始的位置，添加到结果数组中。
在循环的最后，先判断左右标志位中间是否是p的长度，是的话，我们就该把左标志位也右移了，而右移之前，先要看看左标志位这个数我们是否找到过，找到过则要把count数量补回1，不论有没有找到过，都要讲数组中的对应的字母数量补回1。
 */
public class FindAllAnagramsInAString {
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if(p == null || p.length() == 0 || s == null || s.length() == 0 || s.length() < p.length()) 
		return res;
        
        int[] map = new int[26];
        int count = 0;
        for(char c : p.toCharArray()){
            if(map[c - 'a'] == 0)
                count++;
            map[c - 'a']++;
        }
        int l = 0, r = 0;
        while(r < s.length()){
            int c = s.charAt(r) - 'a';
            map[c]--;
            if(map[c] == 0)
                count --;
            while(count == 0){
                int lc = s.charAt(l) - 'a';
                if(r - l + 1 == p.length())
                    res.add(l);
                map[lc]++;
                l++;
                if(map[lc] > 0)
                    count++;
            }
            r++;
        }
        return res;
    }
}

public class Solution {  
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if(s.length() < p.length() || s == null) return res;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : p.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        int count = map.size();
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
                if(right - left == p.length()) //!!!!
                    res.add(left);
                if(map.containsKey(lC)){
                    map.put(lC, map.get(lC) + 1);
                    if(map.get(lC) > 0)
                        count++;
                }
                
                left++;
            }
        }
        return res;    
    }
}

