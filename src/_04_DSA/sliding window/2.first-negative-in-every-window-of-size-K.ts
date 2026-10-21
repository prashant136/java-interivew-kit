
// 👋 ------- brute force -------- time - O(nk), space - O(1)
// const firstNegativeInteger = (arr, k) => {
//     for (let i = 0; i+k < arr.length; i++) {
//         for (let j = i; j < i+k; j++) {
//             if (arr[j] < 0) {
//                 return true;
//             }
//         }
//     }
//     return false;
// }

// let arr = [12, -1, -7, 8, -15, 30, 16, 28];
// let k = 4
// console.log(firstNegativeInteger(arr, k));

function findNegative(nums: number[], i: number, j: number) {
    while (i <= j) {
        if (nums[i] < 0) return nums[i]
        i++;
    }
    return 0    // else return 0
}

const firstNegativeIntegerSlidingWindow = (nums: number[], k: number): number[] => {
    let i = 0;
    let result = [];

    for (let j = 0; j < arr.length; j++) {
        // window size is exceeding
        if (j - i + 1 > k) {
            i++;
        }

        // if window size is eqaul
        if (j - i + 1 === k) {
            result.push(findNegative(nums, i, j));
        }
    }

    return result;
}

let arr = [12, -1, -7, 8, 15, 30, 16, -28];
let k = 4
console.log(firstNegativeIntegerSlidingWindow(arr, k));  // [ -1, -1, -7, 0, -28 ]


