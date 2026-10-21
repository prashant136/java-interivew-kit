// Find Minimum in Rotated Sorted Array
// https://www.youtube.com/watch?v=nhEMDKMB44g&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=7 - take u forwoard

function minimun(arr) {
    let low = 0;
    let high = arr.length - 1;
    let ans = Infinity;

    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        // search space is already sorted
        // then always arr[low] is will be smaller
        // in that search space
        if (arr[low] <= arr[high]) {
            ans = Math.min(ans, arr[low]);
            break;
        }
        if (arr[low] <= arr[mid]) {
            // left part is sorted, minimum value of sorted array will be first element of that portion
            ans = Math.min(ans, arr[low]);
            low = mid + 1;
        } else {
            ans = Math.min(ans, arr[mid]);
            high = mid - 1;
        }
    }
    return ans;
}

let arr = [9, 15, 2, 3, 6];
console.log(minimun(arr));
