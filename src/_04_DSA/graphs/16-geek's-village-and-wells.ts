// https://www.geeksforgeeks.org/problems/geeks-village-and-wells--170647/1

// ✅ Problem Summary:
// You are given a grid with:
//      'W' = Wells (starting points),
//      'H' = Houses (targets),
//      '.' = Empty cells (traversable),
//      'N' = Non-traversable areas.
//  You need to calculate the shortest time from each house to the nearest well. Each step takes 2 units of time.

function chefAndWells(n: number, m: number, grid: string[][]): number[][] {
    // const directions = [
    //     [1, 0], [-1, 0], [0, 1], [0, -1]
    // ];
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];

    const result: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    const queue: [number, number][] = [];

    // Step 1: Push all wells into the queue
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (grid[i][j] === 'W') {
                queue.push([i, j]);
            }
        }
    }

    let counter = 1;

    // Step 2: Multi-source BFS (well could be multiple)
    while (queue.length > 0) {
        const size = queue.length;

        for (let s = 0; s < size; s++) {
            const [row, col] = queue.shift()!;

            for (let k = 0; k < 4; k++) {
                const newRow = row + dx[k];
                const newCol = col + dy[k];

                if (
                    newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m &&
                    (grid[newRow][newCol] === 'H' || grid[newRow][newCol] === '.')
                ) {
                    if (grid[newRow][newCol] === 'H') {
                        result[newRow][newCol] = 2 * counter;
                    }

                    // Mark visited
                    grid[newRow][newCol] = 'X';
                    queue.push([newRow, newCol]);
                }
            }
        }

        counter++;
    }
    
    // Step 3: Update result for houses that are unreachable
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (grid[i][j] === 'H') {
                result[i][j] = -1;
            }
        }
    }

    return result;
}

// const grid = [
//     ['H', '.', 'W'],
//     ['.', 'H', '.'],
//     ['W', '.', 'H']
// ];
// const m = 3, n=3;

const grid = [
    ['H', 'N', 'H', 'H', 'H'],
    ['N', 'N', 'H', 'H', 'W'],
    ['W', 'H', 'H', 'H', 'H'],
    ['H', 'H', 'H', 'H', 'H'],
    ['H', 'H', 'H', 'H', 'H'],
];
const m = 5, n = 5;
console.log(chefAndWells(m, n, grid));

// output - [
//     [ -1, 0, 6, 4, 2 ],
//     [ 0, 0, 4, 2, 0 ],
//     [ 0, 2, 4, 4, 2 ],
//     [ 2, 4, 6, 6, 4 ],
//     [ 4, 6, 8, 8, 6 ]
//   ]
