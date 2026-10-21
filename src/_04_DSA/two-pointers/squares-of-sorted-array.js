/***
 * 
 * Given an integer array nums sorted in non-decreasing order, 
 * return an array of the squares of each number sorted in non-decreasing order.

    Example 1:
    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After $04DSA.sorting, it becomes [0,1,9,16,100].

    Example 2:
    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
 
    Squaring each element O(n) and $04DSA.sorting O(nlogn) the new array is very trivial, overall complexity - O(nlogn)
    could you find an O(n) solution using a different approach?
 */

const sortedSquares = (arr) => {
    let ans = [];
    let l = 0;
    let r = arr.length - 1;
    while (l <= r) {
        let sqrL = arr[l] * arr[l];
        let sqrR = arr[r] * arr[r];
        if (sqrL < sqrR) {
            ans.unshift(sqrR);
            r--;
        } else {
            ans.unshift(sqrL);
            l++;
        }
    }
    return ans;
};

// let arr = [-4, -3, 0, 2, 3];
let arr = [-7, -3, 2, 3, 11];
console.log(sortedSquares(arr));
