/**
    You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

    Return the length of the longest substring containing the same letter you can get after performing the above operations.


    Input: s = "ABAB", k = 2
    Output: 4
    Explanation: Replace the two 'A's with two 'B's or vice versa.

    Input: s = "AABABBA", k = 1
    Output: 4
    Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    The substring "BBBB" has the longest repeating letters, which is 4.
    There may exists other ways to achieve this answer too.
 */

function characterReplacement(s: string, k: number): number {
    let map = new Map<string, number>();
    let i = 0;
    let maxFreq = 0;
    let maxLen = 0;

    for (let j = 0; j < s.length; j++) {

        map.set(s[j], (map.get(s[j]) || 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(s[j])!);

        while ((j - i + 1) - maxFreq > k) {
            map.set(s[i], map.get(s[i])! - 1);
            i++;
        }

        maxLen = Math.max(maxLen, j - i + 1);
    }

    return maxLen;
}

const s = "AABABBA"
const k = 1
console.log(characterReplacement(s, k));

/*
🧩 Mental Template for This Category -
    
    for (expand window) {

        update frequency

        while (window invalid) {
            shrink window
        }

        update answer
    }
*/

/*
    🔍 Step-By-Step Dry Run -

    Initial State
        i = 0
        maxFreq = 0
        maxLen = 0
        map = {}

        ----------------------------------------
        
        👉 j = 0 → 'A'
        
        Window: "A"

        map = { A:1 }
        maxFreq = 1
        windowSize = 1
        1 - 1 = 0 <= k (valid)

        maxLen = 1

        ----------------------------------------

        👉 j = 1 → 'A'

        Window: "AA"

        map = { A:2 }
        maxFreq = 2
        windowSize = 2
        2 - 2 = 0 <= k

        maxLen = 2

        ----------------------------------------

        👉 j = 2 → 'B'

        Window: "AAB"

        map = { A:2, B:1 }
        maxFreq = 2
        windowSize = 3
        3 - 2 = 1 <= k

        Valid (replace B → A)

        maxLen = 3

        ---------------------------------------

        👉 j = 3 → 'A'

        Window: "AABA"

        map = { A:3, B:1 }
        maxFreq = 3
        windowSize = 4
        4 - 3 = 1 <= k

        Valid

        maxLen = 4

        --------------------------------------

        👉 j = 4 → 'B'

        Window: "AABAB"

        map = { A:3, B:2 }
        maxFreq = 3
        windowSize = 5
        5 - 3 = 2 > k ❌

        Invalid → shrink

        Remove s[i] = 'A'

        map = { A:2, B:2 }
        i = 1
        windowSize = 4
        4 - 3 = 1 <= k

        Valid again

        maxLen = 4

        -------------------------------------

        👉 j = 5 → 'B'

        Window: "ABABB"

        map = { A:2, B:3 }
        maxFreq = 3
        windowSize = 5
        5 - 3 = 2 > k ❌

        Shrink:

        Remove s[i] = 'A'

        map = { A:1, B:3 }
        i = 2
        windowSize = 4
        4 - 3 = 1 <= k

        Valid

        maxLen = 4

        -------------------------------------

        👉 j = 6 → 'A'

        Window: "BABBA"

        map = { A:2, B:3 }
        maxFreq = 3
        windowSize = 5
        5 - 3 = 2 > k ❌

        Shrink:

        Remove s[i] = 'B'

        map = { A:2, B:2 }
        i = 3
        windowSize = 4
        4 - 3 = 1 <= k

        Valid

        maxLen = 4

        ------------------------------------

        🎯 Final Answer - 4

        Longest valid substring examples:
        "AABA"
        "ABBA"
*/