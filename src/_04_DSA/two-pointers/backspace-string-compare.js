/***
 * 
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
    Note that after backspacing an empty text, the text will continue empty.

    Example 1:
    Input: s = "ab#c", t = "ad#c"
    Output: true
    Explanation: Both s and t become "ac".

    Example 2:
    Input: s = "ab##", t = "c#d#"
    Output: true
    Explanation: Both s and t become "".

    Example 3:
    Input: s = "a#c", t = "b"
    Output: false
    Explanation: s becomes "c" while t becomes "b".

    Can you solve it in O(n) time and O(1) space?
 */

// ----------- brute force ------------
// function backspaceCompare(s, t) {
//     let sArr = s.split("");
//     let tArr = t.split("");

//     let sAfter = [];
//     let tAfter = [];

//     for (let i = 0; i < sArr.length; i++) {
//         if (sAfter.length && sArr[i] === "#") {
//             sAfter.pop();
//         } else {
//             sAfter.push(sArr[i]);
//         }
//     }

//     for (let i = 0; i < tArr.length; i++) {
//         if (tAfter.length && tArr[i] === "#") {
//             tAfter.pop();
//         } else {
//             tAfter.push(tArr[i]);
//         }
//     }

//     console.log(
//         sAfter.join(""),
//         tAfter.join(""),
//         sAfter.join("") === tAfter.join(""),
//         sAfter,
//         tAfter
//     );
//     return sAfter.join("") === tAfter.join("");
// }

function backspaceCompare(s, t) {
    function processString(input) {
        let result = [];

        for (let char of input) {
            if (char === "#" && result.length > 0) {
                result.pop();
            } else if (char !== "#") {
                result.push(char);
            }
        }

        return result.join("");
    }

    const sAfter = processString(s);
    const tAfter = processString(t);

    return sAfter === tAfter;
}

// let s = "ab#c",
//     t = "ad#c";

// let s = "ab##",
//     t = "c#d#";

// let s = "a#c",
//     t = "b";

const s = "y#fo##f";
const t = "y#f#o##f";
console.log(backspaceCompare(s, t));
