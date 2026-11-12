package _04_DSA;

import java.util.*;

public class _2_Arrays {
    private boolean isChar(char c) {
        if(c >= 'a' && c <= 'z') return true;
        if(c >= 'A' && c <= 'Z') return true;
//        if(c >= '0' && c <= '9') return true;
        return false;
    }
    public boolean validPalindrome(String str) {
        int l=0, r=str.length()-1;
        while(l<r) {
            if(str.charAt(l) != str.charAt(r)) {
                return false;
            }
            // space
            while(l < r && !isChar(str.charAt(l))) l++;
            while(l < r && !isChar(str.charAt(r))) r--;
            l++;
            r--;
        }
        return true;
    }

    public int[] twoSumInSortedArrayI(int[] nums, int target) {
        // ... use hashmap (find the indexes)
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i+1);
//        }
//        for(int i = 0; i < nums.length; i++) {
//            if(map.containsKey(target-nums[i])) {
//                return new int[]{i+1, map.get(target-nums[i])};
//            }
//        }
//        return new int[]{-1, -1};

        // ... use two pointers
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(sum == target) {
                return new int[]{l + 1,r + 1};
            } else if(sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSumInSortedArrayII(int[] nums, int target) {
        // ... use binary search (since array is sorted)
        return null;
    }

    // [1,2,0,1,0,0,0,0]
    // Output -
    //[[0,0,0],[0,0,0]]
    // Expected -
    //[[0,0,0]]
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (nums[l] == nums[l - 1] && l < r) l++;
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str, int l, int r) {
        while(l < r) {
            if(str.charAt(l) != str.charAt(r)) {
                return false;
            } else {
                l++; r--;
            }
        }
        return true;
    }
    public boolean validPalindrome2(String str) {
        int l = 0, r = str.length() - 1;
        while(l < r) {
            if(str.charAt(l) != str.charAt(r)) {
                return isPalindrome(str, l+1, r) || isPalindrome(str, l, r-1);
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/merge-sorted-array/description/
    public int[] mergeI(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] ans = new int[m + n];

        while(i < m && j < n) {
            if(nums1[i] <= nums2[j]) {
                ans[k] = nums1[i];
                i++;
            } else {
                ans[k] = nums2[j];
                j++;
            }
            k++;
        }

        while(i < m) {
            ans[k] = nums1[i];
            i++;
            k++;
        }

        while(j < n) {
            ans[k] = nums2[j];
            j++;
            k++;
        }

        for (int p = 0; p < m + n; p++) {
            nums1[p] = ans[p];
        }
        return nums1;
    }

    public int[] mergeII(int[] nums1, int m, int[] nums2, int n) {
        //... without extra space
        return null;
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int i=0, j=0;
        while(i< word1.length() && j<word2.length()) {
            res.append(word1.charAt(i));
            res.append(word2.charAt(j));
            i++; j++;
        }
        res.append(word1.substring(i));
        res.append(word2.substring(j));
        return res.toString();      // convert StringBuilder to String using toString.
    }

    // https://leetcode.com/problems/container-with-most-water/description/
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int maxArea = 0;

        while(i < j) {
            int currentWater = Math.min(height[i], height[j]) * (j - i);
            maxArea = Math.max(maxArea, currentWater);
            if(height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
    public int removeDuplicates(int[] nums) {
        int k = 1;
        // keep first element as it then start from index 1 to n
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    // https://leetcode.com/problems/rotate-array/
    public int[] rotate(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        k = k % n;

        for (int i = n - k; i < n; i++) {
            list.add(nums[i]);
        }

        for (int i = 0; i < n - k; i++) {
            list.add(nums[i]);
        }

        // convert arrayList to array
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

    // To figure out the required time and space complexity from constraints, you can use the 10⁸ Operations Rule. Online judges (like LeetCode)
    // typically allow roughly 10⁸ simple operations per second before throwing a Time Limit Exceeded (TLE) error.
    // ---------------------------------- [ https://leetcode.com/problems/subarray-sum-equals-k/ ] ------------------------------------
    public int subarraySum(int[] nums, int k) {
        // you can solve brute force way O(n2)
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    //  🚩Important ::
    // can't use sliding window { mathematical reason:: Negative Numbers. Sliding Window tabhi kaam karegi jab array ki values monotonic behavior show karein (yani window bada karne par sum hamesha badhe, aur chota karne par sum hamesha ghate }
    // two pointer only applicable, if we need two points nothing in between (not subarray type)
    // use prefix sum + hashmap
    // map structure - { prefix sum: count }
    public int subarraySumI(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    void main() {
        System.out.println("validPalindrome: " + validPalindrome("  Was it a car or a cat I saw?? "));

        System.out.println("twoSumInSortedArray: " + Arrays.toString(twoSumInSortedArrayI(new int[]{1,2,3,4}, 3)));
        System.out.println("twoSumInSortedArray: " + twoSumInSortedArrayII(new int[]{1,2,3,4}, 3));

        System.out.println("three sum:" + threeSum(new int[] {-1,0,1,2,-1,-4}));

        System.out.println("validPalindrome: " + validPalindrome2("abbda"));

        System.out.println("ContainerWithMostWater: " + maxArea(new int[] {1,8,6,2,5,4,8,3,7}));

        System.out.println("merge sorted array: " + Arrays.toString(mergeI(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3)));

        System.out.println("merge string alternate: " + mergeAlternately("ab", "abbxxc"));

        System.out.println("remove duplicates: " + removeDuplicates(new int[] {0,0,0,1,1,2,2,3,3,4}));

        System.out.println("rotate Array: " + Arrays.toString(rotate(new int[] {1,2,3,4,5,6,7}, 3)));

        System.out.println("subarray sum equal to k: " + subarraySum(new int[]{1,-1,0,2,-1,3}, 3));
    }
}
