/*
    🚀 Problem Description ::

    You are given an array of meeting time intervals:

    intervals[i] = [starti, endi]

    Each interval represents the start and end time of a meeting.

    You need to determine:

    Can a person attend all meetings?

    A person can attend all meetings only if no meetings overlap.

    Two meetings overlap if:

    meeting1.end > meeting2.start
    ✅ Example 1
    Input:
    [[0,30],[5,10],[15,20]]

    Output:
    false

    Why?

    Meeting 1 : 0 ----------- 30
    Meeting 2 :      5 ---10

    Overlap exists.

    So person cannot attend both.

    ✅ Example 2
    Input:
    [[7,10],[2,4]]

    Output:
    true

    After arranging:

    [2,4]
    [7,10]

    No overlap.

    Person can attend both.

    Constraints

    0 <= intervals.length <= 10^4

    start < end

    Time values up to 10^6
*/


/*

    🧠 First Thought (Brute Force) -
    Check every meeting with every other meeting.

        for every i
            for every j
                check overlap

        Time Complexity: O(n²)

    Interviewers expect better.

    -------------------------------------------------------------------------
    🚀 Key Intuition (MOST IMPORTANT)

    Imagine checking your calendar.

    What do you do?

    You naturally look at meetings in time order.

    Example:

    [7,10]
    [2,4]
    [8,12]

    Messy order.

    So first step:

    ✅ Arrange chronologically.
    -------------------------------------

    ⭐ Golden Insight

    If meetings are sorted by start time:

    Only adjacent meetings can overlap.

    You don't need to check all pairs.

    Because:

    If meeting A doesn't overlap B,
    then it cannot overlap later meetings either.

    This reduces problem complexity drastically.

    🔥 Approach (Interview Perfect)
    Step 1 — Sort by Start Time
    intervals.sort((a,b) => a[0] - b[0])

    Timeline becomes clean.

    Step 2 — Compare Adjacent Meetings

    Check:
    previous.end > current.start

    If true → overlap exists.

    Return false immediately.

    Step 3 — If No Conflict Found

    Return true.
*/

function canAttendMeetings(intervals: number[][]): boolean {

    // Step 1: sort by start time
    intervals.sort((a, b) => a[0] - b[0]);

    // Step 2: check overlap
    for (let i = 1; i < intervals.length; i++) {

        let prevEnd = intervals[i - 1][1];
        let currStart = intervals[i][0];

        if (currStart < prevEnd) {
            return false;
        }
    }

    return true;
}

// ⏱ Complexity
// Time - O(n log n)
// Space - O(1)

const arr = [[7,10],[2,4],[8,12]];
console.log(canAttendMeetings(arr));
