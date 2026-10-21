/*
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Input: nums = [1,2,3,1], k = 3
Output: true

Input: nums = [1,0,1,1], k = 1
Output: true

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

// ✅  Using a Set with Sliding Window:
function containsNearbyDuplicateSlidingWindow(nums: number[], k: number): boolean {
    // Create a Set to store unique elements within the $04DSA.sliding window
    const numSet = new Set();

    // Loop through the array 'nums'
    for (let i = 0; i < nums.length; i++) {
        // Check if the current index 'i' is greater than 'k'
        if (i > k) {
            // If yes, remove the element at position 'i - k - 1' from the Set
            // This maintains the $04DSA.sliding window of size 'k'
            numSet.delete(nums[i - k - 1]);
        }

        // Check if the current element 'nums[i]' is already in the Set
        if (numSet.has(nums[i])) {
            // If yes, there is a duplicate within the specified distance 'k'
            return true;
        }

        // Add the current element 'nums[i]' to the Set
        numSet.add(nums[i]);
    }

    // If the loop completes without finding any duplicates, return false
    return false;
}

// ✅  Using a Hash Map: (easy)
function containsNearbyDuplicateHashMap(nums: number[], k: number): boolean {
    const map = new Map<number, number>();

    for (let i = 0; i < nums.length; i++) {
        // Check if the current element 'nums[i]' is a key in the map
        // and if the difference between the current index 'i' and the stored index is less than or equal to 'k'
        if (map.has(nums[i]) && Math.abs(i - map.get(nums[i])!) <= k) {
            // If yes, there is a duplicate within the specified distance 'k'
            return true;
        }

        // Store the current index 'i' as the value for the key 'nums[i]' in map.
        map.set(nums[i], i);
    }

    // If the loop completes without finding any duplicates, return false
    return false;
}

// Example
const array1 = [1, 2, 3, 4, 5];
const array2 = [1, 2, 3, 4, 1];
const k = 2;

console.log(containsNearbyDuplicateSlidingWindow(array1, k)); // Output: false
console.log(containsNearbyDuplicateSlidingWindow(array2, k));
