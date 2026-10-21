/*

    🔷 Core Idea (Golden Rule)
        👉 Constraints tell you how fast your solution MUST be

        Think like this:
            Constraints → Max input size → Allowed complexity

    -------------------------------------------------------------

    🔷 Time Complexity Mapping (MOST IMPORTANT)
    
        Memorize this table 👇
        | Input Size (n)           | Allowed Complexity       |
        | ------------------------ | ------------------------ |
        | n ≤ 10                   | O(n!) brute force        |
        | n ≤ 20                   | O(2^n) recursion         |
        | n ≤ 100                  | O(n³)                    |
        | n ≤ 1,000                | O(n²)                    |
        | n ≤ 10⁵                  | O(n log n) or O(n)       |
        | n ≤ 10⁶                  | O(n) only                |

        Quick Memory Trick -
            Big n → faster algorithm needed
            Small n → brute force allowed

    -------------------------------------------------------------
    
    🔷 Apply to Your Problem (Example)
        
        K Closest Points - problem

        Constraint: n ≤ 10^5

        Allowed:
        O(n log n)  ✅
        O(n log k)  ✅
        O(n²)       ❌

        So:
            Sorting → O(n log n) → OK
            Heap → O(n log k) → Better

    ------------------------------------------------------------

    🔹 Median from Data Stream

        Operations: 10^5 calls
        If you sort every time: O(n log n) per query ❌
        Total: 10^5 * n log n → TOO BIG ❌
        So you need: O(log n) per operation ✅
        → Use Heap

    -----------------------------------------------------------

    🔷 Step-by-Step Thinking Process (Use This Always)

        When you see a problem:

        Step 1 — Check Constraints
        Example: n ≤ 10^5

        Step 2 — Decide Allowed Complexity
        n log n or n

        Step 3 — Match with Approach
        | Approach      | Complexity |
        | ------------- | ---------- |
        | Brute force   | n²         |
        | Sorting       | n log n    |
        | Heap          | n log k    |
        | Binary Search | log n      |
        | HashMap       | n          |
        
        Step 4 — Pick Best Fit

    ----------------------------------------------------------

    🔷 Why You Feel Confused (Important Insight)

        You said: “I solve but don’t know if it fits”

        Because you're doing: Solve → Then check complexity

        Instead do:
        Check constraints → THEN choose approach

    ----------------------------------------------------------

    🔷 Example (Your Style Thinking Fix)
        Problem: Find K closest elements
        n = 10^5

        Wrong Thinking ❌
        Let me try brute force first
        
        Correct Thinking ✅
        n = 10^5 → cannot use O(n²)

        Options:
        - Sorting → O(n log n)
        - Heap → O(n log k)
        - Binary Search → O(log n + k)

        Pick best → Binary Search

    ----------------------------------------------------------

    🔷 Space Complexity (Simple Rule)
        Ask: Am I storing extra data?

        | Code          | Space |
        | ------------- | ----- |
        | new array     | O(n)  |
        | HashMap       | O(n)  |
        | Heap          | O(k)  |
        | In-place sort | O(1)  |

    ---------------------------------------------------------

    🔷 Interview Trick (Very Important)

        When interviewer asks:

        👉 “What’s your complexity?”

        Answer like this:

        Time Complexity:
        We iterate over n elements and each $04DSA.heap operation takes log k,
        so total complexity is O(n log k)

        Space Complexity:
        We use a $04DSA.heap of size k, so O(k)

    ----------------------------------------------------------
    
    🔷 Pattern-Based Complexity Recognition

    This will help you a LOT:

    | Pattern        | Complexity |
    | -------------- | ---------- |
    | Sorting        | O(n log n) |
    | Heap           | O(n log k) |
    | Sliding Window | O(n)       |
    | Binary Search  | O(log n)   |
    | Two Pointers   | O(n)       |
    | Hashing        | O(n)       |

    --------------------------------------------------------

    🔷 Real Interview Insight (Important)

    Interviewers don’t expect perfect answer immediately.
    They expect:
        1. Identify constraints
        2. Reject bad approaches
        3. Move toward optimal

    🔷 Practice Trick (Use Daily)

        Whenever solving the problem, Write this before coding:
            n = ?
            Allowed complexity = ?
            Approach = ?
    
    🔷 One-Line Rule (Remember Forever)
        Constraints decide algorithm
        NOT the other way around
*/