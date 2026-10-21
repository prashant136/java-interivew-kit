// https://leetcode.com/problems/find-if-path-exists-in-graph/description/

/**
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
    The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. 
    Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

    You want to determine if there is a valid path that exists from vertex source to vertex destination.

    Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

    Example 1:-
    0 ----- 2
     \     /
      \   /
        1
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2

    Example 2:-
        0                 5
       / \      and      / \
      1   2             3 - 4  
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.
 */

// Approach:-
// 1. Create an adjacency list to represent the graph.
// 2. Use either BFS or DFS to traverse the graph from the source node.
// 3. Keep track of visited nodes to avoid cycles.
// 4. If you reach the destination node during traversal, return true.
// 5. If the traversal ends without reaching the destination, return false.
// 6. The time complexity is O(V + E), where V is the number of vertices and E is the number of edges.
// 7. The space complexity is O(V) for the visited set and the adjacency list.
// 8. The algorithm is efficient for large $04DSA.graphs and can handle cycles and disconnected components.
class Graph {
    private buildGraph(edges: number[][], adjList: Map<number, number[]>): Map<number, number[]> {
        for (const [u, v] of edges) {
            if (!adjList.has(u)) adjList.set(u, []);
            if (!adjList.has(v)) adjList.set(v, []);
            adjList.get(u)!.push(v);
            adjList.get(v)!.push(u);  // for undirected graph
        }
        return adjList;
    }

    private dfs(source: number, destination: number, graph: Map<number, number[]>, visited: Set<number>): boolean {
        if (source === destination) return true;
        if (visited.has(source)) return false;

        visited.add(source);
        for (const neighbor of graph.get(source)!) {
            if (this.dfs(neighbor, destination, graph, visited)) {
                return true;
            }
        }
        return false;
    }

    private bfs(source: number, destination: number, graph: Map<number, number[]>, visited: Set<number>): boolean {
        const queue: number[] = [source];
        visited.add(source);

        while (queue.length > 0) {
            const node = queue.shift()!;
            if (node === destination) return true;

            for (const neighbor of graph.get(node) || []) {
                if (!visited.has(neighbor)) {
                    visited.add(neighbor);
                    queue.push(neighbor);
                }
            }
        }
        return false;
    }

    public validPath(n: number, edges: number[][], source: number, destination: number): boolean {
        let graph = this.buildGraph(edges, new Map<number, number[]>());
        let visited = new Set<number>();
        console.log(graph);
        
        // return this.dfs(source, destination, graph, visited);    // using DFS 😎
        return this.bfs(source, destination, graph, visited);   // using BFS 😎
    }
}

const n = 3;
const edges = [[0,1],[1,2],[2,0]];
const source = 0;
const destination = 2;
const graph = new Graph();
console.log(graph.validPath(n, edges, 0, 2));

// [[0,7],[0,8],[6,1],[2,0],[0,4],[5,8],[4,7],[1,3],[3,5],[6,5]]



// java solution dfs - 
// class Solution {
//     public boolean validPath(int n, int[][] edges, int source, int destination) {
//         Map<Integer, List<Integer>> graph = new HashMap<>();
//         for (int[] edge : edges) {
//             int u = edge[0];
//             int v = edge[1];
//             graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//             graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
//         }
        
//         Set<Integer> visited = new HashSet<>();
//         return dfs(source, destination, graph, visited);
//     }
    
//     private boolean dfs(int node, int destination, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
//         if (node == destination) {
//             return true;
//         }
//         visited.add(node);
//         for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
//             if (!visited.contains(neighbor)) {
//                 if (dfs(neighbor, destination, graph, visited)) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
// }

// java solution bfs -
// class Solution {
//     public boolean validPath(int n, int[][] edges, int source, int destination) {
//         Map<Integer, List<Integer>> graph = new HashMap<>();
//         for (int[] edge : edges) {
//             int u = edge[0];
//             int v = edge[1];
//             graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
//             graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
//         }
        
//         Queue<Integer> queue = new LinkedList<>();
//         Set<Integer> visited = new HashSet<>();
        
//         queue.offer(source);
//         visited.add(source);
        
//         while (!queue.isEmpty()) {
//             int node = queue.poll();
//             if (node == destination) {
//                 return true;
//             }
//             List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
//             for (int neighbor : neighbors) {
//                 if (!visited.contains(neighbor)) {
//                     visited.add(neighbor);
//                     queue.offer(neighbor);
//                 }
//             }
//         }
        
//         return false;
//     }
// }

// java solution - union find
// class Solution {
//     private int[] parent;
//     private int[] rank;
    
//     public boolean validPath(int n, int[][] edges, int source, int destination) {
//         parent = new int[n];
//         rank = new int[n];
        
//         // Initialize parent pointers and ranks
//         for (int i = 0; i < n; i++) {
//             parent[i] = i;
//             rank[i] = 1;
//         }
        
//         // Union-find operations based on given edges
//         for (int[] edge : edges) {
//             union(edge[0], edge[1]);
//         }
        
//         // Check if source and destination belong to the same set
//         return find(source) == find(destination);
//     }
    
//     private int find(int x) {
//         if (parent[x] != x) {
//             parent[x] = find(parent[x]); // Path compression
//         }
//         return parent[x];
//     }
    
//     private void union(int x, int y) {
//         int rootX = find(x);
//         int rootY = find(y);
        
//         if (rootX != rootY) {
//             if (rank[rootX] > rank[rootY]) {
//                 parent[rootY] = rootX;
//             } else if (rank[rootX] < rank[rootY]) {
//                 parent[rootX] = rootY;
//             } else {
//                 parent[rootY] = rootX;
//                 rank[rootX]++;
//             }
//         }
//     }
// }