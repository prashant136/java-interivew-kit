// function printOnetoN(i, n){
//     if (i > n) {
//         return;
//     }
//     console.log(i);
//     printOnetoN(i + 1, n);
// }
// printOnetoN(1, 5);

// function printOnetoN(i, n){
//     if (i < n) {
//         return;
//     }
//     printOnetoN(i - 1, n);
//     console.log(i);
// }
// printOnetoN(5, 1);

// function printNtoOne(i, n){
//     if (i < n) {
//         return;
//     }
//     console.log(i);
//     printNtoOne(i - 1, n);
// }
// printNtoOne(5, 1);

// ⛈️ 👋 sum of n number in recursion
// ----- paramenterised way -----
// function sum(i, ans) {
//     if (i == 0) {
//         console.log(ans);
//         return;
//     }
//     sum(i - 1, ans+i);
// }
// sum(5, 0);

// ----- functional way -----
// function sum(n) {
//     if (n == 0) {
//         return 0;
//     }
//     return n + sum(n - 1);
// }
// console.log(sum(5));

// -------- factorial of n --------
// function factorial(n) {
//     if (n == 0) {
//         return 1;
//     }
//     return n * factorial(n - 1);
// }
// console.log(factorial(5));

// // ✋ ##### reverse array #####
// function reverse(arr, l, r) {
//     if (l >= r) {
//         console.log(arr);
//         return;
//     }
//     [arr[l], arr[r]] = [arr[r], arr[l]];
//     reverse(arr, l + 1, r - 1);
// }

// function reverseII(arr, i, n) {
//     if (i >= n/2) {
//         console.log(arr);
//         return;
//     }
//     [arr[i], arr[n-i-1]] = [arr[n-i-1], arr[i]];
//     reverseII(arr, i+1, n);
// }
// let arr = [1, 3, 5, 10, -2];
// reverse(arr, 0, arr.length);
// reverseII(arr, 0, arr.length);

// ------- fibonacci ------
// function fibonacci(n) {
//     if (n <= 1) {
//         return n;
//     }
//     return fibonacci(n - 1) + fibonacci(n - 2);
// }
// console.log(fibonacci(50));

//  using DP
function fib(n) {
    let arr = new Array(n + 2); // 1 extra to handle case, n = 0
    arr[0] = 0;
    arr[1] = 1;
    for (let i = 2; i <= n; i++) {
        arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
}
console.log(fib(50));
