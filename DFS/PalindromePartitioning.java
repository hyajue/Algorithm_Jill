package DFS;
/**
* Given a string s, partition s such that every substring of the partition is a palindrome.
* 
* Return all possible palindrome partitioning of s.
* 
* For example, given s = "aab",
* Return
* 
* [
*   ["aa","b"],
*   ["a","a","b"]
* ]
*/

/*
复杂度
时间 O(2^(n-1)) 空间O(n)
长度为n的字符串 有n-1个地方可以隔开 每个地方可以放挡板或者不放 -> 2^(n-1)
思路：回溯法 
N-皇后模型
*/
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(s == null || s.length() == 0) return res;
        helper(res, new ArrayList<String>(), s, 0);
        return res;
    }
    private void helper(List<List<String>> res, List<String> subRes, String s, int index){
        if(index >= s.length()){
            res.add(new ArrayList<String>(subRes));
            return;
        }
        for(int i = index; i < s.length(); i++){
            String str = s.substring(index, i + 1);
            if(isValidPalindrome(str)){
                subRes.add(str);
                helper(res, subRes, s, i + 1);
                subRes.remove(subRes.size() - 1);
            }
        }
    }
    private boolean isValidPalindrome(String str){
        if (str.length() == 1) return true;
        int l = 0, r = str.length() - 1;
        while(l < r){
            if(str.charAt(l) != str.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
