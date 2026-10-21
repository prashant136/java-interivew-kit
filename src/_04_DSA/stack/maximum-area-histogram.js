// Find the largest rectangular area possible in a given histogram
//  where the largest rectangle can be made of a number of contiguous bars. 
// For simplicity, assume that all bars have same width and the width is 1 unit.

// find previous smaller and next smaller for evey element. then calculate area
// https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-using-stack/
// https://www.youtube.com/watch?v=J2X70jj_I1o&list=PLwEApySc2G_k4Zl1gGgQdC2NfykPKCMwR&index=7  -  Aditya verma
function nextSmaller(arr) {
    let stack = []
    let next = new Array(arr.length).fill(-1);

    for (let i = arr.length - 1; i >= 0; i--) {
        while (stack.length && stack[stack.length - 1].area >= arr[i]) {
            stack.pop();
        }
        if (!stack.length) {
            next[i] = -1;
        } else {
            next[i] = stack[stack.length - 1].index;
        }
        stack.push({ area: arr[i], index: i });
    }
    return next
}

function prevSmaller(arr) {
    let stack = []
    let prev = new Array(arr.length).fill(-1);

    for (let i = 0; i < arr.length; i++) {
        while (stack.length && stack[stack.length - 1].area >= arr[i]) {
            stack.pop();
        }
        if (!stack.length) {
            prev[i] = -1;
        } else {
            prev[i] = stack[stack.length - 1].index;
        }
        stack.push({ area: arr[i], index: i });
    }
    return prev
}


function calcutaeAreaOFHistogram(arr) {
    let prev = prevSmaller(arr);
    let next = nextSmaller(arr);
    let ans = 0;

    for (let i = 0; i < arr.length; i++) {
        const width = next[i] - prev[i] - 1;
        const area = arr[i] * width;
        ans = Math.max(ans, area);
    }
    return ans;
}


let nums = [60, 20, 50, 40, 10, 50, 60];
console.log(nextSmaller(nums));
console.log(prevSmaller(nums));
console.log(calcutaeAreaOFHistogram(nums));
