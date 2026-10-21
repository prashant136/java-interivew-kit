/*
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
    The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

    You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

    Input: points = [[1,3],[-2,2]], k = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].


    Input: points = [[3,3],[5,-1],[-2,4]], k = 2
    Output: [[3,3],[-2,4]]
    Explanation: The answer [[-2,4],[3,3]] would also be accepted.
*/

// brute force approch -
/*
    Sorting arranges points like:
    closest → farthest

    So first k elements are the answer.

    points = [[3,3],[5,-1],[-2,4]]
    k = 2

    Step 1 — Compute distance
    [3,3]   → 9 + 9 = 18
    [5,-1]  → 25 + 1 = 26
    [-2,4]  → 4 + 16 = 20

    Step 2 — Sort
    [(18,[3,3]), (20,[-2,4]), (26,[5,-1])]

    Step 3 — Take first k
    [[3,3], [-2,4]]
*/
function kClosest__bruteForce(points: number[][], k: number): number[][] {

    // Step 1: sort based on distance
    points.sort((a, b) => {
        let distA = a[0] * a[0] + a[1] * a[1];
        let distB = b[0] * b[0] + b[1] * b[1];

        return distA - distB; // ascending order
    });

    return points.slice(0, k);
}

function kClosestPoint(points: number[][], k: number): number[][] {

    let heap: [number, number[]][] = [];

    function swap(i: number, j: number) {
        [heap[i], heap[j]] = [heap[j], heap[i]];
    }

    function heapifyUp() {
        let i = heap.length - 1;

        while (i > 0) {
            const parent = Math.floor((i - 1) / 2);
            if (heap[i][0] < heap[parent][0]) return;
            swap(i, parent);
            i = parent;
        }
    }

    function heapifyDown() {
        let i = 0;

        while (true) {
            let left = 2 * i + 1;
            let right = 2 * i + 2;
            let largest = i;

            if (left < heap.length && heap[left][0] > heap[largest][0])
                largest = left;

            if (right < heap.length && heap[right][0] > heap[largest][0])
                largest = right;

            if (largest === i) break;

            swap(largest, i);

            largest = i;
        }
    }

    function insert(val: [number, number[]]) {
        heap.push(val);
        heapifyUp();
    }

    function remove() {
        heap[0] = heap.pop()!;
        heapifyDown();
    }

    for (let point of points) {
        const distance = point[0] * point[0] + point[1] * point[1]
        insert([distance, point]);
        if (heap.length > k) {
            remove()
        }
    }

    return heap.map(x => x[1]);
}

// const points = [[3, 3], [5, -1], [-2, 4]]
// const k = 2

const points = [[89, 6], [-39, -4], [-13, 91], [97, -61], [1, 7], [-66, 69], [-51, 68], [82, -6], [-21, 44], [-58, -83], [-40, 73], [-88, -24]]
const k = 8;        // output - [[1,7],[-39,-4],[-21,44],[82,-6],[-40,73],[-51,68],[89,6],[-88,-24]]
console.log(kClosestPoint(points, k));
