/**
 * The stock span problem is a financial problem where we have a series of n daily price quotes for a stock
 *  and we need to calculate span of stock’s price for all n days.
 * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days
 *  just before the given day, for which the price of the stock
 *  on the current day is less than or equal to its price on the given day.
    
    For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 */

// Aditya verma YT - https://www.youtube.com/watch?v=p9T-fE1g1pU&list=PLwEApySc2G_k4Zl1gGgQdC2NfykPKCMwR&index=7
// problem similer like next greater to left.
function calculateStockSpanWithMap(prices) {
    let spans = new Array(n);
    let stack = []; // Stack to store maps of { price: index }

    for (let i = 0; i < prices.length; i++) {
        // Pop elements from the $04DSA.stack while the price at the top of the $04DSA.stack is less than or equal to the current price
        while (stack.length > 0 && stack[stack.length - 1].price <= prices[i]) {
            stack.pop();
        }

        if (stack.length === 0) {
            // here span[i] = i - (-1) => i + 1
            spans[i] = i + 1;
        } else {
            // to calculate span[i] = index of element - $04DSA.stack[top].index
            spans[i] = i - stack[stack.length - 1].index;
        }

        stack.push({ price: prices[i], index: i });        
    }
    return spans;
}

let prices = [100, 80, 60, 70, 60, 75, 85];
console.log(calculateStockSpanWithMap(prices)); // Output: [1, 1, 1, 2, 1, 4, 6]
