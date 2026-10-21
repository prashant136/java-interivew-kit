
// https://leetcode.com/problems/coin-change-ii/description/
// how number of ways ???
function changeTopDown(amount: number, coins: number[]): number {
    let n = coins.length;
    const dp = Array.from({ length: n }, () => Array(amount + 1).fill(-1));

    function kanpsack(amount: number, i: number) {
        // base case
        if (amount === 0) return 1       // it means we successfully formed the amount using some combination of coins. this represents one valid way
        if (i === 0) {
            // If amount % coins[0] === 0, then yes — we can form it using only this coin. That counts as exactly one way.
            // Otherwise, it’s impossible, so return 0.
            return amount % coins[0] === 0 ? 1 : 0;
        }

        if (dp[i][amount] !== -1) return dp[i][amount]

        if (coins[i] > amount) {
            return dp[i][amount] = kanpsack(amount, i - 1);     // exclude coin
        } else {
            const include = kanpsack(amount - coins[i], i);   // include coin
            const exclude = kanpsack(amount, i - 1);   // include coin
            return dp[i][amount] = include + exclude;
        }
    }
    return kanpsack(amount, n - 1)
};


function changeBottomUp(amount: number, coins: number[]): number {
    let n = coins.length;
    const dp = Array.from({ length: n + 1 }, () => Array(amount + 1).fill(0));

    // Base case: ways to make amount=0 is always 1 (choose no coins)
    for (let i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }

    for (let i = 1; i <= n; i++) {
        for (let t = 1; t <= amount; t++) {
            if (coins[i - 1] > t) {
                dp[i][t] = dp[i - 1][t];
            } else {
                dp[i][t] = dp[i - 1][t] + dp[i][t - coins[i - 1]];
            }
        }
    }

    return dp[n][amount];
}


const amount = 5;
const coins = [1, 2, 5];

// console.log(changeTopDown(amount, coins));
console.log(changeBottomUp(amount, coins));




