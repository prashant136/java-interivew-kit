
class Traversal {
    private adjacencyList: Map<number, number[]>;
    private isDirected: boolean;

    constructor(edgeList: number[][], isDirected: boolean) {
        this.adjacencyList = new Map();
        this.isDirected = isDirected;
        this.buildAdjacencyList(edgeList)
    }

    private buildAdjacencyList(edges: number[][]): void {
        for (const [u, v] of edges) {
            if (!this.adjacencyList.has(u)) {
                this.adjacencyList.set(u, []);
            }
            this.adjacencyList.get(u)!.push(v);

            if (!this.isDirected) {
                if (!this.adjacencyList.has(v)) {
                    this.adjacencyList.set(v, []);
                }
                this.adjacencyList.get(v)!.push(u);
            }
        }
    }

    public printAdjList(): void {
        console.log("Adjacency List:");
        for (const [node, neighbor] of this.adjacencyList) {
            console.log(`${node} -> ${neighbor}`);
        }
    }

    public bfs(startNode: number): void {
        const visited = new Set<number>();
        const queue: number[] = [startNode];

        while (queue.length > 0) {
            const node = queue.shift()!;
            if (!visited.has(node)) {
                console.log(node);
                visited.add(node);
                for (const neighbor of this.adjacencyList.get(node) || []) {
                    if (!visited.has(neighbor)) {
                        queue.push(neighbor);
                    }
                }
            }
        }
    }

    public dfs(startNode: number, visited: Set<number> = new Set()): void {
        if (visited.has(startNode)) return;
        console.log(startNode);
        visited.add(startNode);

        for (const neighbor of this.adjacencyList.get(startNode) || []) {
            this.dfs(neighbor, visited);
        }
    }
}

const edgeList = [[0, 1], [1, 4], [1, 2], [2, 3]];

console.log("Directed Graph:");
const directedGraph = new Traversal(edgeList, true);
directedGraph.printAdjList();
console.log("\nBFS traversal:");
directedGraph.bfs(0);
console.log("\nDFS traversal:");
directedGraph.dfs(0);

console.log("Undirected Graph:");
const undirectedGraph = new Traversal(edgeList, false);
undirectedGraph.printAdjList();
console.log("\nBFS traversal:");
directedGraph.bfs(0);
console.log("\nDFS traversal:");
directedGraph.dfs(0);