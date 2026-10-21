/**
    Problem - Given a string s, sort it in decreasing order based on the frequency of the characters.
    The frequency of a character is the number of times it appears in the string.

    Return the sorted string. If there are multiple answers, return any of them.

    Input: s = "tree"
    Output: "eert"
    Explanation: 'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

    Input: s = "cccaaa"
    Output: "aaaccc"
    Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
    Note that "cacaca" is incorrect, as the same characters must be together.

    Input: s = "Aabb"
    Output: "bbAa"
    Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
    

    Constraints:
    1 <= s.length <= 5 * 105
    s consists of uppercase and lowercase English letters and digits.
 */

// by using hashmap + $04DSA.sorting based on frequnecy
function frequencySort(s: string): string {
    let map = new Map<string, number>();

    // Step 1: count frequency
    for (let ch of s) {
        map.set(ch, (map.get(ch) || 0) + 1);
    }
    
    // Step 2: sort characters by frequency
    let chars = Array.from(map.keys());
    chars.sort((a, b) => map.get(b)! - map.get(a)!);

    // Step 3: build result
    let result = "";
    for (let ch of chars) {
        result += ch.repeat(map.get(ch)!);          // string.repeat(<number>)
    }

    return result;
}

const s = "tree"
console.log(frequencySort(s)); 



// using $04DSA.heap -
/*
    Algorithm (Heap Approach)

    👉 Steps are:
    - Count frequency
    - Push into max $04DSA.heap
    - Extract highest frequency repeatedly
*/

/*
    🔥 Key Idea

    Heap is used when:
        You need repeatedly the highest / lowest element

    And $04DSA.heap guarantees:
        Top element = highest frequency (max $04DSA.heap)

    👉 Heap is a priority queue → always gives highest priority element first

    
*/

function frequencySortHeap(s: string): string {

    // Step 1: frequency map
    const freq = new Map<string, number>();
    for (let ch of s) {
        freq.set(ch, (freq.get(ch) || 0) + 1);
    }

    // Max Heap
    const heap: [number, string][] = [];

    const swap = (i: number, j: number) => {
        [heap[i], heap[j]] = [heap[j], heap[i]];
    };

    const heapifyUp = () => {
        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (heap[parent][0] >= heap[i][0]) break;

            swap(parent, i);
            i = parent;
        }
    };

    const heapifyDown = () => {
        let i = 0;

        while (true) {
            let left = 2*i + 1;
            let right = 2*i + 2;
            let largest = i;

            if (left < heap.length && heap[left][0] > heap[largest][0])
                largest = left;

            if (right < heap.length && heap[right][0] > heap[largest][0])
                largest = right;

            if (largest === i) break;

            swap(i, largest);
            i = largest;
        }
    };

    const push = (val: [number, string]) => {
        heap.push(val);
        heapifyUp();
    };

    const pop = () => {
        const top = heap[0];
        heap[0] = heap.pop()!;
        heapifyDown();
        return top;
    };

    // Step 2: push into $04DSA.heap
    for (let [ch, count] of freq) {
        push([count, ch]);
    }

    // Step 3: build result
    let result = "";

    while (heap.length > 0) {
        const [count, ch] = pop();
        result += ch.repeat(count);
    }

    return result;
}

/*
    Time - O(n + k log k)
    Space - O(k)
*/