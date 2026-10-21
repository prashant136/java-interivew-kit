// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

function isSubsetSum(arr, n, sum) {
    if (sum === 0) return true;
    if (n === 0) return false;

    // If the last element is greater than the sum, ignore it
    if (arr[n - 1] > sum) {
        return isSubsetSum(arr, n - 1, sum);
    }

    // Check if sum can be obtained by including
    // or excluding the last element
    return isSubsetSum(arr, n - 1, sum) ||
        isSubsetSum(arr, n - 1, sum - arr[n - 1]);
}

// const arr = [3, 34, 4, 12, 5, 2];
// const sum = 12;
// console.log(isSubsetSum(arr, arr.length, sum));

/**
 * 
 * 🎯 Problem Statement: Subset Sum
    Given an array of integers arr[] and a target sum S, determine if there exists a subset of the array whose sum is exactly S.
    
    Example:
    Arr = [3, 34, 4, 12, 5, 2], Sum = 12  
    Output: true  // subset {3, 4, 5}

    Arr = [3, 34, 4, 12, 5, 2], Sum = 30
    Output: false // There is no subset that add up to 30.

    🧠 Intuition & Relation to 0/1 Knapsack
        Knapsack version:
            weight[i] = arr[i]
            value[i] = arr[i] (but here value is irrelevant, we just care about sum match)
            Capacity = Sum

    🔁 Top-Down Approach (Recursion + Memoization)
        dp(i, target) → true if a subset of arr[0..i] can make sum = target

    Recurrence:
        If target == 0 → return true (found exact sum)
        If i < 0 and target not 0 → return false
        Otherwise:
            Exclude: dp(i-1, target)
            Include: if arr[i] <= target then dp(i-1, target - arr[i])
        Return true if any is true.
 */

function subsetSumTopDown(arr, sum) {
    const n = arr.length;
    const memo = Array.from({ length: n }, () => Array(sum + 1).fill(-1));

    function dfs(i, target) {
        if (target === 0) return true;
        if (i < 0) return false;

        if (memo[i][target] !== -1) return memo[i][target];

        if (arr[i] > target) {
            return memo[i][target] = dfs(i - 1, target);
        } else {
            const taken = dfs(i - 1, target - arr[i]);
            const notTaken = dfs(i - 1, target);
            return memo[i][target] = taken || notTaken;
        }
    }

    return dfs(n - 1, sum);
}
// const arr = [3, 34, 4, 12, 5, 2];
// const sum = 12;
// console.log(subsetSumTopDown(arr, sum));

/** 
 * 📥 Bottom-Up Approach (Tabulation)
 * 
 * 
 */

function subsetSumBottomUp(arr, sum) {
    const n = arr.length;
    const dp = Array.from({ length: n+1 }, () => Array(sum + 1).fill(false));

    // base case --> dp[i][0] = true for all i (we can always form sum 0 by taking no elements)
    for (let i = 0; i < arr.length; i++) {
        dp[i][0] = true;
    }

    // console.table(dp);
    for (let i = 1; i <= n; i++) {
        for (let t = 1; t <= sum; t++) {
            // const taken = dp[i - 1][t];
            // const notTaken =  dp[i - 1][t - arr[i]];
            if (arr[i] > sum) {
                dp[i][t] = dp[i - 1][t];
            } else {
                dp[i][t] = dp[i - 1][t] || dp[i - 1][t - arr[i]];
            }
        }
    }
    return dp[n][sum]

}
const arr = [3, 34, 4, 12, 5, 2];
const sum = 12;
console.log(subsetSumBottomUp(arr, sum));
