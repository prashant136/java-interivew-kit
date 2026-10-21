/***
 * 
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a 
    subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.


    Example 1:
    Input: target = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    
    Example 2:
    Input: target = 4, nums = [1,4,4]
    Output: 1
    
    Example 3:
    Input: target = 11, nums = [1,1,1,1,1,1,1,1]
    Output: 0
 */

// varible size $04DSA.sliding window
function minSubArrayLen(target: number, nums: number[]): number {
    let i = 0;
    let sum = 0;
    let minLen = Infinity;

    for (let j = 0; j < nums.length; j++) {
        sum += nums[j];

        if (sum >= target) {
            while (sum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }
    }

    return minLen === Infinity ? 0 : minLen;
}

// let target = 7;
// let nums = [2, 3, 1, 2, 4, 3];
// let target = 4;
// let nums = [1, 4, 4];
// let target = 11;
// let nums = [1,1,1,1,1,1,1,1];
let target = 11;
let nums = [1, 2, 3, 4, 5];

console.log(minSubArrayLen(target, nums));
