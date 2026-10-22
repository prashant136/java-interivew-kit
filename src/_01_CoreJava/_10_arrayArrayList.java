package _01_CoreJava;

import java.util.ArrayList;
import java.util.Arrays;

public class _10_arrayArrayList {
    void main() {
        int[] numbers = new int[3];     // fixed size = 3, default value = 0
        int[] number1 = {2, 7, 9};        // value initialized
        System.out.println(Arrays.toString(number1));

        ArrayList<Integer> numList = new ArrayList(Arrays.asList(1, 4, 8));
        System.out.println(numList);

        // ArrayList<int> numList2 = new ArrayList();  // ❌



    }
}

/// in arrays, we have to give size of array at beginning. aur java array create krke default value rakh deta hai.
/// default value - [ int - 0, string - null, double - 0.0 ]
/// ArrayList ke case me size dene ki need nhi hoti, hum dynamically value add krte hai.
/// Arrays can be initialized using {} and ArraysList can be initialized using Arrays.asList();
/// Arrays work with both Objects and primitives but Arraylist can't support primitive datatype. only works with objects.

/// fetch - arrays -> arr[i]; arraylist -> list.get(i);
/// no new element can be added in arrays at end,  arraylist me kr sakte hai list.add()
/// removed - element can't be removed in arrays. arraylist -> list.remove(i);
/// update - arrays -> arr[i] = updateEle; arraylist -> list.set(i, updateElem);

/// in array if you try to print some value - we get some address, but you can print arraylist directly.
/// toString() method return a string represetation of list in square brackets, with elements saparated by comma.
/// array doesn't have toString() but Arraylist have.
/// arr doesn't implement list interface but arraylist implements list interface.

