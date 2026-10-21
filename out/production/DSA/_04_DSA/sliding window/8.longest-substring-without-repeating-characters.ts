/***
 * 
 * Given a string s, find the length of the longest substring without repeating characters.

    Example 1:
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.

    Example 2:
    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

    Example 3:
    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */ 

// 🎲 👑 ------ slidig window and set -------
function longestSubstringWithoutRepeating(s: string): number {
    let set = new Set<string>();
    let i = 0;
    let maxLen = 0;

    for (let j = 0; j < s.length; j++) {

        // shrink window if duplicate found
        while (set.has(s[j])) {
            set.delete(s[i]);
            i++;
        }

        set.add(s[j]);
        maxLen = Math.max(maxLen, j - i + 1);
    }

    return maxLen;
}
const inputString = "abcabcbb";
console.log(longestSubstringWithoutRepeating(inputString));



