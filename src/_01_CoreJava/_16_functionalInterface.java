package _01_CoreJava;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


// prototype -
@FunctionalInterface
interface MyInterface {
    void method1();
    default void method2() {

    }

    static void method3() {

    }
}

/// functional interface - an interface that contains exactly one abstract method making it suitable for use with lambda expressions.
/// purpose of FI - we can give an implementation of this interface using lambda expression.
///
/// lambda expressions - lambda expression are a concise way to represent an implementation of functional interface using a short, readable syntax. It is design to reduce boilerplate code.

interface Greeting {
    void greet();
}
interface Square {
    int operate(int number);
}
class MyInterfaceMain {
    void main() {
        /// let's assume koi interface hai aur usme sirf ek method hai. Is there any way to implement interface (since interface have one method) without making class ??
        /// (since interface have one method) without making class (since interface have only 1 method so it is overhead to make classes to implement the interface)
        /// to solve this issue by making anonymous class and further it's simplified by lambda expressions.

        /// 1. implement FI using anonymous inner class
        Greeting g1 = new Greeting() {
            @Override
            public void greet() {
                System.out.println("greeting g1");
            }
        };
        g1.greet();

        /// 2. using lambda expression
        Greeting g2 = () -> System.out.println("greeting g2");
        g2.greet();


        Square s1 = number -> number * number;
        s1.operate(10);
    }
}

/// ---------------------------- PreDefined functional interface -------------------------

class Predefined_FI {
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




/// ---------------------------------- 🚩 Method References -------------------------------------
/// shorter way to implement a functional interface. syntax is even more optimized than lambda using method reference.
/// first take an example of lambda expression, then make this expression shorter and convert it to method reference.
interface Calculator {
    int sum(int a, int b);
}
class MathOperations {
    public static int add(int a, int b) {
        return a + b;
    }
}
class MethodRefMain {
    void main() {
        // 1. anonymous inner class
        Calculator c1 = new Calculator() {
            @Override
            public int sum(int a, int b) {
                return MathOperations.add(a, b);
            }
        };
        System.out.println(c1.sum(10, 20));

        // 2. lambda expression
        Calculator c2 = (a, b) -> {
            return MathOperations.add(a, b);
        };
        System.out.println(c2.sum(10, 20));

        // 3. method reference (compact and best)
        // there are 2 condition whether a lamba is eligible for method reference -
        // a. implementation should have single statement.
        // b. there is some method call happening in the statement.
        Calculator c3 = MathOperations::add;
        System.out.println(c3.sum(10, 20));
    }
}

/// ------------------------------- Types of Method reference ------------------------------
/// A. Static Method Reference - It is used when we are calling static method of a class directly, without creating an object.
/// syntax - Classname::staticMethod
interface Machine {
    int calculate(int a, int b);
}
class SomeWork {
    // ... other methods

    public static int multiply(int a, int b) {
        return a * b;
    }
}
class Main1 {
    void main() {
        Machine m1 = (a, b) -> SomeWork.multiply(a, b);
        System.out.println(m1.calculate(10, 3));

        // using method ref.
        Machine m2 = SomeWork::multiply;
        System.out.println(m2.calculate(10, 3));
    }
}

/// B. instance method Reference(of a particular object) - It is used when we are calling instance method of some class using its object.
/// syntax- ObjectName::instanceMethodName
interface Validator {
    boolean validate(int num);
}
class SomeWork1 {
    // ... other methods

    public boolean isPositive(int num) {
        return num > 0;
    }
}
class Main2 {
    void main() {
        SomeWork1 sm =  new SomeWork1();
        Validator v1 = (num) -> sm.isPositive(num);
        System.out.println(v1.validate(10));

        // using method ref.
        SomeWork1 sm1 =  new SomeWork1();
        Validator v2 = sm1::isPositive;
        System.out.println(v2.validate(-2));
    }
}

/// C. Instance method Reference(of an arbitrary object) - It is used when we are calling an instance method on an object that is passed as an argument.
/// syntax - ClassName::instanceMethodName
interface StringOperations {
    int operate(String str);
}
class Main3 {
    void main() {
        StringOperations s1 = str -> {
            return str.length();
        };
        System.out.println(s1.operate("Prashant"));

        // using method ref.
        StringOperations s2 = String::length;
        System.out.println(s2.operate("Prashant"));

        StringOperations s3 = str -> {
           return str.hashCode();
        };
        System.out.println(s3.operate("Prashant"));

        // using method ref.
        StringOperations s4 = String::hashCode;
        System.out.println(s4.operate("Prashant"));
    }
}

/// D. constructor Method Reference - It is used when we are calling a constructor to create a new object.
/// syntax - ClassName::new
interface Creator<T> {
    T create(String name);
}
class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}
class Product {
    String productName;

    public Product(String productName) {
        this.productName = productName;
    }
}
class Main4 {
    void main() {
        Creator<User> c1 = (name) -> {
            return new User(name);
        };
        User u1 = c1.create("maitra");
        System.out.println(u1.name);

        // using method ref.
        Creator<User> c2 = User::new;
        User u2 = c2.create("maitra");
        System.out.println(u2.name);


        Creator<Product> c3 = (name) -> {
            return new Product(name);
        };
        Product p1 = c3.create("apple watch 10");
        System.out.println(p1.productName);

        Creator<Product> c4 = Product::new;
        Product p4 = c4.create("apple watch 9");
        System.out.println(p4.productName);
    }
}