package TwoPointers;

import java.util.HashMap;

/**
* Given a string, find the length of the longest substring T that contains at most k distinct characters.
* 
* For example, Given s = “eceba” and k = 2,
* 
* T is "ece" which its length is 3.
*/
/*
这种类型的题都应该用两个指针解决，同时用一个map来记录字符及其出现次数。一个右指针先移动，不断更新map, 
当发现map里的字符个数大于规定个数的时候，开始移动左指针，同时更新map,直到map里的字符个数等于规定个数，
中间不断更新包含规定字符个数的最大长度。
time: O(n), space: O(n)
*/
public class LongestSubstringWithAtMostKDistinctCharacters {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if(s == null || s.length() <= k) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int left = 0, right = 0;
		int count = 0, len = 0;
		while(right < s.length()){
			char rC = s.charAt(right);
			map.put(rC, map.getOrDefault(rC, 0) + 1);
			//if(map.get(rC) == 1) count++; //更新distinct的cha个数
			right++;
			while(map.size() > k){
				char lC = s.charAt(left)；
				if(map.containsKey(lC)){
					map.put(lC, map.get(lC) - 1);
					if(map.get(lC) == 0) map.remove(lC);
				}
				left++;
			}

			len = Math.max(len, right - left);
		}
		return len;
	}
}
