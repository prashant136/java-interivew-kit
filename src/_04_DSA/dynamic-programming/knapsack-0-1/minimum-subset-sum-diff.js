/**
 * 
 *  🧠 Intuition:
        We’re trying to split the array into two subsets such that their sum difference is as small as possible.
        
        Let:
            sum = total sum of the whole array
            subset1 = sum of one subset
            subset2 = sum - subset1 = sum of the other subset since (sum = first subset + second subset)
        
        The difference between subset sums is:
            we need to minimize diffrence of two subsets

            min Diffrence = min(subset1 - subset2) | in other words
                          = min(sum - subset2 - subset2)
                          = min(sum - 2 * subset2)
                          = absolute(min(sum - 2 * subset2))  -> take absolute for positive value.
 */

// recursion sol -
function minDifference(arr) {
    const sum = arr.reduce((a, b) => a + b, 0);
    const n = arr.length;

    function helper(n, subset1) {
        if (n === 0) {
            return Math.abs((sum - 2 * subset1));
        }
    
        let include = helper(n - 1, subset1 + arr[n - 1]);
    
        let exclude = helper(n - 1, subset1);
    
        return Math.min(include, exclude);
    }
    return helper(n, 0);
}

// recursion + memoization -
function minDifferenceMemo(arr) {
    const sum = arr.reduce((a, b) => a + b, 0);
    const n = arr.length;
    let memo = Array.from({ length: arr.length + 1 }, () => Array(sum + 1).fill(-1));

    function helper(n, subset1) {
        if (n === 0) {
            return Math.abs((sum - 2 * subset1));
        }
        
        if(memo[n][subset1] !== -1) return memo[n][subset1];

        let include = helper(n - 1, subset1 + arr[n - 1]);
        let exclude = helper(n - 1, subset1);
    
        return memo[n][subset1] = Math.min(include, exclude);
    }
    return helper(n, 0);
}

// tabulation approch -
/**
 *      
    columns - 

    | i \ t |  0  |  1  |  2  |  3  | ... 
    | 0     |  ✅ | ❌  |  ❌  |  ❌ | ... |
    | 1     |  ✅ | ?   |  ?  |  ?  | ... |
    | 2     |  ✅ | ?   |  ?  |  ?  | ... |
    | ...   |  ✅ | ?   |  ?  |  ?  | ... |

    📌 Base Case 1 —> target = 0 -> always TRUE
        dp[i][0] = true   // for all i from 0..n  { You can always make empty subset from sum 0 by picking NO elements -> empty subset. }

    📌 Base Case 2 —> i = 0 -> sum > 0 -> FALSE
        dp[0][t] = false   // for all t > 0     { With zero items, you cannot make any positive sum. }

 */
function minDifferenceTabulation(arr) {
    const n = arr.length;
    const sum = arr.reduce((a, b) => a + b, 0);

    // dp[i][t] = true if subset sum t is possible using first i items
    const dp = Array.from({ length: n + 1 }, () => Array(sum + 1).fill(false));

    // Base case #1: sum = 0 is always possible (empty subset)
    for (let i = 0; i <= n; i++) {
        dp[i][0] = true;
    }

    // Base case #2: dp[0][t] = false for all t > 0 (already false in array)

    // Fill DP table
    for (let i = 1; i <= n; i++) {
        for (let t = 1; t <= sum; t++) {
            const notTake = dp[i - 1][t];
            const take = arr[i - 1] <= t ? dp[i - 1][t - arr[i - 1]] : false;

            dp[i][t] = notTake || take;
        }
    }

    // console.table(dp)
    let minDiff = Infinity;

    // Iterate over all possible subset sums to find minimum difference
    // we need to minimize |subset2 - subset1| in other words |sum - 2 * subset1| should be minimum = 0,
    // |sum - 2 * subset1| = 0
    // subset1 = sum / 2;   // Checking beyond half is redundant because it mirrors the other side.
    for (let subset1 = 0; subset1 <= sum / 2; subset1++) {
        if (dp[n][subset1]) {
            minDiff = Math.min(minDiff, Math.abs(sum - 2 * subset1));
        }
    }

    return minDiff;
}

let arr = [1, 6, 11, 5];
// console.log(minDifference(arr));
// console.log(minDifferenceMemo(arr));
console.log(minDifferenceTabulation(arr));
