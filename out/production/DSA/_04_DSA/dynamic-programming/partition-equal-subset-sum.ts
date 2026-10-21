function canPartition(nums: number[]): boolean {
    let sum = 0;
    for (let ele of nums) {
        sum += ele
    }

    if (sum % 2 === 0) {
        return partitionFn(nums, nums.length, sum / 2)
    } else {
        return false;
    }
};

function partitionFn(nums: number[], n: number, sum: number) {
    let dp = Array.from({ length: n + 1 }, () => new Array(sum + 1).fill(false));

    for (let i = 0; i < n; i++) {
        dp[i][0] = true
    }

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= sum; j++) {
            if (j < nums[i - 1]) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
    }
    return dp[n][sum];
}