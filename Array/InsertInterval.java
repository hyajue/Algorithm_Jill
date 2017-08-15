/**
* Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
* 
* You may assume that the intervals were initially sorted according to their start times.
* 
* Example 1:
* Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
* 
* Example 2:
* Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
* 
* This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/

/*
思路： 
scan input list to find where to insert newInterval
if newInterval.end < curInterval.start: insert front of curInterval 
if curInterval.end < newInterval.strat: next interval 
if newInterval has overlaps with curInterval: newStart = min of two starts; newEnd = max of two ends
if no overlaps with any curIntervals: insert at end of input list
*/
public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if(intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        
        int idx = 0;
        //第一种不overlap的情况是intervals.get(idx).end < newInterval.start
        while(idx < intervals.size() && intervals.get(idx).end < newInterval.start)
            res.add(intervals.get(idx++));
        //第二种不overlap的情况是intervals.get(idx).start > newInterval.end
        //在这中间的overlapping情况可以如下处理：
        while(idx < intervals.size() && intervals.get(idx).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(idx).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(idx).end);
            idx++;
        }
        //记得加入overlap段的结果
        res.add(newInterval);
        //第二种不overlap的情况，加入结果
        while(idx < intervals.size())
            res.add(intervals.get(idx++));
        
        return res;
    }
}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */