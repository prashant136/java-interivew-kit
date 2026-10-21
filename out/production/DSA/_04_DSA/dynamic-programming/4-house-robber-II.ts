/**
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent 
 * houses were broken into on the same night.

    Given an integer array nums representing the amount of money of each house, 
    return the maximum amount of money you can rob tonight without alerting the police.

    Example 1:

    Input: nums = [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
    
    Example 2:

    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.
    
    Example 3:

    Input: nums = [1,2,3]
    Output: 3
 */

function houseRob(nums: number[]): number {
    function rob(arr: number[], start: number) {
        let dp = new Array(arr.length + 1).fill(-1);

        function robHelper(i: number) {

            if (i >= arr.length) return 0;

            if (dp[i] !== -1) return dp[i];

            const steal = arr[i] + robHelper(i + 2);
            const skip = robHelper(i + 1);

            return dp[i] = Math.max(steal, skip);
        }
        return robHelper(start);
    };

    const n = nums.length;
    const take_0th_house = rob(nums.slice(0, n - 1), 0);
    const take_1st_house = rob(nums.slice(1), 1);


    return Math.max(take_0th_house, take_1st_house);
};

function houseRobBottomUp(nums: number[]): number {
    const n = nums.length;
    if (n === 0) return 0;
    if (n === 1) return nums[0];

    function rob(arr: number[]): number {
        const dp = new Array(arr.length + 1).fill(0);
        dp[0] = 0;
        dp[1] = arr[0];

        for (let i = 2; i <= arr.length; i++) {
            const steal = dp[i - 2] + arr[i - 1];
            const skip = dp[i - 1];
            dp[i] = Math.max(steal, skip);
        }

        return dp[arr.length];
    }

    const takeFirst = rob(nums.slice(0, n - 1)); // exclude last
    const takeLast = rob(nums.slice(1));        // exclude first

    return Math.max(takeFirst, takeLast);
}

// constnat time (similer pattern)
function houseRobConstantTime(nums: number[]) {
    // ....
}

const nums = [1, 2, 3, 1];
console.log(houseRob(nums));
console.log(houseRobBottomUp(nums));
