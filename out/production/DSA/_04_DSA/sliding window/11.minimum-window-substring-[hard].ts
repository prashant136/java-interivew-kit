/*
    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

    The testcases will be generated such that the answer is unique.

    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

    Input: s = "a", t = "a"
    Output: "a"
    Explanation: The entire string s is the minimum window.

    Input: s = "a", t = "aa"
    Output: ""
    Explanation: Both 'a's from t must be included in the window.
    Since the largest window of s only has one 'a', return empty string.

    Constraints:

    m == s.length
    n == t.length
    1 <= m, n <= 105
    s and t consist of uppercase and lowercase English letters.
*/

// https://www.youtube.com/watch?v=iwv1llyN6mo&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=13

function minWindow(s: string, t: string): string {
    if (t.length === 0) return "";

    const map = new Map<string, number>();

    // Step 1: build frequency map
    for (let c of t) {
        map.set(c, (map.get(c) || 0) + 1);
    }

    let remaining = t.length;
    let i = 0;
    let minLen = Infinity;
    let start = 0;

    for (let j = 0; j < s.length; j++) {
        const rightChar = s[j];

        if (map.has(rightChar)) {
            map.set(rightChar, map.get(rightChar)! - 1);

            if (map.get(rightChar)! >= 0) {
                remaining--;
            }
        }

        // When all chars matched
        while (remaining === 0) {

            // update answer
            if (j - i + 1 < minLen) {
                minLen = j - i + 1;
                start = i;
            }

            const leftChar = s[i];

            if (map.has(leftChar)) {
                map.set(leftChar, map.get(leftChar)! + 1);

                if (map.get(leftChar)! > 0) {
                    remaining++;
                }
            }

            i++;
        }
    }

    return minLen === Infinity ? "" : s.substring(start, start + minLen);
}

const s = "ADOBECODEBANC"
const t = "ABC"
console.log(minWindow(s, t));

/*
    Perfect 🔥

    s = "ADOBECODEBANC"
    t = "ABC"

    ✅ Initial Setup
    map = { A:1, B:1, C:1 }
    remaining = 3
    i = 0
    minLen = ∞
    start = 0


    🔎 Step-by-Step Execution -

    👉 j = 0 → 'A'
    map[A] = 1 → 0
    remaining = 2

    Window: "A"

    ------------------------------

    👉 j = 1 → 'D'

    Not in map → ignore

    Window: "AD"

    ------------------------------
    
    👉 j = 2 → 'O'

    Ignore

    ------------------------------

    👉 j = 3 → 'B'
    map[B] = 1 → 0
    remaining = 1

    Window: "ADOB"

    -----------------------------

    👉 j = 4 → 'E'

    Ignore

    -----------------------------

    👉 j = 5 → 'C'
    map[C] = 1 → 0
    remaining = 0 🎉

    Window: "ADOBEC"

    Now window is VALID (remaining === 0)

    ----------------------------

    🔁 Start Shrinking
    Current window: [0..5] = "ADOBEC"

    Length = 6 → update

    minLen = 6
    start = 0

    ------------------------------

    Shrink i = 0 → 'A'
    map[A] = 0 → 1
    remaining = 1 ❌ (window invalid)
    i = 1

    Stop shrinking.

    -------------------------------

    👉 Continue Expanding
    👉 j = 6 → 'O'

    Ignore

    --------------------------------
    👉 j = 7 → 'D'

    Ignore

    --------------------------------

    👉 j = 8 → 'E'

    Ignore

    --------------------------------

    👉 j = 9 → 'B'
    map[B] = 0 → -1
    remaining stays 1

    Extra B (negative means redundant)

    --------------------------------

    👉 j = 10 → 'A'
    map[A] = 1 → 0
    remaining = 0 🎉

    Window valid again.

    Current window: [1..10] = "DOBECODEBA"

    --------------------------------

    🔁 Shrink Again
    i = 1 → 'D'

    Not in map → just move

    --------------------------------

    i = 2 → 'O'

    Ignore

    --------------------------------

    i = 3 → 'B'
    map[B] = -1 → 0
    remaining still 0

    Still valid.

    --------------------------------

    i = 4 → 'E'

    Ignore

    --------------------------------

    i = 5 → 'C'
    map[C] = 0 → 1
    remaining = 1 ❌
    i = 6

    Before breaking, check window [5..10] = "CODEBA"
    Length = 6 (not better than 6)

    --------------------------------

    👉 Continue Expanding
    👉 j = 11 → 'N'

    Ignore

    --------------------------------

    👉 j = 12 → 'C'
    map[C] = 1 → 0
    remaining = 0 🎉

    Window valid again.

    Current window: [6..12] = "ODEBANC"

    --------------------------------

    🔁 Final Shrinking Phase
    i = 6 → 'O'

    Ignore

    --------------------------------

    i = 7 → 'D'

    Ignore

    --------------------------------

    i = 8 → 'E'

    Ignore

    --------------------------------

    i = 9 → 'B'
    map[B] = 0 → 1
    remaining = 1 ❌

    Before invalidating, check window:

    [9..12] = "BANC"
    Length = 4  ✅

    Update:

    minLen = 4
    start = 9

    Stop shrinking.

    --------------------------------

    ✅ Final Answer
    s.substring(9, 13) = "BANC"
*/

// 🧠 Most Important Mental Model
// Expand → make window valid
// Shrink → make window minimal
// Repeat

// This is the hardest $04DSA.sliding window pattern.