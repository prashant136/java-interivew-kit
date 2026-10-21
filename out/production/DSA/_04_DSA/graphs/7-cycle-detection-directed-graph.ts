
/**
 * 🧠 Cycle detection in directed $04DSA.graphs using DFS and the concept of visited + current path works
 * because of how direction affects the flow of nodes.

  🔁 Why do we need "visited" and "current path"?
    Imagine you're walking through a maze of one-way paths (directed edges).
    You want to check if you ever end up back on your own path — that would be a cycle.
 */

function hasCycleDirected(V: number, edges: number[][]): boolean {
    const adj: number[][] = Array.from({ length: V }, () => []);
    const visited: boolean[] = new Array(V).fill(false);
    const currentPath: boolean[] = new Array(V).fill(false); // recursion $04DSA.stack

    // Build adjacency list
    for (const [u, v] of edges) {
        adj[u].push(v);
    }

    // DFS to detect cycle
    function dfs(node: number): boolean {
        visited[node] = true;
        currentPath[node] = true;

        for (const neighbor of adj[node]) {
            if (!visited[neighbor]) {
                if (dfs(neighbor)) return true;
            } else if (currentPath[neighbor]) {
                // Back edge found → cycle
                return true;
            }
        }

        currentPath[node] = false; // backtrack
        // console.log('currentPath', currentPath);
        
        return false;
    }

    // Check all components
    for (let i = 0; i < V; i++) {
        if (!visited[i]) {
            if (dfs(i)) return true;
        }
    }

    return false;
}
// console.log(hasCycleDirected(4, [[0, 1], [0, 2], [1, 2], [2, 0], [2, 3]])); // true
// console.log(hasCycleDirected(4, [[0, 1], [0, 2], [1, 2], [2, 3]]));        // false

// ✌️ this works for both numbers and strings.
function hasCycle(edges: [string, string][]): boolean {
    const adj: Record<string, string[]> = {};
    const visited: Set<string> = new Set();
    const path: Set<string> = new Set();

    // Build adjacency list
    for (const [u, v] of edges) {
        if (!adj[u]) adj[u] = [];
        adj[u].push(v);
    }
    
    function dfs(node: string): boolean {
        visited.add(node);
        path.add(node);

        for (const neighbor of adj[node] || []) {
            if(!visited.has(neighbor)) {
                if (dfs(neighbor)) return true;
            } else if (path.has(neighbor)) {
                // cycle found
                return true;
            }
        }
        path.delete(node); // backtrack
        console.log(path);
        
        return false;
    }

    // Run DFS on all nodes
    const allNodes = new Set<string>();
    for (const [u, v] of edges) {
        allNodes.add(u);
        allNodes.add(v);
    }

    for (const node of allNodes) {
        if (!visited.has(node)) {
            if (dfs(node)) return true;
        }
    }

    return false;
}

const edges: [string, string][] = [
    ['A', 'B'],
    ['B', 'C'],
    ['C', 'D'],
    ['C', 'E'],
    ['D', 'B'], // Cycle: B -> C -> D -> B
];

console.log(hasCycle(edges));