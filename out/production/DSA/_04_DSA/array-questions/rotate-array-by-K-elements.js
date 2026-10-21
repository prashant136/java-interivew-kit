/**
 * array is divide into two part [0....n-k-1] [n-k....n-1]
    Steps:- Reverse the first part,
        then second part and
        finally the whole array
 */

function reverse(arr, start, end) {
    while (start <= end) {
        let temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }
}
// Function to Rotate k elements to left
function Rotateeletoleft(arr, n, k) {
    // Reverse first k elements
    reverse(arr, 0, k - 1);
    // Reverse last n-k elements
    reverse(arr, k, n - 1);
    // Reverse whole array
    reverse(arr, 0, n - 1);
}
// Function to Rotate k elements to right
function Rotateeletoright(arr, n, k) {
    // Reverse first k elements
    reverse(arr, 0, n - k - 1);
    // Reverse last n-k elements
    reverse(arr, n - k, n - 1);
    // Reverse whole array
    reverse(arr, 0, n - 1);
}

let arr = [1, 2, 3, 4, 5, 6, 7];
let k = 2;
// Rotateeletoleft(arr, arr.length, k);    // -> [3 4 5 6 7 1 2]
// Rotateeletoright(arr, arr.length, k);   // -> [6 7 1 2 3 4 5]
console.log(arr);
