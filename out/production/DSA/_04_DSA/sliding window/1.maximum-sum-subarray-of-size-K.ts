
function maxSumSubArray(arr: number[], k: number): number {
    let result = - Infinity;
    let i = 0;
    let sum = 0

    for (let j = 0; j < arr.length; j++) {
        // calculate sum at every itertion
        sum = sum + arr[j];

        // window size is exceeding
        if (j - i + 1 > k) {
            sum = sum - arr[i];
            i++;
        }

        // if window size is eqaul
        if (j - i + 1 === k) {
            result = Math.max(result, sum);
        }
    }

    return result;
}

const arr = [2, 5, 1, 8, 2, 9, 1];
const k = 3;
console.log(maxSumSubArray(arr, k));
