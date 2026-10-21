/*
    1️⃣ When to Recognize This Pattern?

    If the question involves:
        🗓️ Time ranges (start, end)
        📍 Ranges like [l, r]
        📊 Overlapping segments
        📅 Meeting rooms / bookings
        🔁 Combine or remove overlapping ranges
        🎯 Insert interval into sorted intervals
*/

/*
    2️⃣ Core Idea of the Pattern

    We are given intervals like: [1,3], [2,6], [8,10], [15,18]

    Some intervals overlap.

    Goal: 👉 Merge overlapping ones.

    Output: [1,6], [8,10], [15,18]

*/

/*
    🧠 The Golden Rule
    
    Two intervals overlap if:
        current.start <= previous.end

    If they overlap:  
        previous.end = max(previous.end, current.end)

    If they don’t:
        push previous to result
        move previous = current
*/

/*
    🚀 Step-by-Step Algorithm
    
    ✅ Step 1: Sort by start time:
        intervals.sort((a, b) => a[0] - b[0])

    Why?
        Because merging only works correctly in sorted order.

    ✅ Step 2: Start comparing

        Keep a prev interval.

        Loop from second interval:
            If overlapping → merge
            Else → push previous to result
*/

// Time Complexity: O(n log n) (because of $04DSA.sorting)
// Space Complexity: O(n)
function mergeInterval(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const results = [];

    let prev = intervals[0];      // keep track of prev interval
    for (let i = 1; i < intervals.length; i++) {
        const current = intervals[i];

        // Overlap condition
        if (prev[1] > current[0]) {
            // Merge
            prev[1] = Math.max(prev[1], current[1]);
        } else {
            results.push(prev);
            prev = current;
        }
    }
    results.push(prev);
    return results;
}

const intervals = [[1, 3], [2, 6], [8, 10], [15, 18]];
console.log(mergeInterval(intervals));      // [[ 1, 6 ], [ 8, 10 ], [ 15, 18 ]]
