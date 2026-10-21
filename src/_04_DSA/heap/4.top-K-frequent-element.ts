// https://leetcode.com/problems/top-k-frequent-elements/description/
/**
 Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order. 

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

    Input: nums = [1], k = 1
    Output: [1]
    
    Constraints:
    # 1 <= nums.length <= 105
    # -104 <= nums[i] <= 104
    # k is in the range [1, the number of unique elements in the array].
    # It is guaranteed that the answer is unique.
    
    👋 Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

// brute force approch - 
// 1️⃣ Count frequency of each number
// 2️⃣ Sort elements based on frequency
// 3️⃣ Pick top k elements
function topKFrequent__bruteForce(nums: number[], k: number): number[] {

    // Step 1: Frequency map
    const freq = new Map<number, number>();

    for (let num of nums) {
        freq.set(num, (freq.get(num) || 0) + 1);
    }

    // Step 2: Convert map to array
    const arr: [number, number][] = [];

    for (let [num, count] of freq) {
        arr.push([num, count]);
    }

    // Step 3: Sort by frequency (descending)
    arr.sort((a, b) => b[1] - a[1]);

    // Step 4: Take first k elements
    const result: number[] = [];

    for (let i = 0; i < k; i++) {
        result.push(arr[i][0]);
    }

    return result;
}

function topKFrequent(nums: number[], k: number): number[] {
    const hashmap = new Map<number, number>();
    for (let ele of nums) {
        hashmap.set(ele, (hashmap.get(ele) || 0) + 1);
    }

    const heap: [number, number][] = [];

    function swap(i: number, j: number) {
        [heap[i], heap[j]] = [heap[j], heap[i]]
    }

    function heapifyUp() {
        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);
            if (heap[i][1] >= heap[parent][1]) return;
            swap(i, parent);
            i = parent;
        }
    }

    function heapifyDown() {
        let i = 0;

        while (true) {
            let left = (2 * i) + 1;
            let right = (2 * i) + 2;
            let smallest = i;

            if (left < heap.length && heap[left][1] <= heap[smallest][1]) smallest = left;

            if (right < heap.length && heap[right][1] <= heap[smallest][1]) smallest = right;

            if (smallest === i) return;

            swap(smallest, i)

            i = smallest;
        }
    }

    function insert(val: [number, number]) {
        heap.push(val);
        heapifyUp();
    }

    function remove() {
        let lastElement = heap.pop()!;
        heap[0] = lastElement;
        heapifyDown();
    }

    for (let val of hashmap) {
        insert(val);
        if (heap.length > k) {
            remove();
        }
    }

    let result: number[] = [];
    for (let [number, count] of heap) {
        result.push(number);
    }
    return result;
};

const nums = [1, 1, 1, 2, 2, 3], k = 2;
console.log(topKFrequent(nums, k));

/*
   We need to know how many times each number appears.

    So first step:
    count frequency

    Example:
    nums = [1,1,1,2,2,3]
    k = 2

    Frequency Map:
    1 → 3
    2 → 2
    3 → 1

    we use Min Heap of size K

    ---------------------------------
    👀 Why MinHeap?

    Heap will store:   [number, frequency]

    Example $04DSA.heap:

    [ [2,2], [1,3] ]

    The smallest frequency stays at top.

    If $04DSA.heap size exceeds k, we remove the smallest frequency element.

    This way $04DSA.heap always keeps top K frequent elements.

    -------------------------------------------

    Example Walkthrough

    nums = [1,1,1,2,2,3]
    k = 2

    Frequency Map
    1 → 3
    2 → 2
    3 → 1

    ---------------------

    Insert [1,3]

    $04DSA.heap = [ [1,3] ]

    --------------------

    Insert [2,2]

    $04DSA.heap = [ [2,2], [1,3] ]

    --------------------

    Insert [3,1]

    $04DSA.heap = [ [3,1], [1,3], [2,2] ]

    size > k → remove smallest freq

    remove:

    [3,1]

    Final $04DSA.heap:

    [ [2,2], [1,3] ]

    Answer:

    [2,1]

    ---------------------

    ✅ Time Complexity -
    Frequency Map -  O(n)
    Heap operations -  O(n log k)
    Total: O(n log k)

    ✅ Space Complexity -
    Frequency Map -  O(n)
    Heap -  O(k)
    Total: O(n + k)
*/