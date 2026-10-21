/**

    num  = "1432219",  k = 3
    $04DSA.stack starts empty

    | Digit | Stack | k | Comment                |
    | ----- | ----- | - | ---------------------- |
    | '1'   | 1     | 3 | push                   |
    | '4'   | 1 4   | 3 | push                   |
    | '3'   | 1 3   | 2 | remove 4 because 4 > 3 |
    | '2'   | 1 2   | 1 | remove 3 because 3 > 2 |
    | '2'   | 1 2 2 | 1 | equal so stop popping  |
    | '1'   | 1 1   | 0 | remove 2 because 2 > 1 |
    | '9'   | 1 1 9 | 0 | no removals left       |

    Stack = [1,1,9]
    Result = "119"

    👀 Leading Zeros - We must remove them.

    num = "10200", k = 1
    result = "0200" → strip → "200"
 */

function removeKdigits(num: string, k: number): string {
    const stack: string[] = [];

    for (let ele of num) {
        while (k > 0 && stack.length && stack[stack.length - 1] > ele) {
            stack.pop();
            k--;
        }
        stack.push(ele);
    }

    while (k > 0) {
        stack.pop();
        k--;
    }

    let ans = stack.join("");

    // Remove leading zeros
    while(ans.length && ans[0] === '0') {
        ans = ans.slice(1);
    }
    return ans === '' ? '0' : ans;
}    