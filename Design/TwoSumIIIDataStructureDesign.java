/**
* Design and implement a TwoSum class. It should support the following operations: add and find.
* 
* add - Add the number to an internal data structure.
* find - Find if there exists any pair of numbers which sum is equal to the value.
* 
* For example,
* add(1); add(3); add(5);
* find(4) -> true
* find(7) -> false
*/

/*
The trade off should be considered: 
there has to be one operation's time complexity is O(n) and the other is O(1), no matter add or find. 
So clearly there's trade off when solve this problem, prefer quick find or quick add.
*/

// if quick find is preferred: add will be pre-done
public class TwoSum { 
    Set<Integer> num;
    Set<Integer> sum;

	public TwoSum() {
		num = new HashSet<>();
		sum = new HashSet<>();
    }  
    // Add the number to an internal data structure 
    public void add(int number) {
		if(num.contains(number))
			sum.add(number * 2);
		else {
			for(int item : num) {
				sum.add(number + item);
			}
			num.add(number);
		}
    }
    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
		return sum.contains(value);
    }
}

// if more add ops and quick add is preferred:
public class TwoSum { 
    Map<Integer, Integer> map;

	public TwoSum() {
		map = new HashMap<>();
    }  
    // Add the number to an internal data structure 
    public void add(int number) {
		map.put(number, map.getOrDefault(number, 0) + 1);
    }
    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
		for(int num : map.keySet()) {
			if(map.containsKey(value - num))
				return true;
			else if(map.get(num) >= 2 && value - num == num)
				return true;
		}
		return false;
    }
}




