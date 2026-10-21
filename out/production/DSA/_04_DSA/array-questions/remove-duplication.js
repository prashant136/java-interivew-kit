// using hashset - brute force
// Time complexity: O(n*log(n))+O(n)
// Space Complexity: O(n)
function removeDuplicates(arr) {
    let set = new Set(arr);
    let uniqueArr = Array.from(set);
    return uniqueArr;
}

// two pointers -
// Time Complexity: O(N)
// Space Complexity: O(1)
function removeDuplicatesTwoPointer(arr) {
    let k = 0;
    for (let j = 1; j < arr.length; j++) {
        if (arr[k] !== arr[j]) {
            k++;
            arr[k] = arr[j];
        }
    }
    return arr.slice(0, k + 1);
}


const arr = [1, 1, 2, 2, 2, 3, 3];
// console.log(removeDuplicates(arr));
// console.log(removeDuplicatesTwoPointer(arr));
