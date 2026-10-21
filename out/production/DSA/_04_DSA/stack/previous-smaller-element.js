function previousSmaller(arr) {
    let stack = [];
    let result = new Array(arr.length).fill(-1);

    for (let i = 0; i < arr.length; i++) {
        while(stack.length && stack[stack.length-1] >= arr[i]) {
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

const arr = [5,7,9,6,7,4,5,1,3,7];
console.log(previousSmaller(arr));
