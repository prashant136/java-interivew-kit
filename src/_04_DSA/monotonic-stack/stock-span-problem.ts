/*
    Given an array arr[] of daily stock prices, the stock span for the i-th day is the count of consecutive days up to and including day i, such that each of those days had a stock price less than or equal to the price on day i.

    Examples:

    Input: arr[] = [100, 80, 60, 120]
    Output: [1, 1, 1, 4]
    Explanation: For 100, there are no previous higher prices, so span = 1. For 80 and 60, each is smaller than the previous, so their spans remain 1. For 120, it is greater than all earlier prices (100, 80, 60), so the span extends back across all four days, giving span = 4.

    Input: arr[] = [10, 4, 5, 90, 120, 80]
    Output: [1, 1, 2, 4, 5, 1]
    Explanation: For 10 and 4, no earlier prices are smaller, so span = 1 each. For 5, it is greater than 4, so span = 2. For 90, it is greater than 10, 5, and 4, so span = 4. For 120, it is greater than all previous prices, giving span = 5. Finally, 80 is smaller than 120, so span = 1.
*/

function stockSpan(arr: number[]): number[] {
    let result = []
    for (let i = 0; i < arr.length; i++) {
        let count = 0
        for (let j = i; j >= 0; j--) {
            if (arr[j] <= arr[i]) {  // less than or equal to as question said
                count++;
            } else {
                break;
            }
        }
        result.push(count);
    }
    return result;
}

// const arr = [10, 4, 5, 90, 120, 80];
// console.log(stockSpan(arr));    // [1, 1, 2, 4, 5, 1]


/*
 * if there are two loops ->
    for(i -> some condition) {
        for(j -> depend on i) {

        }
    }
    // there is a high chance for $04DSA.stack.
 * 
 */

function stockOnline(arr: number[]) {
    let stack: number[] = [];
    let result: number[] = [];

    for (let i = 0; i < arr.length; i++) {
        while (stack.length && arr[stack[stack.length - 1]] <= arr[i]) {
            stack.pop();
        }

        if (!stack.length) {
            result.push(i + 1);
        } else {
            result.push(i - stack[stack.length - 1]);
        }
        stack.push(i);
    }
    return result;
}

const arr = [100, 80, 60, 70, 60, 75, 85];
console.log(stockOnline(arr));   // [1, 1, 1, 2, 1, 4, 6]