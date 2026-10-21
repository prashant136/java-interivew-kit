// Each edge is a tuple: [destinationNode, weight]
type Edge = [number, number];
type Graph = Map<number, Edge[]>;

/**
 * ✅ Min-Heap-based Priority Queue
 * Used to always extract the node with the minimum distance
 */
class MinHeap {
    private heap: [number, number][] = []; // [distance, node]

    // Insert a new [distance, node] pair into the $04DSA.heap
    insert(item: [number, number]) {
        this.heap.push(item);
        this.bubbleUp();
    }

    // Remove and return the smallest distance node from the $04DSA.heap
    extractMin(): [number, number] | undefined {
        if (this.heap.length === 0) return undefined;

        const min = this.heap[0];
        const end = this.heap.pop()!;

        if (this.heap.length > 0) {
            this.heap[0] = end;
            this.bubbleDown();
        }

        return min;
    }

    isEmpty(): boolean {
        return this.heap.length === 0;
    }

    // Move the last inserted element to its correct position (up)
    private bubbleUp() {
        let index = this.heap.length - 1;
        const element = this.heap[index];

        while (index > 0) {
            const parentIdx = Math.floor((index - 1) / 2);
            const parent = this.heap[parentIdx];

            // If the parent is already smaller, stop
            if (element[0] >= parent[0]) break;

            // Swap with parent
            this.heap[index] = parent;
            this.heap[parentIdx] = element;
            index = parentIdx;
        }
    }

    // Move the root element down to its correct position
    private bubbleDown() {
        let index = 0;
        const length = this.heap.length;
        const element = this.heap[0];

        while (true) {
            let leftIdx = 2 * index + 1;
            let rightIdx = 2 * index + 2;
            let swapIdx: number | null = null;

            const left = this.heap[leftIdx];
            const right = this.heap[rightIdx];

            // Compare with left child
            if (leftIdx < length && left[0] < element[0]) {
                swapIdx = leftIdx;
            }

            // Compare with right child
            if (
                rightIdx < length &&
                ((swapIdx === null && right[0] < element[0]) ||
                    (swapIdx !== null && right[0] < this.heap[swapIdx][0]))
            ) {
                swapIdx = rightIdx;
            }

            if (swapIdx === null) break;

            // Swap and continue
            this.heap[index] = this.heap[swapIdx];
            this.heap[swapIdx] = element;
            index = swapIdx;
        }
    }
}

/**
 * ✅ Dijkstra's Algorithm
 * @param graph - A weighted graph represented as adjacency list
 * @param start - The starting node
 * @returns Map of shortest distances from start node to all other nodes
 */
function dijkstra(graph: Graph, start: number): Map<number, number> {
    const distances = new Map<number, number>(); // shortest distances
    const visited = new Set<number>(); // visited nodes
    const pq = new MinHeap(); // priority queue to process the next nearest node

    // Initialize all distances as Infinity
    for (const node of graph.keys()) {
        // console.log('node--', node);
        distances.set(node, Infinity);
    }

    // Distance to start node is 0
    distances.set(start, 0);
    pq.insert([0, start]); // start processing from source
    

    while (!pq.isEmpty()) {
        const [currentDist, currentNode] = pq.extractMin()!;
        console.log({ currentDist, currentNode });

        // Skip if already processed
        if (visited.has(currentNode)) continue;
        visited.add(currentNode);

        // console.log({visited});
        

        const neighbors = graph.get(currentNode) || [];

        console.log('neighbors:: ', neighbors);
        

        for (const [neighbor, weight] of neighbors) {
            const totalDist = currentDist + weight;

            console.log('totalDist >>', totalDist);
            console.log(`distances.get(${neighbor}) >>', ${distances.get(neighbor)}`);
            

            // If this path is shorter, update and enqueue the neighbor
            if (totalDist < (distances.get(neighbor) || Infinity)) {
                distances.set(neighbor, totalDist);
                pq.insert([totalDist, neighbor]);
            }
        }
    }

    return distances;
}

// Sample graph:
// 0 --(4)--> 1
// 0 --(2)--> 2
// 1 --(1)--> 2, 1 --(5)--> 3
// 2 --(8)--> 3, 2 --(10)--> 4
// 3 --(2)--> 4

const graph: Graph = new Map([
    [0, [[1, 4], [2, 2]]],
    [1, [[2, 1], [3, 5]]],
    [2, [[3, 8], [4, 10]]],
    [3, [[4, 2]]],
    [4, []]
]);

const result = dijkstra(graph, 0);
console.log(Object.fromEntries(result));
// Output: { '0': 0, '1': 4, '2': 2, '3': 9, '4': 11 }
