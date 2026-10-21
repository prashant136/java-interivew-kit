
function rodCutting(chunks: number[], profit: number[], rodLength: number, n: number): number {
    let dp = Array.from({ length: n }, () => Array(rodLength + 1).fill(-1));
    
    function knapsack(rodLength: number, idx: number): number {
        // base case
        if (idx < 0 || rodLength === 0) {
            return 0;
        }

        if (dp[idx][rodLength] !== -1) return dp[idx][rodLength];

        if (chunks[idx] > rodLength) {
            return dp[idx][rodLength] = knapsack(rodLength, idx - 1);
        } else {
            const taken = profit[idx] + knapsack(rodLength - chunks[idx], idx);
            const notTaken = knapsack(rodLength, idx - 1);
            return dp[idx][rodLength] = Math.max(taken, notTaken);
        }
    }

    return knapsack(rodLength, n - 1);
}

const chunks = [2, 4, 6];
const profit = [5, 11, 13];
const rodLength = 10;

console.log(rodCutting(chunks, profit, rodLength, profit.length)); 


