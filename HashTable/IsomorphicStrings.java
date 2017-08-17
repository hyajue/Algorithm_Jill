/**
* Given two strings s and t, determine if they are isomorphic.
* 
* Two strings are isomorphic if the characters in s can be replaced to get t.
* 
* All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
* 
* For example,
* Given "egg", "add", return true.
* 
* Given "foo", "bar", return false.
* 
* Given "paper", "title", return true.
* 
* Note:
* You may assume both s and t have the same length.
*/
/*
说到Character的hashmap，就要想到可以用对应的int数组来表示！
注意：这里的character不仅限于lower letter， 所以应该是ASCII ：256
*/
//hashmap
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null)  // both null strings
  	        return true;
        if (s == null || t == null)   // one of them is null
    	    return false;
        if (s.length() == 0 && t.length() == 0)  // both zero length
    	    return true;    
        if (s.length() != t.length())   // lengths not equal
    	    return false;    
        
        Map<Character, Character> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char k = s.charAt(i);
            char v = t.charAt(i);
            if(!map.containsKey(k)) {
                // muti-key to one value case
                if(!map.containsValue(v))
                    map.put(k, v);
                else
                    return false;
            }else {
                if(map.get(k) != v)
                    return false;
            }
        }
        return true;
    }
}
//类似于双指针！！！
/*
想法是每次指针挪到i的时候，分别记录对应的s和t的那个char的值是i+1(跟初始值0区分开)
t的位置就是char c+256以后所对应的那个
这样可以节省一个数组
*/
public class Solution {
    public boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }
}

//int[]
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null)  // both null strings
  	        return true;
        if (s == null || t == null)   // one of them is null
    	    return false;
        if (s.length() == 0 && t.length() == 0)  // both zero length
    	    return true;    
        if (s.length() != t.length())   // lengths not equal
    	    return false;    
        
        int[] map = new int[256];
        //起始位置-1，防止与0位混淆
        for(int i = 0; i < 256; i++)
            map[i] = -1;
        //检查v是否有重复情况
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            int k = s.charAt(i);
            int v = t.charAt(i);
            //k之前出现过
            if(map[k] != -1) {
                if(map[k] != v)
                    return false;
            } else {
                //k还未出现，然而v已经出现过了：v不能被多个k对应
                if(set.contains(v))
                    return false;
                else {
                    map[k] = v;
                    set.add(v);
                }
            }
        }
        return true;
    }
}