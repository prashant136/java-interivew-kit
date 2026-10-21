function merge(arr, low, high, mid) {
    let temp = [];
    let left = low;
    let right = mid + 1;

    // Merge two sorted arrays into temp array
    while (left <= mid && right <= high) {
        if (arr[left] <= arr[right]) {
            temp.push(arr[left]);
            left++;
        } else {
            temp.push(arr[right]);
            right++;
        }
    }

    // Copy the remaining elements from left half
    while (left <= mid) {
        temp.push(arr[left]);
        left++;
    }

    // Copy the remaining elements from right half
    while (right <= high) {
        temp.push(arr[right]);
        right++;
    }

    // Copy sorted elements back to original array
    for (let i = low, j = 0; i <= high; i++, j++) {
        arr[i] = temp[j];
    }
}

function mergeSortHelper(arr, low, high) {
    if (low == high) {
        return;
    }
    let mid = Math.floor((low + high) / 2);
    // Divide the array into two halves and sort them recursively
    mergeSortHelper(arr, low, mid);
    mergeSortHelper(arr, mid + 1, high);
    // Merge the sorted halves
    merge(arr, low, high, mid);
}

function mergeSort(arr) {
    mergeSortHelper(arr, 0, arr.length - 1);
    return arr;
}

// let arr = [12, 11, 13, 5, 6, 7];
// mergeSort(arr);
// console.log("Sorted array:", arr);

// quick sort -
// pick a pivot - (could be first, last, middle or any random element)
function partitionFn(arr, low, high) {
    let pivot = arr[low];
    let i = low;
    let j = high;
    while (i < j) {
        while (arr[i] <= pivot && i <= high-1) {
            console.log('i', i);
            i++;
        }
        while (arr[j] > pivot && j >= low+1) {
            console.log('j', j);
            j--;
        }
        if (i < j) {
            // swap
            [arr[i], arr[j]] = [arr[j], arr[i]]
        }
    }
    console.log({low, j});
    [arr[low], arr[j]] = [arr[j], arr[low]]
    console.log({low, j});
    return j;
}
function helperQS(arr, low, high) {
    if (low >= high) {
        return;
    }
    const partition = partitionFn(arr, low, high);
    console.log({partition});
    helperQS(arr, low, partition - 1);
    helperQS(arr, partition + 1, high);
}

function quickSort(arr) {
    helperQS(arr, 0, arr.length - 1);
}

let arr = [12, 11, 13, 5, 6, 7];
quickSort(arr);
console.log("Sorted array:", arr);
