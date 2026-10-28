package _04_DSA;

import java.util.*;

public class _0_basic {
    static void main() {
        // convert set to arrays
        Set<String> stringSet = new HashSet<>();
        String[] strArray = stringSet.toArray(String[]::new);

        Set<Integer> intSet = new HashSet<>();
        Integer[] intArray = intSet.toArray(Integer[]::new);

        Set<Double> doubleSet = new HashSet<>();
        Double[] doubleArray = doubleSet.toArray(Double[]::new);

        Set<Character> charSet = new HashSet<>();
        Character[] charArray = charSet.toArray(Character[]::new);

        /// java me arrays ko directly print karoge to hashcode dega [@7ad041f3. So, Arrays.toString() use krke human readable string me convert krte hai.
        Arrays.toString(strArray);

        /// empty character - char ch = ' ';


    }
}
