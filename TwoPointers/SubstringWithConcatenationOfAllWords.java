package TwoPointers;
/**
* You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
* 
* For example, given:
* s: "barfoodbarthefoobarman"
* words: ["foo", "bar"]
* 
* You should return the indices: [0,9].
* (order does not matter).
*/

/*
复杂度
时间：O(n) 空间O(n)
思路：双指针(滑动窗口)
维护一个窗口 窗口里面是字典里出现的词 同max length of substring套路一样 只不过这里是去匹配一个字符串
*/
public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        if(s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);
        int wordLen = words[0].length();
        int wordNum = words.length;
        for(int i = 0; i < wordLen; i++){//initial pos
            int count = 0; // number of words in the window
            int left = i;
            HashMap<String, Integer> curMap = new HashMap<String, Integer>();
            for(int right = i; right <= s.length() - wordLen; right += wordLen){
                String str = s.substring(right, right + wordLen);
                if(!map.containsKey(str)){ //search miss
                    curMap.clear();
                    count = 0;
                    left = right + wordLen;
                }else{
                    curMap.put(str, curMap.getOrDefault(str, 0) + 1);
                    count++;
                    while(curMap.get(str) > map.get(str)){
                        String temp = s.substring(left, left + wordLen);
                        curMap.put(temp, curMap.get(temp) - 1);
                        left += wordLen;
                        count--;
                    }
                    if(count == wordNum){
                        res.add(left);
                        String temp = s.substring(left, left + wordLen);
                        curMap.put(temp, curMap.get(temp) - 1);
                        left += wordLen;
                        count--;
                    }
                }
            }
        }
        return res;
    }
}
