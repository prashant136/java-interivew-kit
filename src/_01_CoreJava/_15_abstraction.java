package _01_CoreJava;

/// ?? what is Abstraction ?
/// Abstraction focuses on hiding internal implementation details and showing only the essential features to the user.


/// ?? how to achieve abstraction in java ? abstract class aur interface dono ki help se hum abstraction achieve krte hai.

/// ?? what is abstract class ? (supports partial abstraction by allowing both abstract methods and methods with implementations)
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
/// ?? what is interface ? why interface came into picture ?
/// interface ke do kaam hai:: 1. abstraction achieve krna aur multiple inheritance achieve krna.
///  class - blueprint for object
///  interface - blueprint for class

/// interface -
/// 1. methods are public abstract - (private and protected aren't allowed)
/// 2. variables are public static final (constant)
/// 3. jab hum interface ko kisi class se implement karte hai. there are 2 possibilities -
///     a.) interface ke method ko implement karo.
///     b.) OR, class ko abstract bna do. (tab method implement krna optional hai)
/// 4. interface doesn't have constructor.
/// 5. interface have default aur static method. (both are public) (java 8)
/// 6. static method() - helper methods / factory methods (static method sirt interface se access kiye ja sakte hai)
/// 7. default method in interface - ye concrete method hai. [ intuition - suppose karo agar default method nhi sirf abstract method hota, toh classes me iski implemenation krni padti.
///  assume karo 1 interface ko hazar class implement kar rhi toh kya hazaro classes me ye method implement karoge (abstract ke case me) ? Not practical
///  that's reason default method aaya (ye ek concrete method hai), jo iske classes me automatically availabe hongi. class ka object bnake ise access ] ((default method))
/// 8. we can define multiple default method and default method can be overridden.

interface AnimalInterface {

    int MAX_AGE = 150;
    void eat();
    void sleep();

    static void info() {
        System.out.println("I am an animal");
    }

    default void run() {
        this.sleep();
        System.out.println("Animals are running");
    }

    default void breed() {
        System.out.println("Animals are breeding");
    }
}

class Elephant implements AnimalInterface {

    @Override
    public void eat() {
        System.out.println("Elephant is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Elephant is sleeping");
    }
}

class Dog implements AnimalInterface {

    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping");
    }

    @Override
    public void breed() {
        System.out.println("Dog is breeding");
    }
}

class InterfaceMain {
    public static void main(String[] args) {
        Elephant elephant = new Elephant();
        elephant.eat();

        Dog dog = new Dog();
        dog.eat();

        System.out.println(Elephant.MAX_AGE);
        System.out.println(AnimalInterface.MAX_AGE);
        AnimalInterface.info();

        elephant.run();
        dog.run();

        elephant.breed();   // inherit the AnimalInterface's breed.
        dog.breed();    // override the default breed method
    }
}


/// ------------------------ how interface solve multiple interface ------------------------
/// If a class implements multiple interfaces with same default method, it must override it.
interface MusicPlayer {
    void playMusic();
    void stopMusic();
}
interface Phone {
    void makeCall(String number);
    void endCall();
}
interface Camera {
    void takePhoto();
    void recordVideo();
}

class SmartPhone implements MusicPlayer, Phone, Camera {
    @Override
    public void endCall() {
        System.out.println("Ending call on SmartPhone");
    }

    @Override
    public void makeCall(String number) {
        System.out.println("Calling " + number + " from SmartPhone");
    }

    @Override
    public void stopMusic() {
        System.out.println("Stopping music on SmartPhone");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music on SmartPhone");
    }

    @Override
    public void recordVideo() {
        System.out.println("Recording video on SmartPhone");
    }

    @Override
    public void takePhoto() {
        System.out.println("Taking photo on SmartPhone");
    }
}

class InterfaceDemoMain {
    public static void main(String[] args) {
        SmartPhone smartPhone = new SmartPhone();
        smartPhone.playMusic();
        smartPhone.stopMusic();
        smartPhone.takePhoto();
        smartPhone.recordVideo();
        smartPhone.makeCall("988347581");
        smartPhone.endCall();
    }
}

/// ----------------------------------------------------------------------------------
class Calcular1 {
    public int operate(int num1, int num2) {
        return num1 * num2;
    }
}
interface Calcular {
    default int operate(int num1, int num2) {
        return num1 + num2;
    }

    /// Object class ka equals method ko interface me override nhi kr sakte hai.
//    default boolean equals(Object obj) {      // compile error
//        return false;
//    }
}
class CalcularImpl extends Calcular1 implements Calcular {

}
class CalculatorMain {
    void main() {
        CalcularImpl c = new  CalcularImpl();
        System.out.println(c.operate(10, 20));      // class Calcular1's operate method
    }
}

/// CalcularImpl class Calcular1 and Calcular ko inherit kr rha ek interface hai doorsa class and dnon me same method (ek normal method hai doosra default method hai)
/// class method ko preference milega.

/// Object class me kisi me method ko hum interface me overide nhi kr sakte hai. A default method can't override a method form Object class.




/// -------------- can we write main() method in interface ---------------------------
interface ABC {
    public static void main(String[] args) {
        System.out.println("hello world!!");
    } // jvm ko main() ki object bnane ki need nhi hoti, aur interface me hum static method bana sakte hai.
}

/// ?? abstract class and interface me diff ? abstract class me instance variable hote hai aur unko initialize krne ke liye constructor bhi hota hai.
///     but interface me ye cheeze nhi hoti hai. Ek class sirf ek abstract method ko extend krti hai. Wahi ek class multiple interface ko implement kr sakti hai.








/// which one you choose interface and abstract class ?

