/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

/*
Follow Up:

Q3: 

1. Store the two strings in distributed system(whether self designed or not), then using MapReduce technique to solve the problem;

2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;

3. Processing the Strings by streaming, then check.

------------------------------------------------
If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.

If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.

I think the second part of the solution is impractical, if you read 2 elements at a time, this procedure will take forever. In principle, we want minimize the number of disk access during the run-time.
An improvement can be sort them using external sort, read (let’s say) 2G of each into memory and then using the 2 pointer technique, then read 2G more from the array that has been exhausted. Repeat this until no more data to read from disk.
But I am not sure this solution is good enough for an interview setting. Maybe the interviewer is expecting some solution using Map-Reduce paradigm.

*/

//Method1: 2 Pointers
//Time: O(nlogn) Space: O(n)
//Need to sort the arrays first
class IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(nums1.length == 0 || nums2.length == 0) return new int[] {};
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j])
                i++;
            else if(nums2[j] < nums1[i])
                j++;
            else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] ret = new int[res.size()];
        int z = 0;
        for(Integer num : res) {
            ret[z++] = num;
        }
        return ret;
    }
}

//Method2: HashMap
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        if(nums1.length == 0 || nums2.length == 0) return new int[] {};
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int n : nums1) {
            if(map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
        }
        for(int n : nums2) {
            if(map.containsKey(n) && map.get(n) > 0) {
                map.put(n, map.get(n) - 1);
                ls.add(n);
            }
        }
        int[] res = new int[ls.size()];
        for(int i = 0; i < ls.size(); i++) {
            res[i] = (Integer) ls.get(i);
        }
        return res;
    }
}

