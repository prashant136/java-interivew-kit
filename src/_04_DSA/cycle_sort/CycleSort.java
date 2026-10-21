package _04_DSA.cycle_sort;
import java.util.Arrays;

public class CycleSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] cycleSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1;
            if (arr[i] != arr[correct]) {
                swap(arr, i, correct);
            } else {
                i++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 4};
        System.out.println(Arrays.toString(cycleSort(arr)));
    }
}
/* cycle sort -  It finds the correct position for this element in the cycle by repeatedly swapping
                it with the element currently at that position until the correct position is reached.
*/
// 🚩 👉 whe given number from range 1 to N => apply cycle sort
