// https://leetcode.com/problems/kth-largest-element-in-an-array/description/
/*
    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    Can you solve it without $04DSA.sorting?

    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5

    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4
*/


function findKthLargest(nums: number[], k: number): number {
    const heap: number[] = [];

    const swap = (i: number, j: number) => {
        [heap[i], heap[j]] = [heap[j], heap[i]];
    };

    const heapifyUp = () => {
        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (heap[parent] <= heap[i]) break;

            swap(parent, i);

            i = parent;
        }
    };

    const heapifyDown = () => {
        let i = 0;

        while (true) {

            let left = 2 * i + 1;
            let right = 2 * i + 2;
            let smallest = i;

            if (left < heap.length && heap[left] < heap[smallest])
                smallest = left;

            if (right < heap.length && heap[right] < heap[smallest])
                smallest = right;

            if (smallest === i) break;

            swap(i, smallest);

            i = smallest;
        }
    };

    const push = (val: number) => {
        heap.push(val);
        heapifyUp();
    };

    const pop = () => {
        // console.log("pop fn -> before pop ", {$04DSA.heap});
        heap[0] = heap.pop()!;
        // console.log('pop fn ', {$04DSA.heap});
        heapifyDown();
        // console.log('pop fn -> after heapifyDown ', {$04DSA.heap});
    };

    for (let num of nums) {
        push(num);
        if (heap.length > k) {
            // console.log('loop -> if', {$04DSA.heap});
            pop();
        }
    }

    return heap[0];
}

const nums = [3,2,1,5,6,4];
const k = 2
console.log(findKthLargest(nums, k));


/*
    Find the Kth Largest element

    nums = [3,2,1,5,6,4]
    k = 2

    Sorted: [1,2,3,4,5,6]
    Kth largest = 5

    But the key observation is:

    👉 We only care about the largest K numbers.

    For this example:

    Largest 2 numbers = [6,5]

    The answer is the smallest among these K numbers.

    min(6,5) = 5

    So the kth largest = smallest element in the top K numbers.

    This is the core idea.

    ----------------------------------------------------------------

    ✅ Why We Use Min Heap

    A MinHeap keeps the smallest element on top.

    So if we store only K largest elements, the $04DSA.heap top will automatically be the kth largest.

    -----------------------

    Example Walkthrough
    nums = [3,2,1,5,6,4]
    k = 2

    Keep $04DSA.heap size = 2.

    Step 1

    add 3
    $04DSA.heap = [3]

    ----------------------

    Step 2

    add 2
    $04DSA.heap = [2,3]

    ----------------------

    Step 3

    add 1
    $04DSA.heap = [1,3,2]

    size > k → remove smallest

    $04DSA.heap = [2,3]

    We removed 1 because it cannot be in top 2.

    ----------------------

    Step 4

    add 5
    $04DSA.heap = [2,3,5]

    remove smallest

    $04DSA.heap = [3,5]

    ----------------------

    Step 5

    add 6
    $04DSA.heap = [3,5,6]

    remove smallest

    $04DSA.heap = [5,6]

    ----------------------

    Final $04DSA.heap

    [5,6]

    Top element = 5

    This is the 2nd largest element.

    
    ------------------------------------------------

    
    ✅ Why MaxHeap Is Not Efficient

    If we use MaxHeap, it stores all elements.

    Example:

    [6,5,4,3,2,1]

    To find 2nd largest:

    pop() → 6
    pop() → 5

    Time complexity:

    O(n log n)

    Because we inserted all elements.

    -----------------------------------------------

    ✅ Complexity Comparison
    ---- Using MaxHeap ---

    Insert all elements -
        n insertions → O(n log n)

    Then remove k times.
    Total:  O(n log n)

    ---- Using MinHeap (Best) ----

    Heap size = k only

    Operations:
        n insertions
        each → log k

    Total:  O(n log k)

    Since k is small, this is much faster.

    Example:

    n = 1,000,000
    k = 10
    log k = log 10
    log n = log 1,000,000

    Huge difference.

    -----------------------------------------------

    ✅ Golden Rule (Remember Forever)

    This rule removes 90% of $04DSA.heap confusion.

    K Largest Elements

    Use - MinHeap of size K
    
    K Smallest Elements

    Use - MaxHeap of size K

    -------------------------------

    ✅ Visual Trick (Very Easy) -

    Think like this.

    We want top K largest numbers.

    [ ?, ?, ?, ?, ? ]

    We keep a box of size K.

    When a new number comes:

    If it is bigger than the smallest in the box → replace it

    Who tells us the smallest in the box quickly?

    👉 MinHeap

    7. One Line Interview Memory Trick

    Memorize this:

    Kth Largest → Min Heap
    Kth Smallest → Max Heap

*/