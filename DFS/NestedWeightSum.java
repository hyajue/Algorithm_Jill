/**
*Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
*
*Each element is either an integer, or a list -- whose elements may also be integers or other lists.
*
*Example 1:
*Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
*
*Example 2:
*Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
*/

/*
思路：DFS + backtracking
Time: O(n)
*/
public class NestedWeightSum {
	public int depthSum(List<NestedInteger> nestedList) {
    	return nestedSum(nestedList, 1);
	}
	private int nestedSum(List<NestedInteger> curList, int level) {
		int res = 0;
		for(NestedInteger e : curList) {
			res += e.isInteger() ? e.getInteger() * level : nestedSum(e.getList(), level + 1);
		}
		return res;
	}
}

/*
思路： BFS
level order + stack
*/
public class NestedWeightSum {
	public int depthSum(List<NestedInteger> nestedList) {
    	int res = 0;
    	if(nestedList == null || nestedList.size() == 0)
    		return res;
    	//直接加入这个list
    	Queue<NestedInteger> queue = new LinkedList<>(nestedList);
    	int level = 1;

    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		for(int k = 0; k < size; k++) {
    			NestedInteger e = queue.poll();
    			if(!queue.isInteger())
    				//此处需要addAll。因为可能会有很多object
    				queue.addAll(e.getList());
    			else {
    				res += level * e.getInteger();
    			}
    		}
    		level++;
    	}
    	return res;
	}
}





