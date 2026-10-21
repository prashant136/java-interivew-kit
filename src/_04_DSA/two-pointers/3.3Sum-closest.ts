/*
    Given an integer array nums of length n and an integer target, 
    find three integers at distinct indices in nums such that the sum is closest to target.

    Return the sum of the three integers.

    You may assume that each input would have exactly one solution.

    Input: nums = [-1,2,1,-4], target = 1
    Output: 2
    Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

    Input: nums = [0,0,0], target = 1
    Output: 0
    Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
*/

function threeSumClosest(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let closestSum = 0;
    let minVal = Infinity;

    for (let i = 0; i < nums.length - 2; i++) {
        let left = i + 1;
        let right = nums.length - 1;

        while (left < right) {
            const sum = nums[i] + nums[left] + nums[right];
            
            const diff = Math.abs(sum - target);
            if (diff < minVal) {
                closestSum = sum
                minVal = diff
            }

            if (sum === target) {
                return sum;
            }
            else if (sum < target) {
                left++;
            }
            else {
                right--;
            }
        }
    }

    return closestSum;
};

// const nums = [-1,2,1,-4] 
// const target = 1;

// const nums = [0,0,0];
// const target = 1;

const nums = [0,1,2];
const target = 3;

console.log(threeSumClosest(nums, target));     // 3