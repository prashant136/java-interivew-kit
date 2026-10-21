package _01_CoreJava.streams.methodReference;

interface Calculator {
    int calculate(int a, int b);
}

class MathOperations {
    public static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    void main() {
        // 1.
//        Calculator c1 = (a, b) -> {
//            return MathOperations.add(a, b);
//        };
        // 2. or using method reference
        Calculator c2 = MathOperations::add;
        System.out.println(c2.calculate(10,20));
    }

    /// can I convert this lambda into method reference ?
    /// yes
    /// 🚩because method reference has two conditions :-
    /// 1. implementation should have single statement.
    /// 2. method call happening in the statement

    /// Types -
    /// 1. Static Method Reference - when we are calling static method of a class directly, without creating an object.
    /// classname::staticMethod
    /// Calculator c2 = MathOperations::add;

    /// 2. instance method ref. (particular object) - when we are calling instance method of some class using its object.
    /// objectName::instanceMethodName
    // 3. instance method ref. (arbitrary object)
    // 4. constructor method ref.


}
