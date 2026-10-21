// https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/
function minReorder(n: number, connections: number[][]): number {
    const forwardNbrs: number[][] = Array.from({ length: n }, () => []);
    const backwardNbrs: number[][] = Array.from({ length: n }, () => []);
    const visited: boolean[] = new Array(n).fill(false);

    // Build directed and reverse-directed adjacency lists
    for (const [a, b] of connections) {
        forwardNbrs[a].push(b);   // Needs reordering if used
        backwardNbrs[b].push(a);  // Already correct direction
    }

    console.log({forwardNbrs, backwardNbrs});
    
    let ans = 0;

    function dfs(node: number): void {
        visited[node] = true;

        for (const nbr of forwardNbrs[node]) {
            console.log({forwardNbrs: forwardNbrs[node], nbr});
            
            if (!visited[nbr]) {
                ans += 1; // Need to reverse this edge
                dfs(nbr);
            }
        }

        for (const nbr of backwardNbrs[node]) {
            if (!visited[nbr]) {
                dfs(nbr);
            }
        }
    }

    dfs(0);
    return ans;
}

console.log(minReorder(6, [[0,1],[1,3],[2,3],[4,0],[4,5]])); // Output: 3