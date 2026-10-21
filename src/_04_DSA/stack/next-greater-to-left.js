function nextGreaterToLeft(arr) {
    let stack = [];
    let result = new Array(arr.length).fill(-1);

    for (let i = 0; i < arr.length; i++) {
        while(stack.length && stack[stack.length-1] <= arr[i]) {
            stack.pop();
        }

        if(!stack.length) {
            result[i] = -1;
        } else {
            result[i] = stack[stack.length-1];
        }
        stack.push(arr[i]);
    }
    return result
}

let arr = [1,3,2,4];
console.log(nextGreaterToLeft(arr));