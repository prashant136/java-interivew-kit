/*
    📌 Problem — Meeting Rooms II (LeetCode 253)

    You are given an array of meeting time intervals where:

    intervals[i] = [start_i, end_i]

    Each interval represents a meeting’s start and end time (with start_i < end_i).

    ❓ Task

    Determine the minimum number of conference rooms required so that all meetings can take place without any overlap.

    In other words:

    You need as many rooms as the maximum number of meetings happening at the same time.

    📌 Example 1
    Input: [[0,30],[5,10],[15,20]]
    Output: 2

    Explanation:

    Meeting1: 0–30
    Meeting2: 5–10
    Meeting3: 15–20

    At time 5–10, meetings 1 and 2 overlap → need at least 2 rooms.

    📌 Example 2
    Input: [[7,10],[2,4]]
    Output: 1

    These don’t overlap, so 1 room is enough.
*/


/*
    👉 So your goal is to compute the maximum number of meetings overlapping at the same time.

    ✅ Approach 1 — Min-Heap (Priority Queue)
    🎯 Intuition
        Sort all meetings by start time.
        Use a min-$04DSA.heap to track the end times of meetings currently using rooms.
        If a new meeting starts after or when the earliest meeting ends → reuse that room (pop $04DSA.heap).
        Else → need a new room (push new meeting end time).
        The $04DSA.heap’s size at any point is the number of rooms in use.
*/

function minMeetingRooms_approchOne(intervals: number[][]): number {
    if (intervals.length === 0) return 0;

    // sort by start time
    intervals.sort((a, b) => a[0] - b[0]);

    // min $04DSA.heap for end times
    let heap: number[] = [];

    // add first meeting’s end time
    heap.push(intervals[0][1]);

    for (let i = 1; i < intervals.length; i++) {
        const [start, end] = intervals[i];

        // if earliest meeting ended before current starts
        if (heap.length > 0 && start >= heap[0]) {
            heap.shift(); // reuse that room
        }

        // assign current meeting to a room
        heap.push(end);

        // maintain $04DSA.heap sorted by earliest end time
        heap.sort((a, b) => a - b);
    }

    return heap.length; 
}

/*
    ✅ Approach 2 — Two Pointers on Sorted Starts and Ends
    🎯 Intuition

    Instead of a $04DSA.heap:
        Extract all start times → sorted
        Extract all end times → sorted

    Use two pointers:
        if start[i] < end[j] → need a new room
        else → a room freed, move j
*/

function minMeetingRooms_approachTwo(intervals: number[][]): number {
    let starts = intervals.map(x => x[0]).sort((a, b) => a - b);
    let ends = intervals.map(x => x[1]).sort((a, b) => a - b);

    let rooms = 0, endPtr = 0;

    for (let start of starts) {
        if (start < ends[endPtr]) {
            rooms++;
        } else {
            endPtr++;
        }
    }

    return rooms;
}

const intervals = [[0, 30], [5, 10], [15, 20]];
console.log(minMeetingRooms_approchOne(intervals)); // Output: 2
console.log(minMeetingRooms_approachTwo(intervals)); // Output: 2
// strt = [0, 5, 15]
// end = [30, 10, 20]