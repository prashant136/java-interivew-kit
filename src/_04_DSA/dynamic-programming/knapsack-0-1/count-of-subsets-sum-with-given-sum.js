
/**
 * INTUATION FOR BASE CASE -
 * ✅ How to Analyze Base Cases in Tabulation (Subset Count Example)

    To convert a recursive memoized solution into tabulation, you must first understand the base cases in recursion.

    📌 Step 1 — Start from the Recursive Definition -

        The standard recursion for counting subsets is:
        count(i, sum) = count(i - 1, sum)  // exclude
            +  count(i - 1, sum - arr[i-1])  // include

        The base cases in TOP-DOWN were:

        if sum === 0 → return 1       (one valid subset: empty subset)
        if i === 0   → return 0       (no items but sum > 0 → no subset)

        These base cases tell you EXACTLY how to initialize the DP table.

    
    📌 Step 2 — Convert Each Base Case into Table Initialization -

        You build a 2D table:  dp[i][j] = number of ways to form sum j using first i elements

        Base Case 1: sum === 0 -> 1 way
            This must become:
            dp[i][0] = 1    for all i

        Because with any number of items, there is always exactly 1 way to make sum = 0 
        -> the empty subset.
        ⭐ This is the most important base case.

        Base Case 2: i === 0 -> sum > 0  0 ways
            This becomes:
            dp[0][j] = 0    for all j > 0
 */
function countSubsetsBottomUp(arr, target) {
    const dp = Array.from({length : arr.length + 1}, () => Array(target + 1).fill(0));

    // When sum = 0 → 1 way (empty subset)
    for (let i = 0; i <= arr.length; i++) {
        dp[i][0] = 1;
    }

    // When no items → any positive sum = 0 ways
    for (let j = 1; j <= target; j++) {
        dp[0][j] = 0;
    }

    for (let i = 1; i <= arr.length; i++) {
        for (let j = 0; j <= target; j++) {
            dp[i][j] = dp[i - 1][j];  // exclude

            if (arr[i - 1] <= j) {
                dp[i][j] += dp[i - 1][j - arr[i - 1]];
            }
        }
    }

    return dp[arr.length][target];
}

/** top down approch */
function countFn(arr, target) {
  const n = arr.length;

  // memo[n][sum] = number of ways
  const memo = Array.from({ length: n + 1 }, () =>
    Array(target + 1).fill(undefined)
  );

  function helper(i, sum) {
    // Base cases
    if (sum === 0) return 1;      // one valid subset found
    if (i === 0) return 0;        // no items left

    // Check memo
    if (memo[i][sum] !== undefined) return memo[i][sum];

    let ways = 0;

    // Option 1: Exclude current item
    ways += helper(i - 1, sum);

    // Option 2: Include current item if possible
    if (arr[i - 1] <= sum) {
      ways += helper(i - 1, sum - arr[i - 1]);
    }

    return (memo[i][sum] = ways);
  }

  return helper(n, target);
}

const arr = [ 1, 2, 3, 3 ];
const target = 6;
console.log(countSubsetsBottomUp(arr, target));
// console.log(countFn(arr, target)); 