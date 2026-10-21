package _01_CoreJava.streams.lambda;

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

/// lambda expressions - lambda expression are a concise way to represent an implementation of functional interface using a short, readable syntax. It is design to reduce boilerplate code.




public class Main {
    void main() {
        /// assume karo koi interface (Vehicle interface) hai aur usme ek method hai. we need to implement interface by implementing different class (since interface have only 1 method so it is overhead to make different classes to implement the interface)
        /// is there any way to implement interface (since interface have one method) without making classes.
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