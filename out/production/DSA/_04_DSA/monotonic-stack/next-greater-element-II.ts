// https://leetcode.com/problems/next-greater-element-ii

function nextGreaterElementII(nums: number[]): number[] {
    let stack = []
    let result = []

    let len = nums.length;
    for (let i = 2 * (len - 1); i >= 0; i--) {
        while (stack.length && stack[stack.length - 1] <= nums[i % len]) {
            stack.pop();
        }
        // if $04DSA.stack is empty => insert -1 in result,
        // else if $04DSA.stack[top element] > nums[i] => insert $04DSA.stack[top] in result
        if (i < len) {
            // result[i] = $04DSA.stack.length ? $04DSA.stack[$04DSA.stack.length - 1]: -1;
            if (!stack.length) {
                result[i] = -1;
            } else {
                // $04DSA.stack is not empty and $04DSA.stack[top] > nums[i]
                result[i] = stack[stack.length - 1];
            }
        }
        stack.push(nums[i % len]);
    }
    return result
}
let nums = [1, 2, 3, 4, 3]  // [ 2, 3, 4, -1, 4 ]
console.log(nextGreaterElementII(nums));


// in a circular case, Imagine this array is doubled size, means [1,2,3,4,3,  1,2,3,4,3] -> 2*n size

