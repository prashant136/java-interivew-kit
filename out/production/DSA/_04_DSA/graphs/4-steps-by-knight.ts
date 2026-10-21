// Given a square chessboard of size (n x n), the initial position and target postion of Knight are given. 
// Find out the minimum steps a Knight will take to reach the target position.

// Note: The initial and the target position coordinates of Knight have been given according to 1-base indexing.


// Examples:

// Input: n = 3, knightPos[] = [3, 3], targetPos[]= [1, 2]
// Output: 1
// Explanation: Knight takes 1 step to reach from 
// (3, 3) to (1, 2).

// Input: n = 6, knightPos[] = [4, 5],targetPos[] = [1, 1]
// Output: 3
// Explanation: Knight takes 3 step to reach from 
// (4, 5) to (1, 1):
// (4, 5) -> (5, 3) -> (3, 2) -> (1, 1).

// Constraints:
// 1 <= n <= 1000
// 1 <= knightpos ≤ [x, y], targertpos[x, y] <= n


// there are 3 steps:-
// 1. how to diffrentiate levels
// 2. find neighbour nodes (i, j)
// 3. change source/destination co-ordinate to matrix co-ordinate

// https://www.youtube.com/watch?v=aS4DwjqfMfI&list=PL_z_8CaSLPWcn5bKG8UMI0St2D5EmQszx&index=11

function minStepToReachTarget(knightPos: [number, number], targetPos: [number, number], n: number): number {
    const directions: [number, number][] = [
        [-2, -1], [-1, -2], [1, -2], [2, -1],
        [-2, 1], [-1, 2], [1, 2], [2, 1]
    ];

    function isValid(x: number, y: number, visited: boolean[][]): boolean {
        return x >= 0 && y >= 0 && x < n && y < n && !visited[x][y];
    }

    function helper(src_x: number, src_y: number, target_x: number, target_y: number): number {
        const visited: boolean[][] = Array.from({ length: n }, () =>
            Array(n).fill(false)
        );
        const queue: [number, number][] = [];
        queue.push([src_x, src_y]);
        visited[src_x][src_y] = true;

        let steps = 0;

        while (queue.length > 0) {
            let size = queue.length;
            while (size--) {
                const [x, y] = queue.shift() as [number, number];

                if (x === target_x && y === target_y) {
                    return steps;
                }

                for (const [dx, dy] of directions) {
                    const newX = x + dx;
                    const newY = y + dy;

                    if (isValid(newX, newY, visited)) {
                        visited[newX][newY] = true;
                        queue.push([newX, newY]);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    // Convert from 1-based to 0-based indexing
    const [src_x, src_y] = [knightPos[0] - 1, knightPos[1] - 1];
    const [target_x, target_y] = [targetPos[0] - 1, targetPos[1] - 1];

    return helper(src_x, src_y, target_x, target_y);
}

console.log(minStepToReachTarget([3, 3], [1, 2], 3)); // Output: 1
console.log(minStepToReachTarget([4, 5], [1, 1], 6)); // Output: 3
