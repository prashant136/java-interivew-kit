function shortestCommonSupersequence(s1: string, s2: string): string {
    const n = s1.length;
    const m = s2.length;

    // 1️⃣ Build LCS dp table
    const dp: number[][] = Array.from({ length: n + 1 }, () =>
        Array(m + 1).fill(0)
    );

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= m; j++) {
            if (s1[i - 1] === s2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // 2️⃣ Backtrack to build SCS
    let i = n;
    let j = m;
    const result: string[] = [];

    while (i > 0 && j > 0) {
        if (s1[i - 1] === s2[j - 1]) {
            result.push(s1[i - 1]);
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            result.push(s1[i - 1]);
            i--;
        } else {
            result.push(s2[j - 1]);
            j--;
        }
    }

    // 3️⃣ Add remaining characters
    while (i > 0) {
        result.push(s1[i - 1]);
        i--;
    }

    while (j > 0) {
        result.push(s2[j - 1]);
        j--;
    }

    return result.reverse().join("");
}

const s1 = "abac"
const s2 = "cab"
console.log(shortestCommonSupersequence(s1, s2));