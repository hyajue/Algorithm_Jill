/**
Given a collection of intervals, merge all overlapping intervals.
For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/*
复杂度
时间：O(nlogn)  空间O(1)
思路：
假设这些interval是有序的（先按起始点排序，然后按结束点排序），
那么要把它们合并就只需要按顺序读过来，如果当前一个和结果集中最后一个有重叠，
那么就把结果集中最后一个元素设为当前元素的结束点
(不用改变起始点因为起始点有序，结果集中最后一个元素起始点肯定不比当前元素大)
给interval排序，在java实现中就是要给interval自定义一个Comparator，
规则是按起始点排序，然后如果起始点相同就按结束点排序。
整个算法是先排序，然后再做一次线性遍历，时间复杂度是O(nlogn+n)=O(nlogn)，
空间复杂度是O(1)，不需要额外空间，只有结果集空间
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, (a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
        res.add(intervals.get(0));
        for(int i = 1; i < intervals.size(); i++){
            if(intervals.get(i).start <= res.get(res.size() - 1).end)
                //Math.max因为 eg : [[1,4],[2,3]]
               res.get(res.size()-1).end = Math.max(intervals.get(i).end, res.get(res.size()-1).end);
            else
                res.add(intervals.get(i));
        }
        return res;
    }
}