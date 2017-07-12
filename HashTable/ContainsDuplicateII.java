package HashTable;
/**
* Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
*/

// Space: O(n), Time O(1)
public class ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length < 2) return false;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
