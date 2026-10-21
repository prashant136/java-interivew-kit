
// https://leetcode.com/problems/longest-cycle-in-a-graph/description/

function longestCycle(edges: number[]): number {
    const numOfNodes = edges.length;
    const visitedNode = new Array<number>(numOfNodes).fill(0);
    const currentPath = new Array<number>(numOfNodes).fill(0);

    let longestCycleLen = -1;

    function dfs(cycleLen: number, node: number) {
        cycleLen++;
        currentPath[node] = cycleLen;
        visitedNode[node] = 1;

        const nbr = edges[node];
        if (nbr !== -1) {
            if (!visitedNode[nbr]) {
                dfs(cycleLen, nbr);
            } else if (currentPath[nbr] !== 0) {
                const currCycleLen = currentPath[node] - currentPath[nbr] + 1;
                longestCycleLen = Math.max(longestCycleLen, currCycleLen);
            }
        }

        currentPath[node] = 0; // Backtrack
    }

    for (let i = 0; i < numOfNodes; i++) {
        if (!visitedNode[i]) {
            dfs(0, i);
        }
    }

    return longestCycleLen;
}

console.log(longestCycle([3,3,4,2,3])); // Output: 3 (cycle: 3 -> 2 -> 4 -> 3)
console.log(longestCycle([2,-1,3,1])); // Output: -1 (no cycle)
