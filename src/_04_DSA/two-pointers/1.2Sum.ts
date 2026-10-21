/***
 * 
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.
 

    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]

 */

// -------------       --------------
function twoSum(arr: number[], target: number) {
    let map = new Map();
    // store elements and there index in the map
    for (let i = 0; i < arr.length; i++) {
        map.set(arr[i], i);
    }
    // check if map contains the complement (target - nums[i]) value
    for (let i = 0; i < arr.length; i++) {
        let complement = target - arr[i];
        // Ensure that the complement exists in the map and it's not the same index as the current element
        if (map.has(complement) && map.get(complement) !== i) {
            return [i, map.get(complement)];
        }
    }
}

let arr = [3, 2, 4];
let target = 6;
console.log(twoSum(arr, target));
