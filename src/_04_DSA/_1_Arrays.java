package _04_DSA;


import java.util.*;

public class _1_Arrays {

    public int[] concatinationOfArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[2 * n];

        for (int i = 0; i < n; i++) {
            res[i] = nums[i];
            res[i + n] = nums[i];
        }
        return res;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num: nums) {
            if(set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public boolean validAnagramI(String s, String t) {
        // brute force - easiest
        if (s.length() != t.length()) {
            return false;
        }
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean validAnagramII(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public String longestCommonPrefixI(String[] strs) {
        // sort the string
        Arrays.sort(strs);
        int minLen = strs[0].length();

        // find prefix b/w first and last (idea is first is smallest and last would be largest string)
        int i=0;
        while(i<minLen && strs[0].charAt(i) == strs[strs.length-1].charAt(i)) {
            i++;
        }
        return strs[0].substring(0, i);
    }

    public String longestCommonPrefixII(String[] strs) {
        // ... use divide and conquer (gfg)
        return null;
    }

    public String longestCommonPrefixIII(String[] strs) {
        // ... use trie
        return null;
    }

    public List<List<String>> groupAnagramI(String[] strs) {
        // ... brute force
        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];

        for(int i = 0; i < strs.length; i++) {
            // if string is already visited
            if(visited[i]) continue;

            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            visited[i] = true;

            for(int j = i+1; j < strs.length; j++) {
                // check both string i, j are anagram also check if j is visited
                if(!visited[j] && validAnagramI(strs[i], strs[j])) {
                    group.add(strs[j]);
                    visited[j] = true;
                }
            }
            res.add(group);
        }
        return res;
    }

    // optimized approach
    public List<List<String>> groupAnagramII(String[] strs) {
        // ... use hashtable
        return null;
    }

    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public int majorityElementI(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (count.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    public int majorityElementII(int[] nums) {
        // Boyer Moore Voting Algorithm
        int candidate = 0, count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public int majorityElementIII(int[] nums) {
        // ... use bit manipulation
        return -1;
    }

    public int majorityElement1(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (count.get(num) > nums.length / 2) {
                res = num;
            }
        }
        return res;
    }

    // moors voting algorithm -
    public int majorityElement2(int[] nums) {
        return 0;
    }

    // ------------------------- quick sort ----------------------------
    public void quickSort(int[] nums, int low, int high) {
        return;
    }

    // ------------------------- merge sort ----------------------------
    private void merge(int[] nums, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        List<Integer> temp = new ArrayList<>();

        while(i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp.add(nums[i]);
                i++;
            } else {
                temp.add(nums[j]);
                j++;
            }
        }

        // add remaining elements
        while(i <= mid) {
            temp.add(nums[i]);
            i++;
        }
        while(j <= right) {
            temp.add(nums[j]);
            j++;
        }

        // transfer value to existing array.
        for (int k = 0; k < temp.size(); k++) {
            nums[k + left] = temp.get(k);
        }
    }
    private void mergeSortHelper(int[] nums, int left, int right) {
        if(left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(nums, left, mid);
            mergeSortHelper(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }
    public int[] mergeSort(int[] nums) {
        mergeSortHelper(nums, 0, nums.length-1);
        return nums;    // after sorting
    }

//    Initialize three pointers: l (boundary for 0s), i (current element), and r (boundary for 2s).
//    While i <= r:
//        If nums[i] is 0, swap with nums[l], increment both l and i.
//        If nums[i] is 2, swap with nums[r], decrement r (do not increment i since the swapped element needs to be checked).
//        If nums[i] is 1, just increment i.
    public int[] sortColors(int[] nums) {
        int i = 0, l = 0, r = nums.length - 1;
        while (i <= r) {
            if (nums[i] == 0) {
                swap(nums, l, i);
                l++;
            } else if (nums[i] == 2) {
                swap(nums, i, r);
                r--;
                i--;
            }
            i++;
        }
        return nums;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        suff[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            pref[i] = nums[i - 1] * pref[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = nums[i + 1] * suff[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = pref[i] * suff[i];
        }
        return res;
    }

    // O(n*log n)
    public int longestConsecutiveI(int[] nums) {
        Arrays.sort(nums);

        int res = 0, count = 1;

        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) continue;

            if(nums[i] == nums[i-1] + 1) {
                count++;
            } else {
                count = 1;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // O(n), O(n)
    public int longestConsecutiveII(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            // check element is starting point of the streak
            if (set.contains(nums[i]) && !set.contains(nums[i] - 1)) {
                int streak = nums[i];
                int count = 0;
                while (set.contains(streak)) {
                    count++;
                    streak++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public int secondLargestElement(int[] arr) {
        int largest = -1, secondLargest = -1;
        // find largest
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }
        // find second largest
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    // https://leetcode.com/problems/first-unique-character-in-a-string/description/
    public char firstUniqueCharacter(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' '; // empty character
    }

    // https://leetcode.com/problems/first-missing-positive/
    public int firstMissingPositiveI(int[] nums) {
        // [3,4,-1,1] => 2
        // [1,2,0] => 3
        // [7,8,9,11,12] => 1
        // [-2,-1,0] => 1

        // 1. sort the array then iterate and find the missing number - time - O(n*log n), space - O(1)
        Arrays.sort(nums);
        int k = 1;
        for(int num: nums) {
            if(num > 0 && num == k) {
                k++;
            }
        }
        return k;

        // 2. using boolean array start from 1 -> the biggest number, fill with true then find missing
    }

    public int firstMissingPositiveII(int[] nums) {
        // ...use Cycle sort - in the sorted version index is equal to element.
        int n = nums.length;
        int i = 0;

        while (i < n) {
            if (nums[i] <= 0 || nums[i] > n) {
                i++;
                continue;
            }
            int index = nums[i] - 1;
            if (nums[i] != nums[index]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            } else {
                i++;
            }
        }
        // find missing element
        for (i = 0; i < n; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n+1;     // if every element is sorted, [1,2,3,4] - 5
    }

    // 🚩Cycle Sort ::
    // 1. when given number is range from 1....N, use Cycle sort
    // 2. after sorting we can see, index = value - 1 (because index value is start from zero)
    // 3. start from beginning, we can check that value should be in right index. if it not then swap with correct indexed value.
    // example - [3, 5, 2, 1, 4]
    //              swap 3 with 2 (index 2)
    //           [2, 5, 3, 1, 4]
    //              swap 2 with 5 (index 1)
    //           [5, 2, 3, 1, 4]
    //              swap 5 with 4 (index 4)
    //           [4, 2, 3, 1, 5]
    //              swap 4 with 1 (index 3)
    //           [1, 2, 3, 4, 5]    ---> array is sorted bro...
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int[] cycleSort(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            int correct = nums[i] - 1;
            if(nums[i] != nums[correct]) {
                swap(nums, i, correct);
            } else {
                i++;
            }
        }
        return nums;
    }

    // 🚩Counting Sort ::
    // 1. first find the largest element.
    // 2. create an array of size largest + 1 (store the frequency of elements)
    // 3. fill this array with frequencies where index number is element number.
    // 4. traverse the array, if value of index > 0, it means that index present in my original array.
    // 5. since index of array is sorted, exact the value one by one and this is the counting sort.
    // 6. instead of freq. array use hashmap
    public int[] countingSort(int[] nums) {
        return null;
    }

    // 🚩Redix Sort ::

    // 🚩Bucket Sort ::


    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    void main() {

        System.out.println("concatinationOfArray: " + concatinationOfArray(new int[]{1, 4, 1, 2}));
        System.out.println("containsDuplicate: " + containsDuplicate(new int[]{1,2,3,3}));
        System.out.println("validAnagram: " + validAnagramI("racecar", "carrace"));
        System.out.println("validAnagram: " + validAnagramII("racecar", "carrace"));
        System.out.println("twoSum: " + twoSum(new int[]{3,6,5,2,6}, 7));
        System.out.println("longestCommonPrefix: " + longestCommonPrefixI(new String[] {"geeksforgeeks", "geeks", "geek", "geezer"}));  // gee
//        System.out.println("longestCommonPrefix: " + longestCommonPrefixII(new String[] {"geeksforgeeks", "geeks", "geek", "geezer"}));     // gee
        System.out.println("groupAnagram: " + groupAnagramI(new String[]{"act","pots","tops","cat","stop","hat"}));
        System.out.println("removeElement: " + removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));       // answer array - [0,1,3,0,4]
        System.out.println("majorityElement: " + majorityElementI(new int[]{5,5,1,1,1,5,5}));
        System.out.println("majorityElement: " + majorityElementII(new int[]{5,5,1,1,1,5,5}));
//        System.out.println(majorityElementIII(new int[]{5,5,1,1,1,5,5}));

        System.out.println("Sorting: " + Arrays.toString(mergeSort(new int[] {10,9,1,1,1,2,3,1})));
        System.out.println("sort color:" + sortColors(new int[] {1,0,1,0,2}));
        System.out.println("product Except Self: " + productExceptSelf(new int[] {-1,0,1,2,3}));
        System.out.println("longestConsecutiveI : "+ longestConsecutiveI(new int[] {100,4,200,1,3,2}));
        System.out.println("longestConsecutiveII : "+ longestConsecutiveII(new int[] {100,4,200,1,3,2}));
        System.out.println("second Largest: " + secondLargestElement(new int[] {12, 35, 1, 10, 34, 1}));
        System.out.println("First Unique Character in a String: " + firstUniqueCharacter("neetcodeneet"));
        if(firstUniqueCharacter("neetcodeneet") == ' ') {
            System.out.print("No distinct characters in string");
        } else {
            System.out.print(firstUniqueCharacter("neetcodeneet"));
        }

        System.out.println("Cycle Sort: " + Arrays.toString(cycleSort(new int[] {3, 5, 2, 1, 4})));
        System.out.println("Find missing number: " + firstMissingPositiveI(new int[] {-2,-1,0}));
//        int[] arr = {7,8,9,11,12};  // after sorted [7,8,9,11,12] means 1 is missing
//        int[] arr = {-2,-1,0};      // after sorted [-2,-1,0] means 1 is missing
//        int[] arr = {3,4,-1,1};     // after sorted [1,-1,3,4] means 2 is missing
//        int[] arr = {1,2,0};        // after sorted [1,2,0] means 3 is missing
        System.out.println("Find missing number(cycle sort): " + firstMissingPositiveII(new int[] {3,4,-1,1}));

//
//        System.out.println(isSubsequence("node", "neetcode"));
//        System.out.println(isSubsequence("axc", "ahbgdc"));
//
//


    }
}
