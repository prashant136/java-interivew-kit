package _04_DSA;

import java.util.*;

public class _2_ArraysTwoPointers {
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

    // Kadane's algo -
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


    void main() {
        System.out.println("validPalindrome: " + validPalindrome("  Was it a car or a cat I saw?? "));
        System.out.println("twoSumInSortedArray: " + Arrays.toString(twoSumInSortedArrayI(new int[]{1,2,3,4}, 3)));
        System.out.println("twoSumInSortedArray: " + twoSumInSortedArrayII(new int[]{1,2,3,4}, 3));
        System.out.println("three sum:" + threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println("validPalindrome: " + validPalindrome2("abbda"));
        System.out.println("ContainerWithMostWater: " + maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println("maximum subArray (kadane algo): " + maxSubArray(new int[] {2,-3,4,-2,2,1,-1,4}));  // 8

    }
}
