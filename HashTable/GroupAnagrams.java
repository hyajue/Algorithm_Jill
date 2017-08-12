package HashTable;
/**
* Given an array of strings, group anagrams together.
* 
* For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
* Return:
* 
* [
*   ["ate", "eat","tea"],
*   ["nat","tan"],
*   ["bat"]
* ]
* Note: All inputs will be in lower-case.
*/

/*
复杂度
时间:O(n*klogk), where k is the length of longest word in array  空间：O(n)
思路：排序+哈希表
将排序后的单个单词作为key，映射到哈希表中。如果有相应的key存在，则说明之前出现过它的anagrams,把当前词插入存在记录的列表中
如果没有相应的key存在，则创建一条新纪录， 把当前词插进去

char[] cs       String s = new String(cs);
*/
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) return res;
        HashMap<String, List<String>> map= new HashMap<String, List<String>>();
        
        for(String s : strs){
            char[] cS = s.toCharArray();
            Arrays.sort(cS);	
            String str = new String(cS);// char[]如何转成string！！！！
            if(!map.containsKey(str))
                map.put(str, new ArrayList<String>());
            map.get(str).add(s);
        }
        
        for(String str : map.keySet())
            res.add(map.get(str));
        
        return res;
    }
}
