package HashTable;

import java.util.HashMap;
import java.util.List;

/**
* Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
* 
* "abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
* 
* For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], A solution is:
* 
* [
* ["abc","bcd","xyz"],
* ["az","ba"],
* ["acef"],
* ["a","z"]
* ]
*/
/*
举例理解：
["eqdf", "qcpr"]
((‘q’ - 'e') + 26) % 26 = 12, ((‘d’ - 'q') + 26) % 26 = 13, ((‘f’ - 'd') + 26) % 26 = 2
((‘c’ - 'q') + 26) % 26 = 12, ((‘p’ - 'c') + 26) % 26 = 13, ((‘r’ - 'p') + 26) % 26 = 2
所以"eqdf"和"qcpr"是一组shifted strings
*/

public class GroupShiftedStrings {
	public List<List<String>> groupStrings(String[] strings) { 
		List<List<String>> res = new ArrayList<List<String>>();
		if(strings == null || strings.length == 0) reList<E> res;
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		
		for(String str : strings){
			String key = "";
			for(int i = 1; i < str.length(); i++){
				int offset = str.charAt(i) - str.charAt(i-1);
				key += offset > 0 ? offset : (offset + 26);
			}
			if(!map.containsKey(key))
				map.put(key, new ArrayList<Integer>());				
			map.get(key).add(str);	
		}
		for(List<String> list : map.values())
			res.add(list);
		return res;
	}
}
