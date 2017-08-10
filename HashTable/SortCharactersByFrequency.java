/**
* Given a string, sort it in decreasing order based on the frequency of characters.
* 
* Example 1:
* 
* Input:
* "tree"
* 
* Output:
* "eert"
* 
* Explanation:
* 'e' appears twice while 'r' and 't' both appear once.
* So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
* Example 2:
* 
* Input:
* "cccaaa"
* 
* Output:
* "cccaaa"
* 
* Explanation:
* Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
* Note that "cacaca" is incorrect, as the same characters must be together.
* Example 3:
* 
* Input:
* "Aabb"
* 
* Output:
* "bbAa"
* 
* Explanation:
* "bbaA" is also a valid answer, but "Aabb" is incorrect.
* Note that 'A' and 'a' are treated as two different characters.
*/

/*
复杂度
时间：O(n) 空间：O(n)
思路：HashMap + bucket sorting
1.建立哈希映射： <char, frequency>
2.建立桶,每个元素对应一个 frequency
3.哈希元素分配入桶.
4.倒序遍历桶(频率从大到小),顺序将每个桶的元素append到StringBuilder中
*/

public class Solution {
    public String frequencySort(String s) {
        if(s == null || s.length() == 0) return "";
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
            len = Math.max(len, map.get(c));
        }
        List<Character>[] bucket = new List[len + 1];
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            int val = entry.getValue();
            if(bucket[val] == null)
                bucket[val] = new ArrayList<Character>();
            bucket[val].add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = len; i > 0; i--){
            if(bucket[i] != null){
                for(char c : bucket[i]){
                    for(int j = 0; j < i; j++)
                        sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

/*
思路：hashmap + priorityqueue
Time: O(nlogn) Space: O(n)
*/
public class Solution {
    public String frequencySort(String s) {
        if(s == null || s.length() == 0) return "";
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(map.size(), (a, b) -> (b.getValue() - a.getValue()));
        //!!!!!!
        pq.addAll(map.entrySet());
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Map.Entry e = pq.poll();
            //!!!!
            int val = (int)e.getValue();
            //!!!!
            char c = (char)e.getKey();
            for(int i = 0; i < val; i++)
                sb.append(c);
        }
        return sb.toString();
    }
}