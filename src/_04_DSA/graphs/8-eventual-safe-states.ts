// https://www.geeksforgeeks.org/problems/eventual-safe-states/1

// function eventualSafeNodes(V: number, adj: number[][]): number[] {
//     const visited: number[] = new Array(V).fill(0);
//     const currentPath: number[] = new Array(V).fill(0);

//     function dfs(node: number): boolean {
//         visited[node] = 1;
//         currentPath[node] = 1;

//         for (const nbr of adj[node]) {
//             if (!visited[nbr]) {
//                 if(dfs(nbr)) return true;
//             } else if (currentPath[nbr] === 1) {
//                 // Found a back edge — cycle detected
//                 return true;
//             }
//         }

//         currentPath[node] = 0; // backtrack
//         return false;
//     }

//     for (let i = 0; i < V; i++) {
//         if (!visited[i]) {
//             dfs(i);
//         }
//     }

//     const res: number[] = [];
//     for (let i = 0; i < V; i++) {
//         if (currentPath[i] === 0) {
//             res.push(i);
//         }
//     }

//     return res;
// }

// const V = 7;
// const adj = [
//     [1, 2],
//     [2, 3],
//     [5],
//     [0],
//     [5],
//     [],
//     []
// ];
// console.log(eventualSafeNodes(V, adj)); // Output: [2,4,5,6]


function eventualSafeNodes(edges: [string, string][], nodes: string[]): string[] {
    const adj: Record<string, string[]> = {};
    const visited: Set<string> = new Set();
    const currentPath: Set<string> = new Set();

    // Build adjacency list
    for (const [u, v] of edges) {
        if (!adj[u]) adj[u] = [];
        adj[u].push(v);
    }

    // Ensure all nodes are represented in adj, including disconnected ones
    for (const node of nodes) {
        if (!adj[node]) adj[node] = [];
    }

    // adj {
    //     A: [ "B", "D" ],
    //     B: [ "C", "D" ],
    //     C: [ "A" ],
    //     D: [ "E" ],
    //     F: [ "E" ],
    //     E: [],
    //     G: []
    // }
    
    function dfs(node: string): boolean {
        visited.add(node);
        currentPath.add(node);

        for (const neighbor of adj[node]) {
            if (!visited.has(neighbor)) {
                if (dfs(neighbor)) return true;
            } else if (currentPath.has(neighbor)) {
                return true; // cycle found
            }
        }

        currentPath.delete(node); // backtrack
        return false;
    }

    // Run DFS on all nodes
    for (const node of nodes) {
        if (!visited.has(node)) dfs(node);
    }

    // ✌️ Collect safe nodes
    const safeNodes: string[] = [];
    for (const node of nodes) {
        if (!currentPath.has(node)) safeNodes.push(node);
    }

    return safeNodes.sort();
}

// ✅ Include all nodes explicitly (even disconnected ones like 'G')
const nodes = ['A', 'B', 'C', 'D', 'E', 'F', 'G'];
// const edges: [string, string][] = [
//     ['A', 'B'],
//     ['B', 'C'],
//     ['C', 'A'],
//     ['C', 'D'],
// ];
const edges: [string, string][] = [
    ['A', 'B'],
    ['A', 'D'],
    ['B', 'C'],
    ['B', 'D'],
    ['C', 'A'],
    ['D', 'E'],
    ['F', 'E'],
    // G has no edges, handled via nodes[]
];

console.log(eventualSafeNodes(edges, nodes));
