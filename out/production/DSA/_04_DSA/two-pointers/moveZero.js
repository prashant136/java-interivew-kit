/**
 * 
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements. Note that you must do this in-place without
 * making a copy of the array.

    Example 1:
    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]

    Example 2:
    Input: nums = [0]
    Output: [0]
 */

// 👉 https://www.youtube.com/watch?v=nZrB4khR610
const moveZeros = (nums) => {
    let nonZeroIndex = 0;
    let i = 0;

    // Iterate through the array and move non-zero elements to the front
    while (i < nums.length) {
        if (nums[i] != 0) {
            // Swap the non-zero element with the first zero encountered
            [nums[i], nums[nonZeroIndex]] = [nums[nonZeroIndex], nums[i]];
            // Move the non-zero index forward
            nonZeroIndex++;
            i++;
        } else {
            i++;
        }
    }
    return nums;
};

let arr = [0, 1, 0, 3, 12];
console.log(moveZeros(arr));
