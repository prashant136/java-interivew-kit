// Helper function to perform binary search in a finite range
const binarySearch = (arr, target, start, end) => {
    while (start <= end) {
        let mid = Math.floor(start + (end - start) / 2);
        if (arr[mid] === target) {
            return mid; // Target found at mid index
        } else if (arr[mid] < target) {
            start = mid + 1; // Move start to the right
        } else {
            end = mid - 1; // Move end to the left
        }
    }
    return -1; // Target not found
};

// Function to find the position of the target in an infinite sorted array
const findPositionInInfiniteArray = (arr, target) => {
    let start = 0;
    let end = 1;

    // Expand the range exponentially until target is within range
    while (target > arr[end]) {
        start = end; // Move start to the current end
        end = end * 2; // Double the end index
    }

    // Apply binary search in the found range
    return binarySearch(arr, target, start, end);
};

// Example usage
let infiniteArray = [3, 5, 7, 10, 12, 15, 18, 20, 25, 30, 35, 40, 45, 50, 55, 100, '.......'];
let target = 18;
console.log(findPositionInInfiniteArray(infiniteArray, target)); // Output: Index of 18
