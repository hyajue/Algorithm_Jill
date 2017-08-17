/**
*Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
*
*Each element is either an integer, or a list -- whose elements may also be integers or other lists.
*
*Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
*
*Example 1:
*Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
*
*Example 2:
*Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17) 
*/
/*
思路：leve order + 用加法代替乘法
利用每一层将当前整数加起来，然后往后遍历多一层就将前面已经加过的数再加一遍
un = 0 w = 0
un = 1 w = 1
un = 1 + 4 w = 1 + (1 + 4)
un = 1 + 4 + 6 w = 1 + (1 + 4) + 1 + 4 + 6
Instead of multiplying by depth, add integers multiple times 
(by going level by level and adding the unweighted sum to the weighted sum after each level)
*/
/*
@Method 2: 层序遍历，给数组加level，最后一起加权算
*/
//没用queue，每层建一个temp数组，每次更换当前数组
public class NestedWeightSumII {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if(nestedList == null || nestedList.size() == 0) return 0;
		
		int unweighted = 0, weighted = 0;
		while(!nestedList.isEmpty()) {
			List<NestedInteger> nextList = new LinkedList<>();
			for(NestedInteger e : nestedList) {
				if(e.isInteger())
					unweighted += e.getInteger();
				else
					nextList.addAdd(e.getList);
			}

			weighted += unweighted;
			nestedList = nextList;
		}
		return weighted;
	}
}

//用queue做level order
public class NestedWeightSumII {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if(nestedList == null || nestedList.size() == 0) return 0;
		Queue<NestedInteger> queue = new LinkedList<>(nestedList);
		int unweighted = 0, weighted = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				NestedInteger e = queue.poll();
				if(!e.isInteger()) {
					queue.addAll(e.getList);
				} else {
					unweighted += e.getInteger();
				}
			}
			weighted += unweighted;
		}
		return weighted;
	}
}



