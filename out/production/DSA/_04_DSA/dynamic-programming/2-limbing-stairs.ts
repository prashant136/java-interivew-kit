/**
 * https://leetcode.com/problems/climbing-stairs/description/
 * 
 * You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    Example 1:
        Input: n = 2
        Output: 2
        Explanation: There are two ways to climb to the top.
        1. 1 step + 1 step
        2. 2 steps
    
    Example 2:
        Input: n = 3
        Output: 3
        Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step
        2. 1 step + 2 steps
        3. 2 steps + 1 step
 */

function climbStairs(n: number, dp: number[]): number {    
    if(n < 0) return 0;
    if(n === 0) return 1;

    if(dp[n] !== -1) return dp[n]

    dp[n] = climbStairs(n-1, dp) + climbStairs(n-2, dp);
    return dp[n];
};

function climbStairsIterative(n: number, dp: number[]): number {
    dp[0] = 0, dp[1] = 1, dp[2] = 2;

    for(let i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
};

const n = 100;
let dp = new Array(n+1).fill(-1);
console.log(climbStairs(n, dp));
console.log(climbStairsIterative(n, dp));

