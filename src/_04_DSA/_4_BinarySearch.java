package _04_DSA;

import java.util.Arrays;

public class _4_BinarySearch {

    public int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // -------------------- lower bound -------------------
    // smallest index such that arr[index] >= target
    public int lowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;      // by default answer
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                ans = mid;      // maybe an answer
                high = mid - 1;     // look for smaller index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // -------------------- upper bound -------------------
    // smallest index such that arr[index] > target
    public int upperBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                ans = mid;      // maybe an answer
                high = mid - 1;     // look for smaller index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/search-insert-position/description/
    public int searchInsert(int[] nums, int target) {
        // ...use lower bound
        int l = 0;
        int h = nums.length - 1;
        int ans = nums.length;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                ans = m;
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // -------------- Floor ----------------
    // Floor val is <= target

    // -------------- Ceil ------------------
    // Ceil val is >= target (Ceil is LOWER BOUND)


    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
    public int[] searchRange(int[] nums, int target) {
        int lb = lowerBound(nums, target);

        // target doesn't exist (lower bound end position ho yr target ke equal na ho)
        if (lb == nums.length || nums[lb] != target) {
            return new int[]{-1, -1};
        }

        int ub = upperBound(nums, target);
        return new int[]{lb, ub - 1};
    }


    // https://leetcode.com/problems/search-in-rotated-sorted-array/description/
    public int findPivot(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            // If mid is greater than the high, the pivot is in the right half
            if(nums[m] > nums[h]) {
                l = m + 1;
            } else {
                // Otherwise, pivot is in the left half (including mid)
                h = m;
            }
        }
        return l;
    }
    private int bs(int[] nums, int l, int h, int target) {
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == target) {
                return m;
            }  else if(target < nums[m]) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        // find the pivot point (smallest element), after finding pivot, array becomes left sorted half + right sorted half
        // find pivot -
        int pivot = findPivot(nums);
        int ans = bs(nums, 0, pivot - 1, target);
        if(ans == -1) {
            ans = bs(nums, pivot, nums.length - 1, target);
        }
        return ans;
    }

    // 1. first identify the sorted half then you can perform search
    // 2. arr[low] < arr[mid] or arr[high] > arr[mid] ... condition for sorted half compare with mid
    public int searchRotatedI(int[] nums, int target) {
        int l  = 0, h = nums.length - 1;
        while(l <= h) {
            int m = l + (h - l) / 2;
            if(nums[m] == target) return m;

            // now find the sorted half
            else if(nums[l] <= nums[m]) {    // 1. left half is sorted
                // 2. check target is present in left or right half
                if(target >= nums[l] && target <= nums[m]) {
                    h = m - 1;
                } else {
                    l = m + 1;
                }
            } else {    // 1. right half is sorted
                // 2. check target is present in left or right part
                if(target >= nums[m] && target <= nums[h]) {
                    l = m + 1;
                } else {
                    h = m - 1;
                }
            }
        }
        return -1;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    public boolean searchRotation2(int[] nums, int target) {
        int l  = 0, h = nums.length - 1;
        while(l <= h) {
            int m = l + (h - l) / 2;
            if(nums[m] == target) return true;

            // idea:: remove duplicate if mid is equal to start, end then check trim down the list accordingly.
            if(nums[m] == nums[l]) {
                l++; continue;
            }

            // now find the sorted half
            else if(nums[l] <= nums[m]) {    // 1. left half is sorted
                // 2. check target is present in left or right half
                if(target >= nums[l] && target <= nums[m]) {
                    h = m - 1;
                } else {
                    l = m + 1;
                }
            } else {    // 1. right half is sorted
                // 2. check target is present in left or right part
                if(target >= nums[m] && target <= nums[h]) {
                    l = m + 1;
                } else {
                    h = m - 1;
                }
            }
        }
        return false;
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
    public int findMin(int[] nums) {
        // ... east way is just find pivot
        int l = 0, h = nums.length - 1;
        while(l < h) {
            int m = l + (h - l) / 2;
            if(nums[m] > nums[h]) {
                l = m + 1;
            }  else {
                h = m;
            }
        }
        return nums[l];
    }

    // https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
    public boolean checkArrayIsSorted(int[] nums) {
        // do this later
        return false;
    }

    // https://leetcode.com/problems/find-peak-element/description/
    public int findPeakElement(int[] nums) {
        return 0;
    }

    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    public int singleNonDuplicate(int[] nums) {
        return 0;
    }

    //



    void main () {
        System.out.println("Binary Search: " + binarySearch(new int[] {1,2,3,4,5,6,7}, 3));
        System.out.println("lower bound: " + lowerBound(new int[] {2,2,3,6,7,8,8,11,11,12}, 10));
        System.out.println("upper bound: " + upperBound(new int[] {2,2,3,6,7,8,8,11,11,12}, 6));
        System.out.println("insert position: " + searchInsert(new int[] {1,3,5,6}, 5));
        System.out.println("first and last occurrence of array: " + Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println("search in rotated array: " + search(new int[] {4,5,6,7,0,1,2}, 3));
        System.out.println("search in rotated array: " + search(new int[] {4,5,6,7,0,1,2}, 1));
        System.out.println("search in rotated array: " + searchRotation2(new int[] {4,5,6,6,7,0,1,2,4,4}, 6));
        System.out.println("find minimum in rotated sorted array: " + findMin(new int[] {4,5,6,7,0,1,2}));
        System.out.println("check array is sorted and rotated: " + checkArrayIsSorted(new int[] {2,1,3,4}));
        System.out.println("peak element is: " + findPeakElement(new int[] {1,2,1,3,5,6,4}));
        System.out.println("single element in a sorted array: " + singleNonDuplicate(new int[] {3,3,7,7,10,11,11}));
    }
}
