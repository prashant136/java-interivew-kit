
// ---------------------------------- TOPOLOGICAL SORT - BFS -------------------------------
/**
✅ Steps: 
    1️⃣ Build Adjacency List & In-Degree Array
        - For every directed edge u → v, add v to adj[u].
        - Increment inDegree[v] by 1.

    2️⃣ Initialize Queue with Zero In-Degree Nodes
        - Push all nodes having inDegree[node] === 0 into the queue (they have no dependencies).

    3️⃣ Process Nodes in Queue
        - While the queue is not empty:
            - Pop a node from the front.
            - Add it to the result (topological order).
            - For each neighbor:
                - Decrease its in-degree by 1.
                - If its in-degree becomes 0, push it into the queue.

    4️⃣ Cycle Detection (Optional)
        - If the result does not include all nodes (i.e., result.length !== numNodes), the graph contains a cycle.

    🧠 Topological sort is only valid for DAGs.
    If at the end, some nodes still have non-zero in-degree → they are part of a cycle.
    
    👉 in a directed graph, if indegree of all nodes is not zero after topological sort -> then there is a cycle. (cycle detection)
*/



// function topologicalSortBFS(numNodes: number, edges: [number, number][]): number[] {
//     const adj: Map<number, number[]> = new Map();
//     const inDegree: number[] = new Array(numNodes).fill(0);

//     // Build adjacency list and compute in-degrees
//     for (const [u, v] of edges) {
//         if (!adj.has(u)) adj.set(u, []);
//         adj.get(u)!.push(v);
//         inDegree[v]++;
//     }

//     const queue: number[] = [];
//     const topo: number[] = [];

//     // Add nodes with in-degree 0 to the queue
//     for (let i = 0; i < numNodes; i++) {
//         if (inDegree[i] === 0) {
//             queue.push(i);
//         }
//     }

//     // Process nodes
//     while (queue.length > 0) {
//         const node = queue.shift()!;
//         topo.push(node);

//         for (const neighbor of adj.get(node) || []) {
//             inDegree[neighbor]--;
//             if (inDegree[neighbor] === 0) {
//                 queue.push(neighbor);
//             }
//         }
//     }

//     // If topological sort includes all nodes, return it
//     if (topo.length === numNodes) {
//         return topo;
//     } else {
//         // Graph has a cycle
//         return [];
//     }
// }
// const edges: [number, number][] = [
//     [5, 2],
//     [5, 0],
//     [4, 0],
//     [4, 1],
//     [2, 3],
//     [3, 1]
// ];
// const numNodes = 6;
// console.log(topologicalSortBFS(numNodes, edges));

// -------- for string --------
function topologicalSortBFS(edges: [string, string][]): string[] {
    const adj: Map<string, string[]> = new Map();
    const inDegree: Map<string, number> = new Map();
    const allNodes: Set<string> = new Set();

    // Step 1: Collect all nodes
    for (const [u, v] of edges) {
        allNodes.add(u);
        allNodes.add(v);
    }

    // Step 2: Build adjacency list & compute in-degrees
    for (const [u, v] of edges) {
        if (!adj.has(u)) adj.set(u, []);
        adj.get(u)!.push(v);
        inDegree.set(v, (inDegree.get(v) || 0) + 1);
    }

    // Step 3: Initialize queue with 0 in-degree nodes
    const queue: string[] = [];
    for (const node of allNodes) {
        if (!inDegree.has(node)) {
            queue.push(node);
        }
    }

    // Step 4: Process the queue
    const topo: string[] = [];
    while (queue.length > 0) {
        const node = queue.shift()!;
        topo.push(node);

        for (const neighbor of adj.get(node) || []) {
            inDegree.set(neighbor, inDegree.get(neighbor)! - 1);
            if (inDegree.get(neighbor) === 0) {
                queue.push(neighbor);
            }
        }
    }

    // Step 5: Check for cycle (optional)
    if (topo.length === allNodes.size) {
        return topo;
    } else {
        // Cycle detected
        return [];
    }
}
const edges: [string, string][] = [
    ["A", "B"],
    ["A", "D"],
    ["B", "C"],
    ["E", "D"],
    ["E", "F"],
    ["C", "F"],
];
console.log(topologicalSortBFS(edges));





// ---------------------------------- TOPOLOGICAL SORT - DFS ---------------------------------
/*
✅ Key Concepts:
    - Topological Sort is applicable only to Directed Acyclic Graphs (DAG).
    - In DFS-based Topological Sort, we:
        - Use a visited set to track visited nodes.
        - Use a $04DSA.stack to store nodes in post-order (after all descendants are visited).
        - Reverse the $04DSA.stack to get the topological order.
*/
// function topologicalSortDFS(edges: [string, string][]): string[] {
//     const adj: Map<string, string[]> = new Map();
//     const visited: Set<string> = new Set();
//     const ans: string[] = [];
//     const allNodes: Set<string> = new Set();

//     // Collect all nodes
//     for (const [u, v] of edges) {
//         allNodes.add(u);
//         allNodes.add(v);
//         if (!adj.has(u)) adj.set(u, []);
//         adj.get(u)!.push(v);
//     }

//     // DFS function
//     function dfs(node: string) {
//         visited.add(node);
//         for (const neighbor of adj.get(node) || []) {
//             if (!visited.has(neighbor)) {
//                 dfs(neighbor);
//             }
//         }
//         ans.unshift(node);
//     }

//     // Call DFS for all unvisited nodes
//     for (const node of allNodes) {
//         if (!visited.has(node)) {
//             dfs(node);
//         }
//     }

//     return ans;
// }
// const edges: [string, string][] = [
//     ["A", "B"],
//     ["A", "D"],
//     ["B", "C"],
//     ["E", "D"],
//     ["E", "F"],
//     ["C", "F"],
// ];

// console.log(topologicalSortDFS(edges)); 
