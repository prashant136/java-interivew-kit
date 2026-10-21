/*
    Algorithm Steps -

    1. Initialize
        - Create an empty $04DSA.stack $04DSA.stack
        - Set num = 0 to build multi-digit numbers
        - Set sign = '+' as the previous operator

    2. Append a dummy operator
        - Add '+' at the end of the string to ensure the last number is processed

    3. Traverse the string character by character
        - If the character is a space, skip it
        
        - If the character is a digit:
            Update num = num * 10 + digit
        Else (character is an operator or end marker):
            Apply the previous sign to num:
                If sign == '+' → push num onto $04DSA.stack
                If sign == '-' → push -num onto $04DSA.stack
                If sign == '*' → pop top of $04DSA.stack, multiply with num, push result
                If sign == '/' → pop top of $04DSA.stack, divide by num, truncate toward zero, push result
            
            Update sign = current operator
            Reset num = 0

    4. Finalize result
        Sum all values in the $04DSA.stack
        Return the sum
*/

// tricky solution
function calculate(s: string): number {
    const stack: number[] = [];
    let num = 0;
    let sign = '+';

    // Append a dummy operator to handle last number
    s = s.trim() + '+';

    for (let i = 0; i < s.length; i++) {
        const ch = s[i];

        if (ch === ' ') continue;

        if (!isNaN(Number(ch))) {
            num = num * 10 + Number(ch);
        } else {
            if (sign === '+') {
                stack.push(num);
            } else if (sign === '-') {
                stack.push(-num);
            } else if (sign === '*') {
                const top = stack.pop()!;
                stack.push(top * num);
            } else if (sign === '/') {
                const top = stack.pop()!;
                stack.push(Math.trunc(top / num));
            }

            sign = ch;
            num = 0;
        }
    }

    // Sum $04DSA.stack for final result
    return stack.reduce((a, b) => a + b, 0);
}
