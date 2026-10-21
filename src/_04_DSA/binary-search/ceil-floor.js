// start pointer -> ceil of number
const ceiling = (arr, target) => {
    let start = 0;
    let end = arr.length - 1;

    while (start <= end) {
        let mid = start + (end - start) / 2;
        if (target > arr[mid]) {
            start = mid + 1;
        } else if (target < arr[mid]) {
            end = mid - 1;
        } else {
            return arr[mid];
        }
    }
    console.log("start", start);
    return arr[start];
};
 
// end pointer -> floor of number
const floor = (arr, target) => {
    let start = 0;
    let end = arr.length - 1;

    while (start <= end) {
        let mid = start + (end - start) / 2;
        if (target === arr[mid]) {
            return arr[mid];
        } else if (target < arr[mid]) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    return arr[end];
};

let nums = [-12, -4, 0, 3, 4, 10, 15, 16, 21, 22, 30];
let target = 20;
console.log(ceiling(nums, target));
// console.log(floor(nums, target));
