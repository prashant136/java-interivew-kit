
function nearestGreaterToLeft(arr: number[]): number[] {
    let stack: number[] = []
    let ans: number[] = []

    for (let i = 0; i < arr.length; i++) {
        // Remove all elements smaller or equal to current
        while (stack.length && stack[stack.length - 1] <= arr[i]) {
            stack.pop();
        }

        // If $04DSA.stack is empty, no greater element on left
        if (!stack.length) {
            ans.push(-1);
        } else if (stack[stack.length - 1] > arr[i]) {
            ans.push(stack[stack.length - 1]);
        }

        // Push current element
        stack.push(arr[i]);

    }
    return ans;
}

const arr = [1, 3, 2, 4];
console.log(nearestGreaterToLeft(arr));
// Output: [-1, -1, 3, -1]