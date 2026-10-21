/***
 * 
 * Cycle detection in $04DSA.graphs -
 *      In undirected graph - BFS, DFS
 *      In directed graph - DFS (some bratracking elments), BFS (topological sort)
 */

// https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
// Given an undirected graph with V vertices and E edges, represented as a 2D array edges[][],
//  where each entry edges[i] = [u, v] denotes an edge between vertices u and v, determine whether the graph contains a cycle or not.

// Examples:
// Input: V = 4, E = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]
// Output: true
 
// Input: V = 4, E = 3, edges[][] = [[0, 1], [1, 2], [2, 3]]
// Output: false
 
// Constraints:
// 1 ≤ V ≤ 105
// 1 ≤ E = edges.size() ≤ 105

// ------------------------------ dfs -------------------------------
// function isCycleDFS(V: number, edges: number[][]): boolean {
//     const adj: number[][] = Array.from({ length: V }, () => []);
//     const visited: boolean[] = new Array(V).fill(false);

//     // Build adjacency list for undirected
//     for (const [u, v] of edges) {
//         adj[u].push(v);
//         adj[v].push(u);
//     }

//     function dfs(node: number, parent: number): boolean {
//         visited[node] = true;

//         for (const neighbor of adj[node]) {
//             if (!visited[neighbor]) {
//                 if (dfs(neighbor, node)) return true;
//             } else if (visited[neighbor] === true && neighbor !== parent) {
//                 // detect cycle
//                 return true;
//             }
//         }
//         return false;
//     }

//     // Handle disconnected components
//     for (let i = 0; i < V; i++) {
//         if (!visited[i]) {
//             if (dfs(i, -1)) return true;
//         }
//     }

//     return false;
// }
// console.log(isCycleDFS(4, [[0, 1], [0, 2], [1, 2], [2, 3]])); // true
// console.log(isCycleDFS(4, [[0, 1], [1, 2], [2, 3]]));         // false


// ---------------------------------- bfs --------------------------------
function isCycleBFS(V: number, edges: number[][]): boolean {
    const adj: number[][] = Array.from({ length: V }, () => []);
    const visited: boolean[] = new Array(V).fill(false);

    // Build adjacency list for undirected
    for (const [u, v] of edges) {
        adj[u].push(v);
        adj[v].push(u);
    }

    // BFS function to detect cycle from a start node
    function bfs(start: number): boolean {
        const queue: [number, number][] = [[start, -1]]; // [currentNode, parent]

        visited[start] = true;

        while (queue.length > 0) {
            const [node, parent] = queue.shift()!;

            for (const neighbor of adj[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.push([neighbor, node]);
                } else if (visited[neighbor] === true && neighbor !== parent) {
                    // Found a visited node that is not the parent → cycle
                    return true;
                }
            }
        }

        return false;
    }

    // Check all disconnected components
    for (let i = 0; i < V; i++) {
        if (!visited[i]) {
            if (bfs(i)) return true;
        }
    }

    return false;
}

console.log(isCycleBFS(4, [[0, 1], [0, 2], [1, 2], [2, 3]])); // true
// console.log(isCycleBFS(4, [[0, 1], [1, 2], [2, 3]])); 
