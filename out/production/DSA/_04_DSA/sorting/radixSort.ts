/*
    🔢 What is Radix Sort?
        👉 Radix Sort sorts numbers digit by digit

        Instead of comparing numbers directly (like Quick Sort), it:
            Looks at digits (units, tens, hundreds…)
            Sorts step by step

    
    🧠 Core Idea (Intuition)

        👉 Think:
        “Sort numbers based on each digit, starting from least significant digit (LSD)”

    
    🔥 Example (Step-by-Step)
    Input:
    [170, 45, 75, 90, 802, 24, 2, 66]

    --------------------------------------
    
    🥇 Step 1 → Sort by units place
    Units digit:
    170 → 0
    45  → 5
    75  → 5
    90  → 0
    802 → 2
    24  → 4
    2   → 2
    66  → 6

    After $04DSA.sorting:
    [170, 90, 802, 2, 24, 45, 75, 66]

    ---------------------------------------

    🥈 Step 2 → Sort by tens place
    [170, 90, 802, 2, 24, 45, 75, 66]
    Units digit:
    170 → 7
    45  → 4
    75  → 7
    90  → 9
    802 → 0
    24  → 2
    2   → 0
    66  → 6

    After $04DSA.sorting:
    [802, 2, 24, 45, 66, 170, 75, 90]

    ---------------------------------------

    🥉 Step 3 → Sort by hundreds place
    Units digit:
    170 → 1
    45  → 0
    75  → 0
    90  → 0
    802 → 8
    24  → 0
    2   → 0
    66  → 0

    Final:
    [2, 24, 45, 66, 75, 90, 170, 802]
*/

function radixSort(arr: number[]): number[] {
    const max = Math.max(...arr);
    let exp = 1; // 1, 10, 100...

    while (Math.floor(max / exp) > 0) {
        countingSortByDigit(arr, exp);
        exp *= 10;
    }

    return arr;
}

function countingSortByDigit(arr: number[], exp: number): void {
    const output = new Array(arr.length).fill(0);
    const count = new Array(10).fill(0);

    // console.log('count 1', count);

    // count frequency of digits
    for (let num of arr) {
        const digit = Math.floor(num / exp) % 10;
        count[digit]++;
    }

    // prefix sum
    for (let i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // build output (RIGHT to LEFT for stability)
    for (let i = arr.length - 1; i >= 0; i--) {
        const digit = Math.floor(arr[i] / exp) % 10;
        output[count[digit] - 1] = arr[i];
        count[digit]--;
    }

    // copy back
    for (let i = 0; i < arr.length; i++) {
        arr[i] = output[i];
    }
}

const arr = [29, 83, 471, 36, 91, 8]
console.log(radixSort(arr));
