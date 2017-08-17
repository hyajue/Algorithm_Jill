/**
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
/*
原始思路：
我首先想的是要用哈希表来做，建立每个单词和其所有出现位置数组的映射，但是后来想想，反正建立映射也要遍历一遍数组，我们还不如直接遍历一遍数组，直接把两个给定单词所有出现的位置分别存到两个数组里，然后我们在对两个数组进行两两比较更新结果
*/

/*
改进1：
我们其实需要遍历一次数组就可以了，我们用两个变量p1,p2初始化为-1，然后我们遍历数组，遇到单词1，就将其位置存在p1里，若遇到单词2，就将其位置存在p2里，如果此时p1, p2都不为-1了，那么我们更新结果
*/

/*
改进2：
只用一个辅助变量idx，初始化为-1，然后遍历数组，如果遇到等于两个单词中的任意一个的单词，我们在看idx是否为-1，若不为-1，且指向的单词和当前遍历到的单词不同，我们更新结果
*/

//@改进1：
public class ShortestWordDistance {
	public int shortestDistance(String[] words, String word1, String word2) {
		int res = Integer.MAX_VALUE;
		int p1 = -1, p2 = -1;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(word1))
				p1 = i;
			else if(words[i].equals(word2))
				p2 = i;
			if(p1 != -1 && p2 != -1)
				res = Math.min(res, Math.abs(p1 - p2));
		}
		return res;
	}
}

//@改进2：
public class ShortestWordDistance {
	public int shortestDistance(String[] words, String word1, String word2) {
		int idx = -1;
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(word1) || words[i].equals(word2)) {
				if(idx != -1 && !words[i].equals(words[idx]))
					res = Math.min(res, i - idx);
				idx = i;
			}
		}
		return res;
	}
}




