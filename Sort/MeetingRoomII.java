/**
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
*/
/*
思路： Sort + minHeap
Time: O(nlogn) Space: O(n)
用sort来排序interval的start time，再用min heap来当做不同的room：
比如第一个时间段我们放入房间1，然后第二个时间段，如果和房间1的结束时间不冲突，就放入房间1，否则开辟一个房间2。
然后第三个时间段，如果和房间1或者房间2的结束时间不冲突，就放入房间1或者2，否则开辟一个房间3，依次类推，最后统计开辟了多少房间。
对于每个房间，我们只要记录其结束时间就行了，这里我们查找不冲突房间时，只要找结束时间最早的那个房间。

Note:
如果我们把这些房间当作List来管理，每次查询需要O(N)时间，如果我们用heap来管理，可以用logN时间找到时间最早结束的房间。
*/

//更优化： pq存intervals[i].end，而不是存interval
public int minMeetingRooms(Interval[] intervals) {
	if (intervals.length == 0) {
		return 0;
	}
	// sort
	Arrays.sort(intervals, new Comparator<Interval>() {
		@Override
		public int compare(Interval a, Interval b) {
			return a.start - b.start;
		}
	});
	// PriorityQueue
	PriorityQueue<Integer> ends = new PriorityQueue<Integer>();
	ends.offer(intervals[0].end);
	for (int i = 1; i < intervals.length; i++) {
		if (intervals[i].start >= ends.peek()) { // no overlap, then should update smallest end.
			ends.poll();
		} 
		ends.offer(intervals[i].end);
	}
	return ends.size();
}

public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
		if(intervals == null || intervals.length == 0)
			return 0;
		// Sort the intervals by start time
		Arrays.sort(intervals, (a, b) -> (a.start - b.start));
		 // Use a min heap to track the minimum end time of merged intervals
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
		pq.offer(intervals[0]);
		for(int i = 1; i < intervals.length; i++) {
			// get the meeting room that finishes earliest
			Interval interval = pq.poll();
			
			// if the current meeting starts right after 
            // there's no need for a new room, merge the interval
			if(intervals[i].end >= interval.end)
				interval.end = intervals[i].end;
			else
				// otherwise, this meeting needs a new room
				pq.offer(intervals[i]);
			// don't forget to put the meeting room back
			pq.offer(interval);
		}
		return pq.size();
	}
}