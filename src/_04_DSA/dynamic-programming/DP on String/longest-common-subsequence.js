
// recursive + memoization - top down approch
function longestCommonSubsequence(str1, str2) {
    const n = str1.length;
    const m = str2.length;
    // Create a memoization table (n+1) x (m+1) initialized with -1
    const dp = Array.from({ length: n + 1 }, () => Array(m + 1).fill(-1));

    function dfs(n, m) {
        // Base case: if either string is empty, LCS length = 0
        if (n === 0 || m === 0) {
            return 0;
        }

        // If we already solved this subproblem, return cached result
        if (dp[n][m] !== -1) return dp[n][m];

        // Case 1: characters match
        // Take 1 + result of smaller substrings (n-1, m-1)
        if (str1[n - 1] === str2[m - 1]) {
            return dp[n][m] = 1 + dfs(n - 1, m - 1);
        }
        // Case 2: characters don't match
        // Either skip last char of str1 OR skip last char of str2
        // Take the max of both options 
        else {
            const skipStr2 = dfs(n, m - 1)
            const skipStr1 = dfs(n - 1, m)
            return dp[n][m] = Math.max(skipStr1, skipStr2);
        }
    }

    return dfs(n, m)
}

// bottom up approch - tabulation 
function longestCommonSubsequenceBottomUp(str1, str2) {
    const n = str1.length;
    const m = str2.length;
    const dp = Array.from({ length: n + 1 }, () => Array(m + 1).fill(0));

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= m; j++) {
            if (str1[i - 1] === str2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                const includeStr1 = dp[i][j - 1];
                const includeStr2 = dp[i - 1][j];
                dp[i][j] = Math.max(includeStr1, includeStr2);
            }
        }
    }
    return dp[n][m];
}

const str1 = "abcdfg";
const str2 = "axcyf";
console.log(longestCommonSubsequenceBottomUp(str1, str2));


