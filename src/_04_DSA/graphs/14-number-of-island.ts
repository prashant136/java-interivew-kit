// https://leetcode.com/problems/number-of-islands/description/

function numIslands(grid: string[][]): number {
    const rows = grid.length;
    const cols = grid[0].length;
    let islandCount = 0;

    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];

    // To avoid revisiting the same land
    const visited = Array.from({ length: rows }, () => Array(cols).fill(false));

    function dfs(r: number, c: number): void {
        if (
            r < 0 || c < 0 || r >= rows || c >= cols ||
            grid[r][c] === '0' || visited[r][c]
        ) {
            return;
        }

        visited[r][c] = true; // mark the cell as visited

        // Explore all 4 directions
        for (let k = 0; k < 4; k++) {
            const ii = r + dx[k];
            const jj = c + dy[k];
            dfs(ii, jj);
        }
    }

    // Traverse all cells in the grid
    for (let i = 0; i < rows; i++) {
        for (let j = 0; j < cols; j++) {
            // Start DFS when unvisited land is found
            if (grid[i][j] === '1' && !visited[i][j]) {
                dfs(i, j);
                islandCount++; // finished visiting one full island
            }
        }
    }

    return islandCount;
}
const grid = [
    ["1", "1", "0", "0", "0"],
    ["1", "1", "0", "0", "0"],
    ["0", "0", "1", "0", "0"],
    ["0", "0", "0", "1", "1"]
];

console.log(numIslands(grid));
