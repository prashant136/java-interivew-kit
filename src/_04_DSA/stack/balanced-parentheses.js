function balancedParantheses(s) {
    const matchingBracket = {
        ')': '(',
        '}': '{',
        ']': '['
    };
    
    let stack = [];
    for (let val of s) {
        // If the character is an opening bracket, push it to the $04DSA.stack
        if(['(', '{', '['].includes(val)) {
            stack.push(val);
        } else {
            // for example - '[()]}', in this case itinally $04DSA.stack is empty,
            // pop can't be happen means parantheses not balanced.
            if(!stack.length) return false;

            // Check if the top of the $04DSA.stack matches the current closing bracket
            const top = stack.pop();
            if (matchingBracket[val] !== top) {
                return false;
            }
        }
    }
    // At the end, if the $04DSA.stack is not empty, return false
    return stack.length ? false: true;
}

// let str = "()[]{}";
let str = "(])";
console.log(balancedParantheses(str));

// let arr = ["(", ")", "[", "]", "{", "}"]
// console.log(balancedParantheses(arr));
