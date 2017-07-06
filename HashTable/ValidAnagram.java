package HashTable;
/**
* Given two strings s and t, write a function to determine if t is an anagram of s.
* 
* For example,
* s = "anagram", t = "nagaram", return true.
* s = "rat", t = "car", return false.
* 
* Note:
* You may assume the string contains only lowercase alphabets.
* 
* Follow up:
* What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/
/*Method1： 用于follow up
复杂度
时间：O(n) 空间：O(n)
思路：哈希表法
维护一个哈希表,<key, value> = <char of s, freq of char>
然后遍历t,只要当前char c 不存在于哈希表中,就返回false;否则对应的频率减一
最后遍历哈希表,如果所有的字母对应的频率都为0,则证明s和t为同素异形词
这个方法并不受限于输入词的编码方式, unicode也可用 
*/
public boolean isAnagram(String s, String t) {
    if(s == null || t == null || s.length() != t.length())
        return false;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(char c : s.toCharArray())
        map.put(c, map.getOrDefault(c, 0) + 1);
    for(char c : t.toCharArray()){
        if(!map.containsKey(c)) 
            return false;
        map.put(c, map.get(c) - 1);
        if(map.get(c) == 0)
            map.remove(c);
    }
    return true;
}


/*Method2： 数组哈希
复杂度
时间：O(n) 空间：O(1)
思路：数组哈希法
思路跟上面的解法类似,只是用一个长度为26的数组存储各个字母出现的频率.
该解法受限于输入字符串的编码格式.
*/
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length())
            return false;
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        for(int i : map)
            if(i != 0) return false;
        
        return true;
    }
}
