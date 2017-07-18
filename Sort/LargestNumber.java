package Sort;
/**
* Given a list of non negative integers, arrange them such that they form the largest number.
* 
* For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
* 
* Note: The result may be very large, so you need to return a string instead of an integer.
*/

/*
复杂度
时间O(n) 空间O(n)
思路： 
先把输入数组排序 自定义Comparator：按照最高位的大小进行排序 
*/
public class LargestNumber {
	public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                return str2.compareTo(str1);// reverse order here, so we can do append() later
            }
        });
        // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        if(strs[0].charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for(String s : strs)
            sb.append(s);
        return sb.toString();
    }
}
