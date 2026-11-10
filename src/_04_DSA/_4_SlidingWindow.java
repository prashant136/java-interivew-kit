package _04_DSA;

import java.util.*;


// 1. ------------- Fixed Size Window (Subarray / Substring of length k) ------------------
// The problem explicitly asks for subarray/substring of size k with some property (sum, max, distinct chars, etc.).
// You don't need all subarrays, just continuous chunks of length k.
// Q:) find the maximum sum for k size subarray

// 2. ------------ Dynamic / Variable Window (Shrink or Expand) -----------------
// The question says "longest / smallest substring (or subarray) satisfying a condition".
// Typically, involves expanding right pointer, and when condition is violated → shrink left pointer.
// Q:) minimal length of subarray whose sum is greater than or equal to target

// 3. ------------ Substring with Character Frequency Conditions (typically hashmap, hashset, freq count) --------------
// Conditions like "at most k distinct chars" or "all chars appear same number of times".
// Window expands until invalid, then shrink.
// Q:) permutation in string
// Q:) the longest substring almost k distinct character

// 4. ------------ Optimizing Over a Continuous Range --------------
// Problem wants max/min sum, count, or length over continuous subarray/substring.
//



public class _4_SlidingWindow {

    // maximum sum of a subarray of size k (no negative)
    // arr[] = [100, 200, 300, 400],  k = 2, output - 700
    // arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4, output - 39
    // brute force -
    public int maximumSumOfSizeK(int[] arr, int k) {
        int ans = 0;
        int n = arr.length;
        for(int i = 0; i < n - k; i++) {
            int sum = 0;
            for(int j = i; j < k + i; j++) {
                sum += arr[j];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    // sliding window -
    public int maximumSumOfSizeKI(int[] arr, int k) {
        int l = 0, r = 0, windowSum = 0, ans = 0;
        while (r < arr.length) {
            windowSum += arr[r];

            if (r - l + 1 > k) {
                windowSum -= arr[l];
                l++;
            }

            ans = Math.max(ans, windowSum);
            r++;
        }
        return ans;
    }

    // Kadane's algo -
    // ===== The maximum subarray sum with both positive and negative numbers is solved using Kadane's Algorithm ====
    // intuition - if the running sum becomes negative, keeping it will only reduce the sum of any future subarray.
    // whenever the current sum drops below zero, we reset it and start a new subarray from the next element.
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], currSum = 0;
        for(int num: nums) {
            if(currSum < 0) {
                currSum = 0;
            }
            currSum += num;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    // array of positive nums and an integer k, return the total number of subarrays whose sum equals to k.
    // sliding window
    public int subarraySum(int[] nums, int k) {
        int l = 0, r = 0, res = 0, sum = 0;

        while(r < nums.length) {
            sum = sum + nums[r];
            while (sum > k) {
                sum = sum - nums[l];
                l++;
            }

            if(sum == k) {
                res++;
            }
            r++;
        }
        return res;
    }

    // array of (positive, negative) nums and an integer k, return the total number of subarrays whose sum equals to k.


    // https://leetcode.com/problems/max-consecutive-ones/
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] != 1) {
                    break;
                }
                count++;
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    // Optimized - O(n)
    public int findMaxConsecutiveOnesI(int[] nums) {
        int i = 0, currentCount = 0, ans = 0;
        while (i < nums.length) {
            if(nums[i] == 1) {
                currentCount++;
            } else {
                ans = Math.max(ans, currentCount);
                currentCount = 0;
            }
            i++;
        }
        return Math.max(ans, currentCount);
    }

    // https://leetcode.com/problems/max-consecutive-ones-iii/
    // brute force O(n2)
    public int longestOnes(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int zeros = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    zeros++;
                }
                if (zeros > k) {
                    break;
                }
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    // sliding window
    public int longestOnesI(int[] nums, int k) {
        return 0;
    }



    // https://leetcode.com/problems/contains-duplicate-ii
    public boolean containsNearbyDuplicateI(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for (int i = 0; i < nums.length; i++)
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;

        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int l = 0, r = 0;
        HashSet<Integer> set = new HashSet<>();

        while (r < nums.length) {
            // condition violets
            if (Math.abs(l - r) > k) {
                set.remove(nums[l]);
                l++;
            }

            // condition match
            if (set.contains(nums[r]) && Math.abs(l - r) <= k) {
                return true;
            }
            set.add(nums[r]);
            r++;
        }
        return false;
    }


    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
    public int maxProfit(int[] prices) {
        int l = 0, r = 0, maxProfit = 0;

        while (r < prices.length) {
            while (prices[r] < prices[l]) {     // condition violets
                l++;
            }

            if(prices[r] > prices[l]) {
                maxProfit = Math.max(prices[r] - prices[l], maxProfit);
            }
            r++;
        }
        return maxProfit;
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int l = 0, r = 0, res = 0;

        while(r < s.length()) {
            while(set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
            res = Math.max(res, r - l + 1);
            set.add(s.charAt(r));
            r++;
        }
        return res;
    }

    public int lengthOfLongestSubstringII(String s) {
        // ... using hashmap
        return 0;
    }

    // https://leetcode.com/problems/longest-repeating-character-replacement/
    public int characterReplacement(String s, int k) {
        int l = 0, r = 0, res = 0, maxFreq = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        // difference between length of substring (r - l + 1) and maxFreq of map is equal to k
        //  maxFreq = Math.max(map.get(s.charAt(r)), maxFreq);
        //  ((r - l + 1) - maxFreq) == k

        while (r < s.length()) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            maxFreq = Math.max(map.get(s.charAt(r)), maxFreq);

            if ((r - l + 1) - maxFreq > k) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            }

            if ((r - l + 1) - maxFreq <= k) {
                res = Math.max(res, (r - l + 1));
            }
            r++;
        }
        return res;
    }

    // https://leetcode.com/problems/permutation-in-string/
    // s1 = "abcdxabcde", s2 = "abcdeabcdx"
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) return false;

        HashMap<Character, Integer> s1Map = new HashMap<>();
        for (char s : s1.toCharArray()) {
            s1Map.put(s, s1Map.getOrDefault(s, 0) + 1);
        }

        // we need to compare at every iteration when s1Map ki length s2MAp ke equal ho.
        for (int i = 0; i < m; i++) {
            HashMap<Character, Integer> s2Map = new HashMap<>();
            for (int j = i; j < m; j++) {
                s2Map.put(s2.charAt(j), s2Map.getOrDefault(s2.charAt(j), 0) + 1);
                if (s2Map.size() == s1Map.size()) {
                    boolean ans = s2Map.equals(s1Map);
                    if(ans) return true;
                }
            }
        }
        return false;
    }

    // 🟠🟠 Sliding window - Optimization (frequency array)
    public boolean checkInclusionI(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }

        int l = 0, r = 0;
        while (r < s2.length()) {
            freq2[s2.charAt(r) - 'a']++;

            if ((r - l + 1) > s1.length()) {
                freq2[s2.charAt(l) - 'a']--;
                l++;
            }

            if ((r - l + 1) == s1.length()) {
                // compare both freq arrays
                boolean ans = true;
                for (int i = 0; i < 26; i++) {
                    if (freq1[i] != freq2[i]) {
                        ans = false;
                        break;
                    }
                }
                if (ans) {
                    return true;
                }
            }
            r++;
        }
        return false;
    }

    // 🟠🟠  Sliding window - Optimization
    // https://www.youtube.com/watch?v=7ZKe7P5bJbA - [RisingBrain]
    public boolean checkInclusionII(String s1, String s2) {
        // ...use 2 hashmap
        return false;
    }


    void main(String[] args) {
        System.out.println("maximum sum of size k (positive): " + maximumSumOfSizeK(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));
        System.out.println("maximum sum of size k (positive): " + maximumSumOfSizeKI(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));
        System.out.println("maximum sum of size k (positive): " + maximumSumOfSizeKI(new int[]{2, 3}, 1));
        System.out.println("maximum sum of size k (positive): " + maximumSumOfSizeKI(new int[]{100, 200, 300, 400}, 2));

        System.out.println("maximum subArray (Kadane algo): " + maxSubArray(new int[] {2,-3,4,-2,2,1,-1,4}));  // 8

        System.out.println("find max consecutive ones: " + findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
        System.out.println("find max consecutive ones: " + findMaxConsecutiveOnesI(new int[] {1,1,0,1,1,1}));

        System.out.println("find max consecutive ones III: " + longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println("find max consecutive ones III: " + longestOnesI(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));

        System.out.println("contains duplicates: " + containsNearbyDuplicateI(new int[] {1,2,3,1}, 3));
        System.out.println("contains duplicates: " + containsNearbyDuplicate(new int[] {1,2,3,1}, 3));

        System.out.println("max profit: " + maxProfit(new int[] {2,1,2,1,0,1,2}));      // 2

        System.out.println("length of longest non repeating substring: " + lengthOfLongestSubstring("abcabcbb"));
        System.out.println("length of longest non repeating substring: " + lengthOfLongestSubstringII("abcabcbb"));

        System.out.println("longest repeating chareacter replacement: " + characterReplacement("AABABBABB", 1));
        System.out.println("longest repeating chareacter replacement: " + characterReplacement("AAAA", 2));

        System.out.println("permutation in string: " + checkInclusion("ab", "eidbaooo"));
        System.out.println("permutation in string: " + checkInclusionI("ab", "eidbaooo"));
        System.out.println("permutation in string: " + checkInclusionII("ab", "eidbaooo"));

        System.out.println("permutation in string: " + checkInclusionII("aab", "a"));
    }
}
