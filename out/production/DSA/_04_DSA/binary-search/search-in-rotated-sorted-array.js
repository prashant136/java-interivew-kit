/***
 * 
 * Search in Rotated Sorted Array
 * 
    Example 1:
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4

    Example 2:
    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1

    Example 3:
    Input: nums = [1], target = 0
    Output: -1
 */

//     steps: identify the sorted part & unsorted part
//     check whether target is in sorted part or unsorted part
    
// 👉 https://www.youtube.com/watch?v=5qGrJbHhqFs&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=5
// function search(arr, target) {
//     let left = 0;
//     let right = arr.length - 1;

//     while (left <= right) {
//         let mid = Math.floor((left + right) / 2);
//         // 👑 array is rotated sorted. if we calculate middle element, one half is sorted  & other half is unsorted.
//         if (arr[mid] === target) {
//             return mid;
//         }
//         // left part is sorted
//         else if (arr[left] <= arr[mid]) {
//             if (arr[left] <= target && arr[mid] >= target) {
//                 right = mid - 1;
//             } else {
//                 left = mid + 1;
//             }
//         }
//         // right part is sorted
//         else {
//             if (arr[mid] <= target && arr[right] >= target) {
//                 left = mid + 1;
//             } else {
//                 right = mid - 1;
//             }
//         }
//     }
//     return -1;
// }

// let nums = [4, 5, 6, 7, 0, 1, 2];
// let target = 2;
// console.log(search(nums, target));

/** ------------------- Search Element in Rotated Sorted Array II (duplicate elements) -------------------------
    There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
    Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
    Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
    You must decrease the overall operation steps as much as possible.

    Example 1:
    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true

    Example 2:
    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false

*/

// 👉 https://www.youtube.com/watch?v=w2G2W8l__pc&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=7
function searchDuplicate(arr, target) {
    let left = 0;
    let right = arr.length - 1;

    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        // 👑 arr[left] === arr[mid] === arr[right]. then we can't determine sorted & un sorted part. so moved out both pointer
        if (arr[left] === arr[mid] && arr[mid] === arr[right]) {
            left++;
            right--;
            continue;
        }

        console.log({ left, right });
        if (arr[mid] === target) {
            return mid;
        }
        // left part is sorted
        else if (arr[left] <= arr[mid]) {
            if (arr[left] <= target && arr[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // right part is sorted
        else {
            if (arr[mid] <= target && arr[right] >= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return -1;
}

// let arr = [2, 5, 6, 0, 0, 1, 2];
let arr = [3, 3, 1, 3, 3, 3, 3];
let k = 3;
console.log(searchDuplicate(arr, k));
