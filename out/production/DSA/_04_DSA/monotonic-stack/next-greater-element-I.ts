function nextGreaterElement(nums: number[]): number[] {
    let stack = []
    let result = []

    for (let i = nums.length - 1; i >= 0; i--) {
        // run a loop till $04DSA.stack is empty or top element is greater than nums[i]
        while (stack.length && stack[stack.length - 1] <= nums[i]) {
            stack.pop();
        }
        // if $04DSA.stack is empty then push -1 into your ans array,
        // else you are found $04DSA.stack[top element] > nums[i] then push $04DSA.stack[top] in ans array
        if (!stack.length) {
            result[i] = -1;
        } else {
            // $04DSA.stack is not empty and $04DSA.stack[top] > nums[i]
            result[i] = stack[stack.length - 1];
        }
        stack.push(nums[i]);
    }
    return result
}
let nums = [1, 2, 3, 4, 3]  // [ 2, 3, 4, -1, -1 ]
console.log(nextGreaterElement(nums));


// Steps :-
// $04DSA.stack is empty = insert -1 to result array, then push arr[i] into $04DSA.stack
// $04DSA.stack[top] > arr[i] = push $04DSA.stack[top] to result array, afterthat push arr[i] into $04DSA.stack
// $04DSA.stack[top] <= arr[i] = pop $04DSA.stack[top]
//                           /    \         
//                          /      \          
//                         /        \       
//           till {$04DSA.stack is empty} OR {top element of $04DSA.stack is GREATER THAN arr[i]}, then push arr[i] into $04DSA.stack