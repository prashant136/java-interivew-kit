// 👉 🐶 implementation in adjacency matrix.
// class Graph {
//     private matrix: number[][];
//     private size: number;

//     constructor(edges: number[][], isDirected: boolean = false) {
//         this.size = this.getMaxNode(edges);
//         this.matrix = Array.from({ length: this.size }, () => Array(this.size).fill(0));
//         this.buildMatrix(edges, isDirected);
//     }

//     private getMaxNode(edges: number[][]): number {
//         return Math.max(...edges.flat());
//     }

//     private buildMatrix(edges: number[][], isDirected: boolean): void {
//         for (const [u, v] of edges) {
//             this.matrix[u - 1][v - 1] = 1;
//             if (!isDirected) {
//                 this.matrix[v - 1][u - 1] = 1;
//             }
//         }
//     }

//     public printMatrix(): void {
//         console.log("Adjacency Matrix:");
//         for (const row of this.matrix) {
//             // console.log(row.join(" "));
//             console.log(row);
//         }
//     }
// }

// const edgeList: number[][] = [ [1,2], [2,3], [3,4], [4,2], [1,3] ];

// console.log("Directed Graph:");
// const directedGraph = new Graph(edgeList, true);
// directedGraph.printMatrix();

// console.log("\nUndirected Graph:");
// const undirectedGraph = new Graph(edgeList, false);
// undirectedGraph.printMatrix();



// 👉 👉 👉 🍀 🍀 🍀 implementation in adjacency list - (most useful in later questions)
class Graph {
    private adjList: Map<number, number[]>;
    private isDirected: boolean;

    constructor(edges: number[][], isDirected: boolean = false) {
        this.adjList = new Map();
        this.isDirected = isDirected;
        this.buildAdjList(edges);
    }

    private buildAdjList(edges: number[][]): void {
        for (const [u, v] of edges) {
            if (!this.adjList.has(u)) {
                this.adjList.set(u, []);
            }
            this.adjList.get(u)!.push(v);

            if (!this.isDirected) {
                if (!this.adjList.has(v)) {
                    this.adjList.set(v, []);
                }
                this.adjList.get(v)!.push(u);
            }
        }
    }
    // private dfs(source: number, destination: number, graph: Map<number, number[]>(), visited: Set<number>()): boolean {
    public printAdjList(): void {
        console.log("Adjacency List:");
        for (const [node, neighbors] of this.adjList) {
            console.log(`${node} -> ${neighbors.join(", ")}`);
        }
    }
}

const edgeList = [ [1,2], [2,3], [3,4], [4,2], [1,3] ];

console.log("Directed Graph:");
const directedGraph = new Graph(edgeList, true);
directedGraph.printAdjList();

console.log("\nUndirected Graph:");
const undirectedGraph = new Graph(edgeList, false);
undirectedGraph.printAdjList();
