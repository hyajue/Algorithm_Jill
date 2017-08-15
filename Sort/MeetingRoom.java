/**
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

For example, Given [[0, 30],[5, 10],[15, 20]], return false.
*/

/*
思路：Sort
Time: O(nlogn), Space: O(1)
我们按开始时间把这些Interval都给排序后，就挨个检查是否有冲突就行了。
有冲突的定义是开始时间小于之前最晚的结束时间。
Note: 
这里之前最晚的结束时间不一定是上一个的结束时间，所以我们更新的时候要取最大值。
*/
public class MeetingRoom {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length < 1) return false;
		if(intervals.length == 1) return true;
		
		Collections.sort(intervals, (a, b) -> (a.start == b.start ? a.end - b.end : a.start - b.start));
		int end = intervals[0].end;
		for(int i = 1; i < intervals.length; i++){
			// 如果Interval的开始时间小于之前最晚的结束时间，就返回假
			if(intervals[i].start < end)
				return false;
			else
				end = Math.max(end, intervals[i].end);
		}
		return true;
    }
}