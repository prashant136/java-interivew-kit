
function floodFilldfs(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    const rows = image.length;
    const cols = image[0].length;
    const originalColor = image[sr][sc];  // Get the original color of the starting pixel

    // If the new color is the same as the original, no need to do anything
    if (originalColor === newColor) return image;

    // Arrays to represent 4 possible directions (up, right, down, left). 
    // this calculataion is based on element (row = 0, col = 0)
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];


    function dfs(r: number, c: number) {
        // Base case: Check if the current position is out of bounds or not matching the original color
        if (r < 0 || c < 0 || r >= rows || c >= cols || image[r][c] !== originalColor) {
            return;
        }

        // Fill the current pixel with the new color
        image[r][c] = newColor;

        // Visit 4 directions - also we can do this
        // dfs(r + 1, c);
        // dfs(r - 1, c);
        // dfs(r, c + 1);
        // dfs(r, c - 1);

        // Explore all 4 possible directions (up, down, left, right)
        for (let k = 0; k < 4; k++) {
            const rr = r + dx[k];  // new row
            const cc = c + dy[k];  // new column
            dfs(rr, cc);  // Recursively call dfs
        }
    }

    // Start the DFS from the given starting pixel (sr, sc)
    dfs(sr, sc);

    return image;
}

function floodFillbfs(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    const rows = image.length;
    const cols = image[0].length;
    const originalColor = image[sr][sc];

    if (originalColor === newColor) return image;

    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];

    const queue: [number, number][] = [[sr, sc]];

    while (queue.length > 0) {
        const [r, c] = queue.shift()!;
        image[r][c] = newColor;

        for (let k = 0; k < 4; k++) {
            const nr = r + dx[k];
            const nc = c + dy[k];

            if (
                nr < 0 &&
                nc < 0 &&
                nr >= rows &&
                nc >= cols &&
                image[nr][nc] !== originalColor
            ) {
                continue;
            } else {
                queue.push([nr, nc]);
            }
        }
    }

    return image;
}


const image = [
    [1, 1, 1],
    [1, 1, 0],
    [1, 0, 1]
];
const sr = 1, sc = 1, newColor = 2;
console.log(floodFilldfs(image, sr, sc, newColor));
console.log(floodFillbfs(image, sr, sc, newColor));


