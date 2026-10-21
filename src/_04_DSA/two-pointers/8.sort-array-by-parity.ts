/*
    Given an integer array nums, move all the even integers at the beginning of the array 
    followed by all the odd integers.
    Return any array that satisfies this condition.

        Input: nums = [3,1,2,4]
        Output: [2,4,3,1]
        Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

        Input: nums = [0]
        Output: [0]
*/

function sortArrayByParity(nums: number[]): number[] {
    let result: number[] = [];

    // evens
    for (let num of nums) {
        if (num % 2 === 0) result.push(num);
    }

    // odds
    for (let num of nums) {
        if (num % 2 !== 0) result.push(num);
    }

    return result;
}


function sortArrayByParity(nums: number[]): number[] {
    let left = 0;
    let right = nums.length - 1;

    while (left < right) {
        if (nums[left] % 2 === 0) {
            left++;
        } else if (nums[right] % 2 === 1) {
            right--;
        } else {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            left++;
            right--;
        }
    }

    return nums;
}

