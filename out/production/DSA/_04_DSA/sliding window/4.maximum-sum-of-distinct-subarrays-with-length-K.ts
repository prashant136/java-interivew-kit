/*
    You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

    The length of the subarray is k, and
    All the elements of the subarray are distinct.
    Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

    A subarray is a contiguous non-empty sequence of elements within an array.


    Input: nums = [1,5,4,2,9,9,9], k = 3
    Output: 15
    Explanation: The subarrays of nums with length 3 are:
    - [1,5,4] which meets the requirements and has a sum of 10.
    - [5,4,2] which meets the requirements and has a sum of 11.
    - [4,2,9] which meets the requirements and has a sum of 15.
    - [2,9,9] which does not meet the requirements because the element 9 is repeated.
    - [9,9,9] which does not meet the requirements because the element 9 is repeated.
    We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

    Input: nums = [4,4,4], k = 3
    Output: 0
    Explanation: The subarrays of nums with length 3 are:
    - [4,4,4] which does not meet the requirements because the element 4 is repeated.
    We return 0 because no subarrays meet the conditions.
*/

// 👀 Important... Sliding Window + HashMap
function maximumSubarraySum(nums: number[], k: number): number {
    let i = 0;
    let sum = 0;
    let result = 0;
    const map = new Map<number, number>();

    for (let j = 0; j < nums.length; j++) {

        sum += nums[j];
        map.set(nums[j], (map.get(nums[j]) || 0) + 1);

        // If duplicate exists → shrink window
        while (map.get(nums[j])! > 1) {
            map.set(nums[i], map.get(nums[i])! - 1);
            sum -= nums[i];
            i++;
        }

        // If window exceeds k → shrink
        if (j - i + 1 > k) {
            map.set(nums[i], map.get(nums[i])! - 1);
            sum -= nums[i];
            i++;
        }

        // If window size == k → update result
        if (j - i + 1 === k) {
            result = Math.max(result, sum);
        }
    }

    return result;
}

let nums = [1,5,4,2,9,9,9]
let k = 3
console.log(maximumSubarraySum(nums, k));
