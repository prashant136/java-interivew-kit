// calculate prefix and suffix sum
// Given an integers array. return the prefix sum without creating a new array. constant space
// const prefixSum = (arr) => {
//     for (let i = 1; i < arr.length; i++) {
//         arr[i] = arr[i - 1] + arr[i];
//     }
//     console.log(arr);
// };
// const suffixSum = (arr) => {
//     for (let i = arr.length - 2; i >= 0; i--) {
//         arr[i] = arr[i + 1] + arr[i];
//     }
//     console.log(arr);
// };

// prefixSum([5, 4, 1, 2, 3]);
// suffixSum([5, 4, 1, 2, 3]);

/***
 *
 * 👋 🔫  Check if we can partition the array into two subarrays with equal sum.
 *        More formally, check that the prefix sum of a part of the array is equal
 *        to the suffix sum of rest of the array
 */

// calculate prefix and suffix sum at every element, then campare at whether prefix sum === suffix sum
// const prefixSum = (arr, i) => {
//     if (i == 0) {
//         return arr[0];
//     } else {
//         let sum = 0;
//         for (let j = 0; j <= i; j++) {
//             sum = sum + arr[j];
//         }
//         return sum;
//     }
// };

// const suffixSum = (arr, i) => {
//     if (i == arr.length - 1) {
//         return arr[arr.length - 1];
//     } else {
//         let sum = 0;
//         for (let j = arr.length - 1; j > i; j--) {
//             sum = sum + arr[j];
//         }
//         return sum;
//     }
// };
// const findPratition = (arr) => {
//     for (let i = 0; i < arr.length; i++) {
//         console.log(i, prefixSum(arr, i), suffixSum(arr, i));
//         if (prefixSum(arr, i) === suffixSum(arr, i)) {
//             return true;
//         }
//     }
//     return false;
// };
// let nums = [6, 2, 4, 3, 1];
// console.log(findPratition(nums));

// ✅ 👉 👑 ⛈️ important pattern - prefixSum(element) + suffixSum(element) = totalSum
// https://www.youtube.com/watch?v=OjFxqCApM6E&list=PLxgZQoSe9cg0df_GxVjz3DD_Gck5tMXAd&t=2
const findPratitionII = (arr) => {
    // calculate total sum for arr
    let totalSum = 0;
    for (const item of arr) {
        totalSum += item;
    }

    let prefixSum = 0;
    for (let i = 0; i < arr.length; i++) {
        prefixSum += arr[i];
        let suffixSum = totalSum - prefixSum;
        // suffix sum = total sum - prefix sum <-> check prefix and suffix is same
        if (suffixSum === prefixSum) {
            return true;
        }
    }
    return false;
};
let nums = [6, 2, 4, 3, 1];
console.log(findPratitionII(nums));
