const orderAgnostic = (arr, target) => {
    let start = 0;
    let end = arr.length - 1;

    let isAgnostic = arr[start] <  arr[end];

    let mid;
    while (start <= end) {
        mid = start + (end - start) / 2;
        if (target === arr[mid]) {
            return arr[mid];
        }
        
        if (isAgnostic) {
            if (target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            } 
        } else {
            if (target < arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            } 
        }
    }
    return arr[mid] ?? -1;
};

// let nums = [-12, -4, 0, 3, 4, 10, 15, 16, 21, 22, 30];
let nums = [50, 40, 32, 16, 10, 8, -2];
let target = 18;
console.log(orderAgnostic(nums, target));