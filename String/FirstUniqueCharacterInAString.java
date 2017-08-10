/**
* Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
* 
* Examples:
* 
* s = "leetcode"
* return 0.
* 
* s = "loveleetcode",
* return 2.
*/

/*
复杂度
时间：O(n) 空间：O(1)
思路：顺序扫描
维护一个频率数组,将26个字母映射成int后,数组每个元素代表该字母在原词中出现的频率.
first pass：统计各个字母出现频率
second pass:找第一个频率为1的字母返回
*/

public class FirstUniqueCharacterInAString {
  public int firstUniqChar(String s) {
    if (s == null || s.length() == 0) return -1;
    int[] map = new int[26];
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'a';
			map[idx]++;
    }
    for (int i = 0; i < s.length(); i++) {
      idx = s.charAt(i) - 'a';
			if (map[idx] == 1) return i;
    }
    return -1;
  }
}

//Method 2: Two Pointers
public class Solution {
    public int firstUniqChar(String s) {
        if(s.length() == 0 || s == null) return -1;
        int[] map = new int[26];
        char[] cc = s.toCharArray();
        int len = cc.length;
        int fast = 1;
        int slow = 0;
        map[cc[slow] - 'a']++;
        
        while(fast < len){
            map[cc[fast] - 'a']++;
            // if slow pointer is not a unique character anymore, move to the next unique one
            while(slow < len && map[cc[slow] - 'a'] > 1)
                slow++;
            // if(map[cc[slow] - 'a'] == 0){// not yet visited by the fast pointer
            //     map[cc[slow] - 'a']++;
            //     fast = slow;// reset the fast pointer
            // }
            fast++;
        }
        if(slow == len) return -1;
        else return slow;
    }
}
