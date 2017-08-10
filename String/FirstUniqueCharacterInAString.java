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