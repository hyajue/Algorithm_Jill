package HashTable;
/*
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 */
/*
 @Method1： HashSet
 Time: O(1), Space: O(n)
 */
public class ContainsDuplicate {
	public boolean containsDuplicate(int[] nums) {
        if(nums.length <= 1) return false;
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums){
            if(set.contains(i))
                return true;
            else
                set.add(i);
        }
        return false;
    }
}

/*
@Method2： Sorted
Time: O(nlogn), Space: O(1)
*/
public class ContainsDuplicate {
	public boolean containsDuplicate(int[] nums) {
       if(nums.length <= 1) return false;
       Arrays.sort(nums);
       for(int i = 1; i < nums.length; i++){
           if(nums[i] == nums[i-1])
               return true;
       }
       return false;
   }
}
