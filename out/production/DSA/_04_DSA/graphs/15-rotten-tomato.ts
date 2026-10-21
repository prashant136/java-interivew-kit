// https://leetcode.com/problems/rotting-oranges/description/

function orangesRotting(grid: number[][]): number {
    const rows = grid.length;
    const cols = grid[0].length;
    const queue: [number, number][] = [];
    let freshOranges = 0;

    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];

    // Step 1: Count fresh oranges and enqueue initial rotten ones
    for (let i = 0; i < rows; i++) {
        for (let j = 0; j < cols; j++) {
            if (grid[i][j] === 2) {
                queue.push([i, j]);
            } else if (grid[i][j] === 1) {
                freshOranges++;
            }
        }
    }

    let minutes = 0;

    // Step 2: BFS to rot adjacent fresh oranges
    while (queue.length > 0 && freshOranges > 0) {
        let size = queue.length; // process current layer

        for (let i = 0; i < size; i++) {
            const [r, c] = queue.shift()!;

            for (let k = 0; k < 4; k++) {
                const nr = r + dx[k];
                const nc = c + dy[k];

                if (
                    nr >= 0 &&
                    nc >= 0 &&
                    nr < rows &&
                    nc < cols &&
                    grid[nr][nc] === 1
                ) {
                    grid[nr][nc] = 2;
                    freshOranges--;
                    queue.push([nr, nc]);
                }
            }
        }

        minutes++; // 1 layer processed = 1 minute
    }

    return freshOranges === 0 ? minutes : -1;
}

// console.log(orangesRotting([[2,1,1],[1,1,0],[0,1,1]]));  // 4
console.log(orangesRotting([[0, 1]]));  // 4

// const grid = [[2,1,1],[0,1,1],[1,0,1]];
// console.log(orangesRotting(grid));  // -1

// const grid = [[0,2]]
// console.log(orangesRotting(grid));  // 0