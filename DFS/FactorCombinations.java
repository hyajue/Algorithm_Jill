/**
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 

Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
 

Examples: 
input: 1
output: 
[]

input: 37
output: 
[]

input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/



public class Solution {
    public List<List<Integer>> getFactors(int n) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	if(n <= 3) return res;
    	backtrack(n, 2, res, new ArrayList<Integer>());
    	return res;
    }
    private void backtrack(int n, int start, List<List<Integer>> res, List<Integer> subRes) {
    	if(n == 1) {
    		if(subRes.size() > 1)
    			res.add(new ArrayList<>(subRes));
    		return;
    	}
    	// i <= n eg: 2 2 2 2 --> 16
    	for(int i = start; i <= n; i++) {
    		if(n % i == 0) {
    			subRes.add(i);
    			backtrack(n/i, i, res, subRes);
    			subRes.remove(subRes.size() - 1);
    		}	
    	}
    }
}






