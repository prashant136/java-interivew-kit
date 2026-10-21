function topKFrequent(words: string[], k: number): string[] {

    // Step 1: frequency map
    const freq = new Map<string, number>();
    for (let w of words) {
        freq.set(w, (freq.get(w) || 0) + 1);
    }

    // Min Heap
    const heap: [string, number][] = [];

    const swap = (i: number, j: number) => {
        [heap[i], heap[j]] = [heap[j], heap[i]];
    };

    // Custom comparison:
    // smaller freq OR same freq but lexicographically larger → smaller priority
    const compare = (a: [string, number], b: [string, number]) => {
        if (a[1] === b[1]) {
            return a[0] > b[0]; // lexicographically larger = worse
        }
        return a[1] < b[1]; // smaller freq = worse
    };

    const heapifyUp = () => {
        let i = heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (!compare(heap[i], heap[parent])) break;

            swap(i, parent);
            i = parent;
        }
    };

    const heapifyDown = () => {
        let i = 0;

        while (true) {
            let left = 2*i + 1;
            let right = 2*i + 2;
            let smallest = i;

            if (left < heap.length && compare(heap[left], heap[smallest]))
                smallest = left;

            if (right < heap.length && compare(heap[right], heap[smallest]))
                smallest = right;

            if (smallest === i) break;

            swap(i, smallest);
            i = smallest;
        }
    };

    const push = (val: [string, number]) => {
        heap.push(val);
        heapifyUp();
    };

    const pop = () => {
        const top = heap[0];
        heap[0] = heap.pop()!;
        heapifyDown();
        return top;
    };

    // Step 2: process words
    for (let [word, count] of freq) {
        push([word, count]);

        if (heap.length > k) {
            pop();
        }
    }

    // Step 3: extract result
    const result: string[] = [];

    while (heap.length > 0) {
        result.push(pop()[0]);
    }

    return result.reverse(); // because min $04DSA.heap
}


/*
    ---- Why Heap is Used ----

    👉 We don’t want to sort everything.

    Instead:

    Maintain only K best elements

    So we use:

    Min Heap of size K

    ---- Heap Logic (CRITICAL) ----

    Heap stores:

    [word, frequency]

    But comparator is tricky:

    Priority rules:
    Smaller frequency → remove first
    If same frequency → lexicographically larger removed first

    👉 Why?

    Because we want:

    Higher freq first
    Smaller word first (lexicographically)
*/