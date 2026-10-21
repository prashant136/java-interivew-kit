
/*
    1. Brute Force Idea (Very Simple)
        1️⃣ Store all numbers in an array
        2️⃣ Sort the array every time we need median
        3️⃣ Return middle element

    👉 “Whenever someone asks median → sort and pick middle”

    
    Time complexity -
        10^5 operations - O(n log n) 
        we need O(n log k)  ✅
*/


/*

    Time Complexity Mapping (MOST IMPORTANT)
    
    Memorize this table 👇
    | Input Size (n)       | Allowed Complexity       |
    | -------------------- | ------------------------ |
    | n ≤ 10               | O(n!) brute force        |
    | n ≤ 20               | O(2^n) recursion         |
    | n ≤ 100              | O(n³)                    |
    | n ≤ 1,000            | O(n²)                    |
    | n ≤ 10⁵              | O(n log n) or O(n)       |
    | n ≤ 10⁶              | O(n) only                |

    
    Quick Memory Trick -
        Big n → faster algorithm needed
        Small n → brute force allowed   
*/

// ----- brute force approch ------
// class MedianFinder {

//     arr: number[] = [];

//     addNum(num: number): void {
//         this.arr.push(num);
//     }

//     findMedian(): number {
//         // sort array every time
//         this.arr.sort((a, b) => a - b);

//         let n = this.arr.length;

//         // odd length
//         if (n % 2 === 1) {
//             return this.arr[Math.floor(n / 2)];
//         }

//         // even length
//         return (this.arr[n/2 - 1] + this.arr[n/2]) / 2;
//     }
// }



/*
    1. First Think (Very Important)

        We want:

        Keep adding numbers
        → Always return median

        Problem:

        👉 Data is coming continuously (stream)
        👉 We cannot sort every time ❌

-----------------------------------

    2. Key Insight (Game-Changer)

        Instead of storing all numbers and $04DSA.sorting…

        👉 Split numbers into 2 halves

        LEFT SIDE (smaller numbers)
        RIGHT SIDE (larger numbers)

        Example:
        [1,2,3,4,5]

        Split:
        [1,2]  |  [3,4,5]

    👉 Median lies between these two halves

----------------------------------

    3. What Do We Need From Each Side?
        We don’t need full sorted arrays.

        We only need:
            Left side  → biggest number
            Right side → smallest number

        Because:
            👉 Median is either:
                one of these
                or average of both

-----------------------------------

    4. Which Data Structure Gives This?
    👉 Heap

    5. Final Structure
    MaxHeap (left)     |    MinHeap (right)

    smaller nums     |      larger nums
    top = max        |      top = min

👉 This is the core concept

-----------------------------------

    6. Golden Rules (Must Remember)
        Rule 1 — Size Balance
        |left - right| ≤ 1

        👉 both heaps almost equal

        Rule 2 — Order Property
        maxHeap.top ≤ minHeap.top

        👉 left side always smaller

----------------------------------

    7. How We Add Numbers (Super Important)

        Always follow this 3-step flow:

        Step 1 — Add to MaxHeap
            push num → maxHeap

        Step 2 — Move top → MinHeap
            move maxHeap.top → minHeap  
            👉 ensures order is correct

        Step 3 — Balance
            If sizes break:
                move minHeap.top → maxHeap
            👉 keep size balanced
*/
class MedianFinder {

    maxHeap: number[] = []; // left (max $04DSA.heap)
    minHeap: number[] = []; // right (min $04DSA.heap)

    // ---------- MAX HEAP ----------
    maxPush(val: number) {
        this.maxHeap.push(val);
        let i = this.maxHeap.length - 1;

        while (i > 0) {
            let p = Math.floor((i - 1) / 2);
            if (this.maxHeap[p] >= this.maxHeap[i]) break;

            [this.maxHeap[p], this.maxHeap[i]] = [this.maxHeap[i], this.maxHeap[p]];
            i = p;
        }
    }

    maxPop(): number {
        const top = this.maxHeap[0];
        this.maxHeap[0] = this.maxHeap.pop()!;
        let i = 0;

        while (true) {
            let l = 2*i + 1, r = 2*i + 2, largest = i;

            if (l < this.maxHeap.length && this.maxHeap[l] > this.maxHeap[largest]) largest = l;
            if (r < this.maxHeap.length && this.maxHeap[r] > this.maxHeap[largest]) largest = r;

            if (largest === i) break;

            [this.maxHeap[i], this.maxHeap[largest]] = [this.maxHeap[largest], this.maxHeap[i]];
            i = largest;
        }

        return top;
    }

    // ---------- MIN HEAP ----------
    minPush(val: number) {
        this.minHeap.push(val);
        let i = this.minHeap.length - 1;

        while (i > 0) {
            let p = Math.floor((i - 1) / 2);
            if (this.minHeap[p] <= this.minHeap[i]) break;

            [this.minHeap[p], this.minHeap[i]] = [this.minHeap[i], this.minHeap[p]];
            i = p;
        }
    }

    minPop(): number {
        const top = this.minHeap[0];
        this.minHeap[0] = this.minHeap.pop()!;
        let i = 0;

        while (true) {
            let l = 2*i + 1, r = 2*i + 2, smallest = i;

            if (l < this.minHeap.length && this.minHeap[l] < this.minHeap[smallest]) smallest = l;
            if (r < this.minHeap.length && this.minHeap[r] < this.minHeap[smallest]) smallest = r;

            if (smallest === i) break;

            [this.minHeap[i], this.minHeap[smallest]] = [this.minHeap[smallest], this.minHeap[i]];
            i = smallest;
        }

        return top;
    }

    addNum(num: number): void {

        // Step 1: add to maxHeap
        this.maxPush(num);

        // Step 2: move largest of left → right
        this.minPush(this.maxPop());

        // Step 3: balance sizes
        if (this.minHeap.length > this.maxHeap.length) {
            this.maxPush(this.minPop());
        }
    }

    findMedian(): number {

        if (this.maxHeap.length > this.minHeap.length) {
            return this.maxHeap[0];
        }

        return (this.maxHeap[0] + this.minHeap[0]) / 2;
    }
}

const arr = [1,2,3];
