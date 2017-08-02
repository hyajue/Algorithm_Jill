package Heap;

import java.util.*;


/**
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3
Result: "abcabc"
The same letters are at least distance 3 from each other.

Example 2:
str = "aaabc", k = 3 
Answer: ""
It is not possible to rearrange the string.

Example 3:
str = "aaadbbcc", k = 2
Answer: "abacabcd"
Another possible answer is: "abcabcda"
The same letters are at least distance 2 from each other.
 */
/*
 * @Method 2
思路： greedy + 用int数组来表示char的位置，取代hashmap
Time: O(n), Space: O(n)
一个数组表示char出现的频率，一个数组表示该char的下一个可以有效出现的位置
*/

public class RearrangeStringKDistanceApart {
	public String rearrangeString(String str, int k) {
		if(k < 1) return "";
		if(k == 1 || str == null) return str;
		int len = str.length();
		int[] count = new int[26];
		int[] valid = new  int[26];
		for(int i = 0; i < len; i++)
			count[str.charAt(i) - 'a']++;
		
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < len; i++){
			int charPos = findPos(count, valid, i);
			if(charPos == -1) return "";
			count[charPos]--;
			valid[charPos] = i + k;
			res.append((char)('a' + charPos));
		}
		return res.toString();
	}
	//找到最大freq的char，并且它的位置间隔合理，返回该char是什么
	private int findPos(int[] count, int[] valid, int index){
		int max = Integer.MIN_VALUE;
		int charPos = -1;
		for(int i = 0; i < 26; i++){
			//count[i] > max是因为，为了保证顺序是alphabetic的，避免出现a和b都只剩一个，顺序如何排
			if(count[i] > 0 && count[i] > max && valid[i] <= index){
				max = count[i];
				charPos = i;
			}
		}
		return charPos;
	}
}
/*
 @Method 1:
 思路： prioirtyqueue
 Time: O(n) Space: O(n)
  用hashmap来存char跟对应频率，用heap来存map.entry(频率降序)，
  用一个queue来存刚刚出现过的char： 当queue的size变成k的时候，将队列里的第一个char弹出，重新存进heap里
  
最后如果res和str长度不等，则没有正确值，否则返回res
 */
public class RearrangeStringKDistanceApart {
	public String rearrangeString(String str, int k) {
		if(k < 1) return "";
		if(k == 1 || str == null) return str;
		StringBuilder res = new StringBuilder();
		//count frequency of each char
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < str.length(); i++)
			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
			
		//construct a max heap, which holds	all map entries, and frequency as desc
		Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
		maxHeap.addAll(maxHeap);
		//wait queue
		Queue<Map.Entry<Character, Integer>> wait = new LinkedList<Map.Entry<Character, Integer>>();
		
		while(!maxHeap.isEmpty()){
			Map.Entry<Character, Integer> cur = maxHeap.poll();
			res.append(cur.getKey());
			cur.setValue(cur.getValue() - 1);
			wait.offer(cur);
			
			if(wait.size() < k) continue;
			Map.Entry<Character, Integer> first = wait.poll();
			if(first.getValue() > 0)
				maxHeap.offer(first);
		}
		return res.length() == str.length() ? res.toString() : "";
	}
}
