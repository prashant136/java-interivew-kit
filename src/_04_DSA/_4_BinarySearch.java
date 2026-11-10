package _04_DSA;

import java.util.Arrays;

public class _4_BinarySearch {

    public String reverseString(String str) {
        char[] s = str.toCharArray();
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
        return new String(s);
    }

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

    // 🚩 https://leetcode.com/problems/single-element-in-a-sorted-array/
    // explanation - we have a pair of every single element and 1 single element. our array size always odd (2n+1)
    // from mid-element, there will be two side (odd number of elements, even number of element). our single value is present in odd section.
    // calculate leftSize of array. if leftSize is even -> h = m + 1 (element present in right) else leftSize is odd -> l = m -1; (element present in left)
    // example -
    //  arr = [3];
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if ((m == 0 || nums[m] != nums[m - 1]) && (m == nums.length - 1 || nums[m] != nums[m + 1])) {
                return nums[m];
            }
            // Important:: Calculate the size of the left portion (excluding the pair containing m)     // please dry run
            int leftSize = m > 0 && nums[m] == nums[m - 1] ? m - 1 : m;
            if (leftSize % 2 == 1) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    // https://leetcode.com/problems/sqrtx
    // liner approach -
    public int mySqrtI(int x) {
        int ans = 1;

        for (int i = 1; i <= x; i++) {
            if((long) i*i > x) {
                break;
            }
            ans = i;
        }
        return ans;
    }
    // binary search
    public int mySqrtII(int x) {
        int l = 0, h = x;
        int res = 0;
        while(l <= h) {
            int m = l + (h - l) / 2;
            if((long) m * m > x) {
                h = m - 1;
            } else if((long) m * m < x) {
                l = m + 1;
                res = m;
            } else {
                return m;
            }
        }
        return res;
    }

    // https://leetcode.com/problems/find-peak-element
    public int findPeakElement(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (m < nums.length - 1 && nums[m] < nums[m + 1]) {     // boundary condition + strictly increasing
                l = m + 1;
            } else if (m > 0 && nums[m] < nums[m - 1]) {      // boundary condition + strictly decreasing
                h = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    // https://leetcode.com/problems/search-a-2d-matrix
    // time - O(n + log m)
    private boolean binarySearch(int[][] matrix, int target, int row, int col) {
        int l = 0, h = col;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (matrix[row][m] == target) {
                return true;
            } else if (matrix[row][m] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return false;
    }
    public boolean searchMatrixI(int[][] matrix, int target) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        for (int i = 0; i <= rows; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][cols]) {
                return binarySearch(matrix, target, i, cols);
            }
        }
        return false;
    }

    // Optimize log(n * m)
    public boolean searchMatrixII(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int l = 0, h = rows * cols - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int row = m / cols;
            int col = m % cols;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return false;
    }

    // brute force - O(n)
    public double findMedianSortedArraysI(int[] nums1, int[] nums2) {
        // merge both array inn sorted way then find median.
        int len1 = nums1.length, len2 = nums2.length;
        int i = 0, j = 0, k = 0;
        int[] temp = new int[len1 + len2];

        while(i < len1 && j < len2) {
            if(nums1[i] <= nums2[j]) {
                temp[k] = nums1[i];
                i++;
            } else {
                temp[k] = nums2[j];
                j++;
            }
            k++;
        }
        while(i < len1) {
            temp[k] = nums1[i];
            i++; k++;
        }
        while (j < len2) {
            temp[k] = nums2[j];
            j++; k++;
        }
        // nums1 = temp.clone(); // copy element

        int n = temp.length;
        if(n % 2 == 1) {
            return (double) temp[n/2];
        }

        // median even formula
        return (double) (temp[(n / 2) - 1] + temp[n / 2]) / 2.0;
    }

    // https://leetcode.com/problems/peak-index-in-a-mountain-array/
    // same as find peak element in mountain array
    public int peakIndexInMountainArray(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (m < nums.length - 1 && nums[m] < nums[m + 1]) {     // boundary condition + strictly increasing
                l = m + 1;
            } else if (m > 0 && nums[m] < nums[m - 1]) {      // boundary condition + strictly decreasing
                h = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    // 🟠🟠🟠 HARD - https://leetcode.com/problems/find-in-mountain-array
//    public int findInMountainArray(int target, MountainArray mountainArr) {
//        return 0;
//    }

    // 🟠🟠🟠 HARD - https://leetcode.com/problems/median-of-two-sorted-arrays
    // optimized - O(log (m+n)) - using binary search
    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        return 0.0;
    }

    void main () {
        System.out.println("Reverse string: " + reverseString("hello world"));
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
        System.out.println("single element in a sorted array: " + singleNonDuplicate(new int[] {3,3,7,7,10,11,11}));
        System.out.println("square root: " + mySqrtI(16));
        System.out.println("square root: " + mySqrtII(1562444));
        System.out.println("peak element is: " + findPeakElement(new int[] {1,2,1,3,5,6,4}));
        System.out.println("search in 2d matrix: " + searchMatrixI(new int[][] {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 13));
        System.out.println("search in 2d matrix: " + searchMatrixII(new int[][] {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 34));
        System.out.println("median of two sorted array: " + findMedianSortedArraysI(new int[] {1,3,4,7,10}, new int[] {2,6,15}));
        System.out.println("peek index: " + peakIndexInMountainArray(new int[] {0,10,5,2}));
//        System.out.println("median of two sorted array: " + findMedianSortedArraysII(new int[] {1,2}, new int[] {3,4}));

    }
}
