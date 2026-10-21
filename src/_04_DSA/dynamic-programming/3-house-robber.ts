/**
 * https://leetcode.com/problems/house-robber/description/
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 *  and it will automatically contact the police if two adjacent houses were broken into on the same night.

   Given an integer array nums representing the amount of money of each house, 
   return the maximum amount of money you can rob tonight without alerting the police.

    Example 1:
        Input: nums = [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
        Total amount you can rob = 1 + 3 = 4.
    
    Example 2:
        Input: nums = [2,7,9,3,1]
        Output: 12
        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        Total amount you can rob = 2 + 9 + 1 = 12.
 */

export function rob(nums: number[]) {
    let dp = new Array(nums.length + 1).fill(-1);

    function robHelper(i: number) {

        if (i >= nums.length) return 0;

        if (dp[i] !== -1) return dp[i];

        const steal = nums[i] + robHelper(i + 2);
        const skip = robHelper(i + 1);

        return dp[i] = Math.max(steal, skip);
    }
    return robHelper(0);
};

function robBottomUp(nums: number[]) {
    const n = nums.length;

    if (n === 1) return nums[0];

    let dp = new Array(n + 1).fill(0);  // max stolen money till house i
    dp[0] = 0;
    dp[1] = nums[0];

    for (let i = 2; i <= n; i++) {
        const steal = dp[i - 2] + nums[i - 1];
        const skip = dp[i - 1];
        dp[i] = Math.max(steal, skip);
    }

    return dp[n];
};

function robConstantTime(nums: number[]): number {
    const n = nums.length;

    if (n === 1) return nums[0];

    let prevPrev = 0;
    let prev = nums[0];

    for (let i = 2; i <= n; i++) {
        const steal = prevPrev + nums[i - 1];
        const skip = prev;
        const temp = Math.max(steal, skip);

        prevPrev = prev;
        prev = temp;
    }

    return prev;
}

const nums = [2, 7, 9, 3, 1]
console.log(rob(nums));
// console.log(robBottomUp(nums));
console.log(robConstantTime(nums));
