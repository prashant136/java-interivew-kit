// Count of subsets sum with a Given sum
// Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.

// Example:
// Input: arr[] = {1, 2, 3, 3}, X = 6
// Output: 3
// All the possible subsets are {1, 2, 3}, {1, 2, 3} and {3, 3}

function countSubsetSum(arr: number[], sum: number) {
    const n = arr.length;

    const dp = Array.from(Array(n + 1), () => Array(sum + 1).fill(0));

    // If sum is 0, then answer is
    // true (empty subset)
    for (let i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }

    // Fill the dp table in bottom-up manner
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= sum; j++) {
            if (j < arr[i - 1]) {
            
                // Exclude the current element
                dp[i][j] = dp[i - 1][j];
            } else {
            
                // Include or exclude
                dp[i][j] = dp[i - 1][j] +  dp[i - 1][j - arr[i - 1]];
            }
        }
    }

    return dp[n][sum];
}
// const arr = [3, 34, 4, 12, 5, 2];
// const sum = 9;
const arr = [1, 2, 3, 3];
const sum = 6;
console.log(countSubsetSum(arr, sum));