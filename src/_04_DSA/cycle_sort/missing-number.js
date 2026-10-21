/**
 *
 * If range => [0, N] -> every element will be at index = value
 * If range => [1, N] -> every element will be at index = value - 1
 *
 */

// 👉 Find All Numbers Disappeared in an Array
function findDisappearedNumbers(arr) {
    let i = 0;
    while (i < arr.length) {
        let corrent = arr[i] - 1;
        if (arr[i] !== arr[corrent]) {
            // swap
            [arr[i], arr[corrent]] = [arr[corrent], arr[i]];
        } else {
            // automatic handling duplicate
            i++;
        }
    }

    // find missing number (in sorted arr index === val-1)
    let missingNum = [];
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] !== i + 1) {
            missingNum.push(i + 1);
        }
    }
    return missingNum;
}

// let arr = [4, 3, 2, 7, 8, 2, 3, 1];
let arr = [1,1]
console.log(findDisappearedNumbers(arr));
