package _04_DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public int threeSum(int[] nums) {
        return 0;
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

    void main() {
        System.out.println("validPalindrome: " + validPalindrome("  Was it a car or a cat I saw?? "));
        System.out.println("twoSumInSortedArray: " + Arrays.toString(twoSumInSortedArrayI(new int[]{1,2,3,4}, 3)));
        System.out.println("twoSumInSortedArray: " + twoSumInSortedArrayII(new int[]{1,2,3,4}, 3));
        System.out.println("three sum:" + threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println("validPalindrome: " + validPalindrome2("abbda"));
    }
}
