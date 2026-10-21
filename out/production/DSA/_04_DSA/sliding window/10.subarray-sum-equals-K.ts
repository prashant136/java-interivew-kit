
/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

    Input: nums = [1,1,1], k = 2
    Output: 2

    Input: nums = [1,2,3], k = 3
    Output: 2
*/

// Positive numbers → $04DSA.sliding window
// 👀 Negative allowed → prefix sum (works for both positive and negative integer)

// https://www.youtube.com/watch?v=-SWrz90jCUM

function subarraySum(nums: number[], k: number): number {
    let count = 0;
    let cumulativeSum = 0;
    const map = new Map<number, number>();

    // initially cumulative sum is zero. added to map with freq 1
    map.set(0, 1);

    for (let i = 0; i < nums.length; i++) {
        cumulativeSum += nums[i];
        
        if(map.has(cumulativeSum - k)) {
            count += map.get(cumulativeSum - k)!
        }

        map.set(cumulativeSum, (map.get(cumulativeSum) || 0) + 1);
    }
    return count;
};


