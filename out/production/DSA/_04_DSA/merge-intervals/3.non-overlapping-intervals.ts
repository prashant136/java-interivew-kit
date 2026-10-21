
// https://leetcode.com/problems/non-overlapping-intervals/description/

/*
    🧠 Pattern Recognition

    If question says:
        Remove minimum intervals
        Avoid overlap
        Maximum non-overlapping meetings
        Schedule optimization

    👀 Non-Overlapping Intervals is actually a Greedy + Merge Interval
*/

/*
    🚨 Biggest Insight (Interview Gold)

    We are NOT merging.

    We are choosing intervals smartly.

    Goal: Keep maximum non-overlapping intervals.

    Because:
        minimum removed = total intervals - maximum kept
    
    
    ----------------------------------------------------------------
    🔥 Key Greedy Idea

    Always keep the interval which ends earliest.

    Why?
        Because it leaves more space for future intervals.

    Example:
        [1,100]
        [2,3]
        [3,4]

    If you keep [1,100] ❌
        Everything else overlaps.

    If you keep short ones ✅
    You can keep more intervals.
*/

/*
    ✅ Step 1 — Sort by END Time (Important)

    This is the whole trick.

    intervals.sort((a,b) => a[1] - b[1])


    ✅ Step 2 — Track Previous End

    Take first interval.

    Then check overlap.

    Overlap condition:
        current.start < previous.end

    If overlap → remove one.

    Otherwise → keep it.
*/

/*
    🧩 Visual Example

    Input: [[1,2],[1,3],[2,4],[3,5]]

    After $04DSA.sorting by end:
        [1,2]
        [1,3]
        [2,4]
        [3,5]

    Process:
        Keep [1,2]
        [1,3] overlap ❌ remove
        [2,4] no overlap ✅ keep
        [3,5] overlap with [2,4]? No ✅ keep
*/

function eraseOverlapIntervals(intervals: number[][]): number {

    if (intervals.length === 0) return 0;

    // sort by ending time
    intervals.sort((a, b) => a[1] - b[1]);

    let removeCount = 0;
    let prevEnd = intervals[0][1];

    for (let i = 1; i < intervals.length; i++) {

        let start = intervals[i][0];
        let end = intervals[i][1];

        // overlap
        if (start < prevEnd) {
            removeCount++;
        } else {
            prevEnd = end;
        }
    }

    return removeCount;
}


// | Problem         | Sorting        | Goal           |
// | --------------- | -------------- | -------------- |
// | Merge Intervals | start time     | combine        |
// | Insert Interval | already sorted | merge new      |
// | Non-overlapping | end time       | remove minimum |
