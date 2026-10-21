/**
   👉 for $04DSA.sliding window its easy to use while loop
    size of window = (endIndex - startIndex + 1)
    
    🔴 ⚪️ Step to follow in fix size $04DSA.sliding window problems:
            make a window
            do calculation(min/max/occurances etc.) when you first time hit window size
            slide the window and do calculations every time
    
*/

// 👉 🎲 find maximum sum of size k.
// function maxSumSubArray(nums, k) {
//     let ans = Number.MIN_SAFE_INTEGER
//     for (let i = 0; i + k <= nums.length; i++) {
//         let sum = 0
//         for (let j = i; j < i + k; j++) {
//             sum += nums[j]
//         }
//         ans = Math.max(sum, ans)
//     }
//     return ans
// }

// 👋 🎲 ------ $04DSA.sliding window -------
// function maxSum(nums, k) {
//     // size of window - (end - start + 1)
//     let ans = Number.MIN_VALUE
//     let sum = 0
//     let i = 0
//     let j = 0
//     // calculate window and maximum sum for very first time
//     while (j < k) {
//         sum += nums[j]
//         if (j - i + 1 < k) {
//             j += 1
//         }
//         else if (j - i + 1 == k) {
//             ans = Math.max(ans, sum)
//             break
//         }
//     }
//     // after calculating window size, we move window to right direction by removing first element of window and 
//     // adding next element of array which is just outside of window.
//     while (j < nums.length - 1) {
//         sum = sum - nums[i]
//         i += 1
//         j += 1
//         sum = sum + nums[j]
//         ans = Math.max(ans, sum)
//     }
//     return ans
// }
// let nums = [1, 12, -5, -6, 50, 3];
// let k = 4;
// console.log(maxSum(nums, k));

/***
 * Find maximim average of window size k.
    Example 1:
    Input: nums = [1,12,-5,-6,50,3], k = 4
    Output: 12.75000
    Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75

    Example 2:
    Input: nums = [5], k = 1
    Output: 5.00000
 */
//  🚩 🚩 🚩
const findMaxAverage = (arr, k) => {
    let ans = Number.MIN_SAFE_INTEGER;
    let sum = 0;
    let i = 0;
    let j = 0;
    while (j < arr.length) {
        let windowSize = j - i + 1;
        // windowSize is less than k then j++
        if (windowSize < k) {
            sum = sum + arr[j];
            j++;
        }
        // windowSize === k.
        else if (windowSize === k) {
            sum = sum + arr[j];
            ans = Math.max(sum, ans);
            sum = sum - arr[i];
            i++;
            j++;
        }
    }
    return ans;
};
let nums = [1, 12, -5, -6, 50, 3];
let k = 4;

console.log(findMaxAverage(nums, k));
