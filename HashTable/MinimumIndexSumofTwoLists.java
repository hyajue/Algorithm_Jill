package HashTable;
/**
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * 
 *You need to help them find out their common interest with the least list index sum. 
 *If there is a choice tie between answers, output all of them with no order requirement. 
 *You could assume there always exists an answer.
 *
 */
/*
Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".


Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
*/
/**
 Note:
1. The length of both lists will be in the range of [1, 1000].
2. The length of strings in both lists will be in the range of [1, 30].
3. The index is starting from 0 to the list length minus 1.
4. No duplicates in both lists. 
 */

/*解法：HashTable
 * Time: O(n) Space: O(n)
 * 
 * Note: 可能会有坐标相加都等于min的多个答案，所以需要将其放在一个List里
 * 最后，将List转成Array
 */
public class MinimumIndexSumofTwoLists {
	public String[] findRestaurant(String[] list1, String[] list2) {
        if(list1 == null || list1.length == 0 || list2 == null || list2.length == 0)
            return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> res = new LinkedList<String>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list1.length; i++)
            map.put(list1[i], i);
        for(int i = 0; i < list2.length; i++){
            if(map.containsKey(list2[i])){
                int j = map.get(list2[i]);
                if(j + i == min){
                    res.add(list2[i]);
                }else if(j + i < min){
                    res = new LinkedList<String>();
                    min = j + i;
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
