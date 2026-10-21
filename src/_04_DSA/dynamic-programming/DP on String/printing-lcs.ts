

/* algorithm -

    function lcs(i, j) {
        // When either string is exhausted.
        if i < 0 or j < 0:
            return ""

        // Last characters are same, Then this character must be part of LCS.    
        if s1[i] == s2[j]:
            return lcs(i-1, j-1) + s1[i]

        left  = lcs(i-1, j)
        right = lcs(i, j-1)

        return longer(left, right)
    }
    
    Time complexity - O(2^(n+m))
*/

function printLCS(s1: string, s2: string): string {
    // define a map
  const memo = new Map<string, string>();

  function lcs(i: number, j: number): string {
    if (i < 0 || j < 0) return "";

    // define key at every fresh function call
    const key = `${i},${j}`;
    if (memo.has(key)) return memo.get(key)!;

    let result: string;

    if (s1[i] === s2[j]) {
        // console.log("# ---- IF ---- #")
      result = lcs(i - 1, j - 1) + s1[i];
    } else {
        // when both string are not match -> take maximum of both
      const left = lcs(i - 1, j);
      const right = lcs(i, j - 1);
    //   console.log("left -", left)
    //   console.log("right -", right)
      result = left.length >= right.length ? left : right;
    }
    // console.log("result -", result)
    // console.log('memo -', memo, "\n\n");

    memo.set(key, result);
    return result;
  }

  return lcs(s1.length - 1, s2.length - 1);
}

// [ TRICKEY APPROCH ]
/*
    ⏱️ Complexity:
        Time: O(n × m)
        Space: O(n × m)
*/
function printLongestCommonSubsequence(s1: string, s2: string): string {
    const n = s1.length;
    const m = s2.length;

    // 1️⃣ DP table
    const dp: number[][] = Array.from({ length: n + 1 }, () =>
        Array(m + 1).fill(0)
    );

    // Fill dp table
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= m; j++) {
            if (s1[i - 1] === s2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    console.table(dp);
    
    // 2️⃣ Backtracking to build LCS string
    let i = n;
    let j = m;
    const result: string[] = [];

    while (i > 0 && j > 0) {
        if (s1[i - 1] === s2[j - 1]) {
            // character is part of LCS
            result.push(s1[i - 1]);
            i--;
            j--;
        } else {
            // move towards the larger dp value
            if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
    }

    // reverse because we built it backwards
    return result.reverse().join("");
}

const s1 = "acbcf";
const s2 = "abcdaf";

console.log(printLongestCommonSubsequence(s1, s2));
// Output: "ace"

