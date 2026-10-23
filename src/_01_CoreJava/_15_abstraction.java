package _01_CoreJava;

/// what is Abstraction ?
/// Abstraction focuses on hiding internal implementation details and showing only the essential features to the user.


/// how to achieve abstraction in java ? abstract class aur interface dono ki help se hum abstraction achieve krte hai.

/// what is abstract class ? (supports partial abstraction by allowing both abstract methods and methods with implementations)
/// An abstract class in Java is a class declared with the abstract keyword that cannot be instantiated directly.
/// An abstract class can contain:
///     Abstract methods, which do not have a method body.
///     Concrete methods, which contain an implementation.
///     Constructors, instance variables, static methods and final methods.
///
///
/// koi method jiska declaration dena hai but definition nhi dena, use abstract declare kr do.
/// jaise method ko abstract declare kiya toh class ko bhi abstract declare krna padega.
/// ab abstract class dekte hai -
/// 1. abstract class me regular method ho sakte hai.
/// 2. we can declare abstract class without abstract method. there is no other way around.
/// 3. abstract class ko jab extend karte hai 2 possibilities -
///     a. child class me abstract method override kro.
///     b. child class ko abstract declare (abstract method override karo na karo optional hai)
/// 4. abstract class ka object nhi bna sakte. uske child ka object bna sakte hai.
/// 5. abstract class can have a constructor. (subclass ka constructor pehle super() keyword ki help se apne parent (abstract) class ke constructor ko call karta hai.)
///     protected access modifier recommend hota hai.
///
/// private abstract method: (Compile-time Error) Not allowed, there is no point of abstract method if it's declare as private (child won't override)
/// public abstract method: Allowed. Subclass isko kahin se bhi implement kar sakti hai.
/// protected abstract method: Allowed. same package ya dusre package ki subclasses hi ise implement kar sakti hain.
/// default(package private) abstract methods: Allowed. sirf same package ki subclasses hi ise override kar payengi.
abstract class Animal {
    String name;

    final String predator = "Cheetah";

    protected Animal(String name) {
        this.name = name;
    }

    static void see() {

    }

    abstract public String getName();

    // private abstract void eat(); // ❌ compile error

    protected abstract void eat();

    public void sleep() {}
}

// abstract class Cat extends Animal {}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    protected void eat() { }
}

class AbstractMain {
    void main() {
        Animal cat = new Cat("animal");
    }
}


/// --------------------------------------- interface ------------------------------------
/// what is interface ? why interface came into picture ?

/// which one you choose interface and abstract class ?