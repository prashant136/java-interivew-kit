/***
 * 
 * Peak Index in a Mountain Array
    An array arr is a mountain if the following properties hold:
    arr.length >= 3
    There exists some i with 0 < i < arr.length - 1 such that:
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
    Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].

    You must solve it in O(log(arr.length)) time complexity.

    Example 1:
    Input: arr = [0,1,0]
    Output: 1

    Example 2:
    Input: arr = [0,2,1,0]
    Output: 1

    Example 3:
    Input: arr = [0,10,5,2]
    Output: 1
 */

const peakIndexInMountainArray = (arr) => {
    let start = 0;
    let end = arr.length - 1;

    while (start < end) {
        let mid = Math.floor(start + (end - start) / 2);
        if (arr[mid] < arr[mid + 1]) {
            // you are in descending part of array
            // this may be the ans, but look at left
            start = mid + 1; // this is why end != mid - 1
        } else if (arr[mid] > arr[mid + 1]) {
            // you are in ascending part of array
            end = mid; // because we know that mid+1 element > mid element
        }
    }
    // in the end, start == end and pointing to the largest number
    // start and end are always trying to find max element in the above 2 checks
    // hence, when they are pointing to just one element, that is the max one
    return start; // or end both are same
};

let arr = [1, 2, 3, 5, 6, 4, 3, 2];
console.log(peakIndexInMountainArray(arr));
