package _01_CoreJava.streams;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Predefined_FI {
    void main() {
        /// Predicate (pre-defined FI) in java 8 that takes one argument and return boolean value.
        /// [used for condition testing]
        Predicate<Integer> p1 = (num) -> num % 2 == 0;
        System.out.println(p1.test(10));
        System.out.println(p1.test(7));

        Predicate<String> p2 = (str) -> str.isEmpty();
        System.out.println(p2.test(""));
        System.out.println(p2.test("prashant"));

        /// Function (pre-defined FI) in java 8 that takes one input and returns some output.
        /// [used to transforming data]
        Function<String, Integer> f1 = (str) -> str.length();
        System.out.println(f1.apply("prashanth"));

        Function<Integer, Integer> f2 = (num) -> num* num;
        System.out.println(f2.apply(10));

        /// Consumer (pre-defined FI) in java 8 that takes one input return nothing.
        /// [used to perform any operation where you don't need to return anything]
        Consumer<String> c1 = (name) -> System.out.println("my name is: " + name);
        c1.accept("maitra");

        /// Supplier (pre-defined FI) in java 8 that doesn't take anything but return a value.
        /// [used to generate or supply data]
        Supplier<Integer> s1 = () -> (int) Math.random();
        System.out.println(s1.get());

        Supplier<String> s2 = () -> {
            String a = "senior";
            String b = "dasi";
            return a.concat(b);
        };
        System.out.println(s2.get());
    }
}

/// Its shorter way to implement a functional interface. We can make the syntax even more optimized
/// than lambda using method reference.

