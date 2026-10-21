// https://leetcode.com/problems/snakes-and-ladders/description/

// 🧠 when graph is { directed/undirected + unweighted } graph ==> BFS would be shortest path algorithm.
function snakesAndLadders(board: number[][]): number {
    const n = board.length;
    const size = n * n;

    // Step 1: Flatten the board into a 1D array
    const connection: number[] = new Array(size + 1).fill(-1);
    let node = 1;
    let leftToRight = true;

    for (let i = n - 1; i >= 0; i--) {
        if (leftToRight) {
            for (let j = 0; j < n; j++) {
                if (board[i][j] !== -1) {
                    connection[node] = board[i][j];
                }
                node++;
            }
        } else {
            for (let j = n - 1; j >= 0; j--) {
                if (board[i][j] !== -1) {
                    connection[node] = board[i][j];
                }
                node++;
            }
        }
        leftToRight = !leftToRight;
    }

    // Step 2: Build the graph
    const graph: Map<number, number[]> = new Map();
    for (let i = 1; i <= size; i++) {
        const neighbors: number[] = [];
        for (let move = 1; move <= 6; move++) {
            const nbr = i + move;
            if (nbr <= size) {
                if (connection[nbr] !== -1) {
                    neighbors.push(connection[nbr]); // Ladder or snake
                } else {
                    neighbors.push(nbr);
                }
            }
        }
        graph.set(i, neighbors);
    }

    // console.log('graph ---', graph);
    

    // Step 3: BFS to find the shortest path
    const visited: boolean[] = new Array(size + 1).fill(false);
    const queue: number[] = [];
    queue.push(1);
    visited[1] = true;

    let level = 0;

    while (queue.length > 0) {
        // console.log({level, queue});
        
        const sz = queue.length;
        for (let i = 0; i < sz; i++) {
            const node = queue.shift()!;
            if (node === size) return level;

            for (const nbr of graph.get(node) || []) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.push(nbr);
                }
            }
        }
        level++;
    }

    return -1; // If unreachable
}

const board = [
    [-1, -1, -1, -1, -1, -1],
    [-1, -1, -1, -1, -1, -1],
    [-1, -1, -1, -1, -1, -1],
    [-1, 35, -1, -1, 13, -1],
    [-1, -1, -1, -1, -1, -1],
    [-1, 15, -1, -1, -1, -1]
];
console.log(snakesAndLadders(board));
