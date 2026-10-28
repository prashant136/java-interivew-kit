package _04_DSA;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1_ArrayStringQuestion {

    public boolean validAnagram1(String s, String t) {
        if(s.length() != t.length()) return false;

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

    public boolean validAnagram2(String s, String t) {
        if(s.length() != t.length()) return false;

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

    public int removeElement(int[] nums, int val) {
        // in place
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static char m1(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char ans = ' ';     // empty character

        for(int i=0; i<str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0)+1);
        }

        for(int i=0; i<str.length(); i++) {
            if(map.get(str.charAt(i)) == 1) {
                ans = str.charAt(i);    // return type char
                break;
            }
        }

        return ans;
    }

    void main() {
        System.out.println(validAnagram1("racecar", "carrace"));
        System.out.println(validAnagram2("racecar", "carrace"));

        System.out.println(isSubsequence("node", "neetcode"));
        System.out.println(isSubsequence("axc", "ahbgdc"));

        System.out.println(majorityElement1(new int[]{5,5,1,1,1,5,5}));
        System.out.println(majorityElement2(new int[]{5,5,1,1,1,5,5}));

        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));       // answer array - [ 0,1,3,0,4 ]

        String str1 = "aabcc";
        String str2 = "aabbcc";
        char ans = m1(str1);
        if(ans == ' ') {
            System.out.print("No distinct characters in string");
        } else {
            System.out.print(ans);
        }


    }
}
