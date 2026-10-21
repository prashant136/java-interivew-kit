package _01_CoreJava;

/// is java completely object-oriented ? No, because java contains primitive data types like byte, short, int etc. they are not Objects.
/// but java provide a way to convert this primitive type to Objects, that where Wrapper classes comes into picture.
// Primitives -> Wrapper Classes
// byte      -> Byte
// short     -> Short
// int       -> Integer
// long      -> Long
// float     -> Float
// double    -> Double
// char      -> Character
// boolean   -> Boolean

/// if primitive work fine then why do we need wrapper classes ?
/// Some feature in java like Collection, Generics and few method don't accept the primitives.

import java.util.ArrayList;
import java.util.List;

/// Boxing - converting primitive data type (int, char, double) into its corresponding wrapper class object (Integer, Character, Double).
/// Autoboxing - complier convert primitive to its wrapper automatically.
/// Unboxing - Unboxing is the exact reverse of Boxing. (wrapper -> primitive)
/// Auto Unboxing - automatic unboxing
class _3_wrapperClasses {
    void main() {
        int a = 10;
        Integer obj = Integer.valueOf(a);   /// Boxing

        Integer obj1 = a;   /// Autoboxing - shortcut

        int x = obj.intValue();     // Unboxing

        int y = obj1;       // auto unboxing

        // ------------------- 🚩utility methods --------------------
        String number = "10";
        Integer obj2 = Integer.valueOf(number);  // convert String value to Integer
        int number2 = Integer.parseInt(number);     // 👉 parseInt convert String to primitive type integer

        // convert Integer(wrapper class) into String
        String number3 = obj2.toString();
        // String number4 = number2.toString(); // ❌ not possible (primitive int doesn't have toString())

        Integer i1 = 10;
        Integer i2 = 20;
        System.out.println(i1.compareTo(i2));  // if i1 < i2 -> return -1, else return 1 and return 0 for equal
        System.out.println(Integer.compare(i1, i2));    // same as compareTo - because behind the scene compareTo() compare() ko call krta hai.
        System.out.println(Integer.max(i1, i2));
        System.out.println(Integer.min(i1, i2));
        System.out.println(Integer.sum(i1, i2));    // sirf 2 value
        System.out.println(Integer.MAX_VALUE);      // 2147483647
        System.out.println(Integer.MIN_VALUE);      // -2147483648

        // wrapper class in Collections -
        List<Integer> list1 = new ArrayList<>();

        // ❌ primitive types are not allowed in Generics.
        // List<int> list2 = new ArrayList<>();

        // ✅ since int is primitive but int[] is object in java
        // List<int[]> list3 = new ArrayList<>();   // allowed

    }
}