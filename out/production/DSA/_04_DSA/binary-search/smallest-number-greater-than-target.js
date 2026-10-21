/***
 * 
 * 
 * You are given an array of characters letters that is sorted in non-decreasing order,
 *  and a character target. There are at least two different characters in letters.
    Return the smallest character in letters that is lexicographically greater than target.
    If such a character does not exist, return the first character in letters.


    Example 1:
    Input: letters = ["c","f","j"], target = "a"
    Output: "c"
    Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.

    Example 2:
    Input: letters = ["c","f","j"], target = "c"
    Output: "f"
    Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.

    Example 3:
    Input: letters = ["x","x","y","y"], target = "z"
    Output: "x"
    Explanation: There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
 */

const nextGreatestLetter = (letters, target) => {
    let start = 0;
    let end = letters.length - 1;
    while (start <= end) {
        let mid = Math.floor(start + (end - start) / 2);
        if (letters[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    // Wrap around if the end index goes beyond the array size
    return letters[start % letters.length];         // ceil value
    // return start < letters.length ? letters[start] : letters[0];
};

let arr = ["x", "x", "y", "y"];
let target = "z";
console.log(nextGreatestLetter(arr, target));

// -----------------👇 -------------------
function findLargest(arr, target) {
    let start = 0;
    let end = arr.length - 1;

    let ans;
    while (start <= end) {
        let mid = Math.floor((start + end) / 2);
        if (arr[mid] === target) {
            ans = mid;
            break;
        } else if (arr[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    console.log({ start, ans });
    return start === arr.length - 1 ? arr[0] : arr[ans + 1];
}
