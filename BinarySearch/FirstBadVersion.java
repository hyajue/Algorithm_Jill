package BinarySearch;
/**
* You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
* 
* Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
* 
* You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

/*
复杂度
时间 O(logN) 空间 O(1)
思路:二分搜索
因为一个版本是错误，其后面的所有版本都是错误的，所以我们可以用二分搜索，当取中点时，如果中点是错误版本，说明后面都是错误的，那第一个错误版本肯定在前面。如果中点不是错误版本，说明第一个错误版本肯定在后面。
注意
这里直接使用min <= max的二分模板，因为我们其实要找的是好和坏的分界点，即这个点既不是好也不是坏，所以是找不到的，按照模板的特点，最后退出循环时，max指向小于目标的点，min指向大于目标的点，这里第一个坏的version较大，所以返回min
*/	  
public class FirstBadVersion {
	public int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l <= r){
            int mid = (r - l) / 2 + l;
            if(!isBadVersion(mid))
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
}
