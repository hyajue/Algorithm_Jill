package String;
/**
* Given two strings S and T, determine if they are both one edit distance apart.
*/

/*
复杂度
时间O(n) 空间O(1)
思路：
首先两个字符串如果长度相差超过1 直接返回false
当两个字符串首次出现不同的字符时，根据定义，剩下的字符要相同才能返回true
case1: lenS　＞　lenT
S: xyzzzzz
T： xzzzzz
case2: lenT > lenS
S: xz
T: xyz 
case3: lenT == lenS 
qwe
qte
*/
public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if(s == null || t == null) return false;
		if(s.length() > t.length()){ //保证s永远小于等于t
			String temp = s;
			s = t;
			t = s;
		}
		if(t.length() - s.length() > 1) return false;
		String subS = "", subT = "";
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != t.charAt(i)){
				//case3: 
				if(s.length() == t.length()){
					subS = s.substring(i+1);
					subT = t.substring(i+1);
				//case 1&2
				}else{
					subS = s.substring(i);
					subT = t.substring(i+1);
				}
			}
			return subS.equals(subT);
		}
		return false; //两个string完全相等
	}
}
