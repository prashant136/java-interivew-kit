/***
 * 
 * Find First and Last Position of Element in Sorted Array
    Given an array of integers nums sorted in non-decreasing order, 
    find the starting and ending position of a given target value.
    
    If target is not found in the array, return [-1, -1].
    You must write an algorithm with O(log n) runtime complexity.


    Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
    
    Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]
    
    Example 3:
    Input: nums = [], target = 0
    Output: [-1,-1]
 */

    /**
        * 1) Find First Occurrence (Left boundary)

        Even if we find target at mid,
        we don’t stop — we move left (right = mid - 1)
        because an earlier occurrence may exist.

        2) Find Last Occurrence (Right boundary)

        Even if we find target at mid,
        we don’t stop — we move right (left = mid + 1)
        because a later occurrence may exist.

        This “keep searching after finding target” is the entire trick.
        
        🧩 Two Helpful Mental Models
        Model 1: Treat it like searching for a boundary

        We are searching for the boundary:

        [non-target][target target target][non-target]
                    ↑ first           ↑ last


        Left boundary = first target
        Right boundary = last target
     */

// ✅ 👑 facebook
const binarySearch = (nums, target, findFirstIndex) => {
    let start = 0;
    let end = nums.length - 1;
    let ans = -1;

    while (start <= end) {
        let mid = Math.floor(start + (end - start) / 2);
        if (target > nums[mid]) {
            start = mid + 1;
        } else if (target < nums[mid]) {
            end = mid - 1;
        } else {
            ans = mid;
            if (findFirstIndex) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
    return ans;
};
const searchRange = (nums, target) => {
    // apply binary search two times in order to find first and last index target value.
    const firstIndex = binarySearch(nums, target, true);
    const lastIndex = binarySearch(nums, target, false);
    return [firstIndex, lastIndex];
};

let arr = [5, 7, 7, 8, 8, 8, 8, 8,8,8,8,8,8,10];
let target = 8;
console.log(searchRange(arr, target));
