/**
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
思路：
hashmap记录单词位置，这样每次直接调用hashmap找单词，O(1)时间取到位置
不同的是这次我们需要多次调用求最短单词距离的函数，那么用之前那道题的解法二和三就非常不高效，而当时我们摒弃的解法一的思路却可以用到这里，我们用哈希表来建立每个单词和其所有出现的位置的映射，然后在找最短单词距离时，我们只需要取出该单词在哈希表中映射的位置数组进行两两比较即可
*/
		int res = INT_MAX;
        for (int i = 0; i < m[word1].size(); ++i) {
            for (int j = 0; j < m[word2].size(); ++j) {
                res = min(res, abs(m[word1][i] - m[word2][j]));
            }
        }
/*
改进：
使查询的复杂度由上面的O(MN)变为O(M+N)，其中M和N为两个单词的长度，我们需要两个指针i和j来指向位置数组中的某个位置，开始初始化都为0，然后比较位置数组中的数字，将较小的一个的指针向后移动一位，直至其中一个数组遍历完成即可
*/

public class WordDistance {
	private Map<String, List<Integer>> map;
	public WordDistance(String[] words) {
		for(int i = 0; i < words.length; i++) {
			if(!map.containsKey(words[i])) {
				map.put(words[i], new ArrayList<Integer>());
			}
			map.get(words[i]).add(i);
		}
	}
	public int shortest(String word1, String word2) {
		List<Integer> l1 = map.get(word1);
		List<Integer> l2 = map.get(word2);
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE;
		while(i < l1.size() && j < l2.size()) {
			int idx1 = l1.get(i);
			int idx2 = l2.get(j);
			if(idx1 < idx2) {
				min = Math.min(idx2 - idx1, min);
				i++;
			} else {
				min = Math.min(idx1 - idx2, min);
				j++;
			}
		}
		return res;
	}
}



