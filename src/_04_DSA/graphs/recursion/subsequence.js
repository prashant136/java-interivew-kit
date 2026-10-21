// 👋 ----------- print all subsequence whose sum is k ------------
// function subsequnce(originalArr, target, i, ds, sum) {
//     //    base condition
//     if (i === originalArr.length) {
//         if (sum === target) {
//             console.log(ds);
//         }
//         return;
//     }
//     ds.push(originalArr[i]);
//     sum = sum + originalArr[i];
//     subsequnce(originalArr, target, i + 1, ds, sum);
//     ds.pop();
//     sum = sum - originalArr[i];
//     subsequnce(originalArr, target, i + 1, ds, sum);
// }
// let arr = [1, 2, 1];
// let target = 2;
// subsequnce(arr, target, 0, [], 0);

// 👋 ---------- print only one subsequence whose sum is k -----------
// function oneSubsequnce(originalArr, target, i, ds, sum) {
//     // base condition
//     if (i === originalArr.length) {
//         if (sum === target) {
//             console.log(ds);
//             return true;
//         }
//         return false;
//     }
//     ds.push(originalArr[i]);
//     sum = sum + originalArr[i];
//     if (oneSubsequnce(originalArr, target, i + 1, ds, sum)) {
//         return true;
//     }
//     ds.pop();
//     sum = sum - originalArr[i];
//     if (oneSubsequnce(originalArr, target, i + 1, ds, sum)) {
//         return true;
//     }
//     return false;
// }
// let arr = [1, 2, 1];
// let target = 2;
// oneSubsequnce(arr, target, 0, [], 0);

// 👋 ---------- count of subsequence whose sum is k -----------
function countSubsequence(originalArr, target, i, ds, sum) {
    // base condition
    if (i === originalArr.length) {
        if (sum === target) {
            return 1;
        }
        return 0;
    }
    ds.push(originalArr[i]);
    sum = sum + originalArr[i];
    let l = countSubsequence(originalArr, target, i + 1, ds, sum);
    ds.pop();
    sum = sum - originalArr[i];
    let r = countSubsequence(originalArr, target, i + 1, ds, sum);
    return l + r;
}
let arr = [1, 2, 1];
let target = 2;
console.log(countSubsequence(arr, target, 0, [], 0));
