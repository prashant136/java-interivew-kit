/*
    🧠 Pattern Recognition (Very Important)
        Whenever you see:
            Insert a range
            Insert meeting booking
            Add new schedule
            Calendar event insertion
            Maintain non-overlapping intervals
*/

/*
    ✅ Phase 1 — Completely Before New Interval

    If interval ends before new interval starts:
        interval.end < new.start

    No overlap. ✅ Just push it.

    ✅ Phase 2 — Overlapping Intervals

    Overlap condition:
        interval.start <= new.end

    Now merge.

    Update new interval:
        new.start = min(new.start, interval.start)
        new.end = max(new.end, interval.end)

    We keep expanding new interval.

    ✅ Phase 3 — Completely After

    If interval starts after new interval:
        interval.start > new.end

    Push remaining intervals.
*/

// https://leetcode.com/problems/insert-interval/description/

function insertInterval(intervals: number[][], newInterval: number[]): number[][] {
    let result: number[][] = [];
    let i = 0;
    let n = intervals.length;

    // Phase 1: before overlap
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.push(intervals[i]);
        i++;
    }

    // Phase 2: merge overlap
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(
            newInterval[0],
            intervals[i][0]
        );

        newInterval[1] = Math.max(
            newInterval[1],
            intervals[i][1]
        );

        i++;
    }

    result.push(newInterval);

    // Phase 3: remaining
    while (i < n) {
        result.push(intervals[i]);
        i++;
    }

    return result;
}

const intervals = [[1,2], [3,5], [6,7], [8,10], [12,16]]
const newInterval = [4,9]
console.log(insertInterval(intervals, newInterval));    // [[ 1, 2 ], [ 3, 10 ], [ 12, 16 ]]
