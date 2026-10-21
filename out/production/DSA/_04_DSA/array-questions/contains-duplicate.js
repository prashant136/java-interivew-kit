// Using a Set:
function containsDuplicateSet(nums) {
    const numSet = new Set(nums);
    return numSet.size !== nums.length;
}

// Sorting the Array:
function containsDuplicateSortingArray(nums) {
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length - 1; i++) {
        if (nums[i] === nums[i + 1]) {
            return true;
        }
    }
    return false;
}

// Using Hash Map:
function containsDuplicateHashMap(nums) {
    let map = {};
    for (let x of nums) {
        if (map[x]) {
            return true;
        }
        map[x] = true;
    }
    return false;
}

const array1 = [1, 2, 3, 4, 5];
const array2 = [1, 2, 3, 4, 1];

console.log(containsDuplicateHashMap(array1));
console.log(containsDuplicateHashMap(array2));
