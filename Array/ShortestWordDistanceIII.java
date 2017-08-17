/**
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/
/*

*/
我们将p1初始化为数组长度，p2初始化为数组长度的相反数，然后当word1和word2相等的情况，我们用p1来保存p2的结果，p2赋为当前的位置i，这样我们就可以更新结果了，如果word1和word2不相等，则还跟原来的做法一样
/*
改进：
只用一个变量idx，这个idx的作用就相当于记录上一次的位置，当前idx不等-1时，说明当前i和idx不同，然后我们在word1和word2相同或者words[i]和words[idx]相同的情况下更新结果，最后别忘了将idx赋为i
*/
//一个idx
public class ShortestWordDistanceIII {
	public int shortestDistance(String[] words, String word1, String word2) {
		int idx = -1;
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(word1) || words[i].equals(word2)) {
				//word1和word2相等，不需要考虑那种 连续两次都遇到word1的情况
				if(idx != -1 && (word1.equals(word2) || !words[i].equals(words[idx]))
					res = Math.min(res, i - idx);
				idx = i;
			}
		}
		return res;
	}
}
//双指针：
public int shortestWordDistance(String[] words, String word1, String word2) {
    long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
    boolean same = word1.equals(word2);
    for (int i=0; i<words.length; i++) {
        if (words[i].equals(word1)) {
            if (same) {
                i1 = i2;
                i2 = i;
            } else {
                i1 = i;
            }
        } else if (words[i].equals(word2)) {
            i2 = i;
        }
        dist = Math.min(dist, Math.abs(i1 - i2));
    }
    return (int) dist;
}