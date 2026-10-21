// function LCSuf(s1, s2, m, n) {
//     // console.log({ m, n, s1: s1[m - 1], s2: s2[n - 1] })
//     if (m === 0 || n === 0 || s1[m - 1] !== s2[n - 1]) {
//         return 0;
//     }
//     return 1 + LCSuf(s1, s2, m - 1, n - 1);
// }

// function maxCommStr(s1, s2) {
//     let res = 0;
//     let m = s1.length;
//     let n = s2.length;

//     // Find the longest common substring ending at every pair of 
//     // characters and take the maximum of all.
//     for (let i = 1; i <= m; i++) {
//         for (let j = 1; j <= n; j++) {
//             res = Math.max(res, LCSuf(s1, s2, i, j));
//         }
//     }
//     return res;
// }

// let s1 = "geekz";
// let s2 = "geeksz";
// console.log(maxCommStr(s1, s2));



// --------------- top down --------------
function LongestCommonStr(s1, s2) {
    let res = 0;
    let m = s1.length;
    let n = s2.length;
    const dp = Array.from({ length: m + 1 }, () => Array(n + 1).fill(-1));

    console.table(dp);

    function helper(i, j) {
        if (i === 0 || j === 0) {
            return 0;
        }

        if (dp[i][j] !== -1) return dp[i][j];

        if (s1[i - 1] !== s2[j - 1]) {
            dp[i][j] = 0
        } else {
            dp[i][j] = 1 + helper(i - 1, j - 1);
        }
        return dp[i][j];
    }


    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            res = Math.max(res, helper(i, j));
        }
    }

    console.table(dp);
    return res;
}

let s1 = "geekz";
let s2 = "geeksz";
console.log(LongestCommonStr(s1, s2));