// https://leetcode.com/problems/single-element-in-a-sorted-array/description/

// If (i % 2 == 0 and arr[i] == arr[i+1]) or (i%2 == 1 and arr[i] == arr[i-1]), we are in the left half.
// If (i % 2 == 0 and arr[i] == arr[i-1]) or (i%2 == 1 and arr[i] == arr[i+1]), we are in the right half.
function singleNonDuplicate(arr) {
    let low = 1, high = n - 2;
    while (low <= high) {
        let mid = Math.floor((low + high) / 2);

        // If arr[mid] is the single element:
        if (arr[mid] !== arr[mid + 1] && arr[mid] !== arr[mid - 1]) {
            return arr[mid];
        }

        // We are in the left:
        if ((mid % 2 === 1 && arr[mid] === arr[mid - 1])
                || (mid % 2 === 0 && arr[mid] === arr[mid + 1])) {
            // Eliminate the left half:
            low = mid + 1;
        }
        // We are in the right:
        else {
            // Eliminate the right half:
            high = mid - 1;
        }
    }
    return -1;
}

let arr = [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6];
console.log(singleNonDuplicate(arr));


