package _01_CoreJava;

/// ------------------- diamond problem -------------------



/// -------------------------------- polymorphism -----------------------------
/// polymorphism means 'many forms'. In java, it means a method will behave differntly on the basis of how it's been called.
/// two type - compile time and runtime
///
/// compile time polymorphism - the specific method is chosen by the compiler during the compilation phase rather than at runtime. (early binding)
/// Method overloading is a way to achieve compile-time polymorphism.
/// rule for method overloading - class must be same, method name must be same, parameters must be different (Or datatype of parameters must be different, order of params must be different),
/// [ overloading me return type ka role nhi hota hai, same, different, koi fark nhi ]

class Printer {
    public void print() {
        System.out.println("hello world");
    }

//    public String print() {   ❌ return type me overloading achive nhi kr sakte hai
//        return "hello world";
//    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void print(String name, int age) {
        System.out.println(name + age);
    }

    void main(String[] args) {
        Printer printer = new Printer();
        printer.print();
        printer.print("hello world");
        printer.print("Prashant", 31);
    }
}

class PolymorphismHomework1 {
    public void print(String str) {
        System.out.println("String method called");
    }

    public void print(Object obj) {
        System.out.println("Object method called");
    }

    void main(String[] args) {
        PolymorphismHomework1 obj = new PolymorphismHomework1();
        obj.print(null);
        // null ko kisi bhi reference type (String ya Object) ke liye valid hai.
        // String bhi ek Object hai. Java overload ke case choose krta hai jo uske kareeb/specific ho.
        // Kyunki String, Object ki hierarchy mein neeche aati hai (child hai), isliye String hoga.
    }
}

class PolymorphismHomework2 {
    public void print(String str) {
        System.out.println("String method called");
    }

    public void print(Integer number) {
        System.out.println("number method called");
    }

    public void print(Object obj) {
        System.out.println("Object method called");
    }

    void main(String[] args) {
        PolymorphismHomework2 obj = new PolymorphismHomework2();
        // obj.print(null);  // ❌ Ambiguity Error - null String aur Integer me confused hai.
        // null ki Multiple Compatibility: null ko String, Integer aur Object me compatibility hai.
    }
}


/// ✅ run time polymorphism - runtime polymorphism only exits in a child parent relationship.
///
/// rule for method override - in parent child relationship :: method name, return type and parameters must be same.
class Vahan {
    public void description() {
        System.out.println("Vahan method called");
    }
}

class Car extends Vahan {

}

class Bike extends Vahan {
    public void description() {
        System.out.println("Bike method called");
    }
}

class PolymorphismMain {
    void main() {
        Vahan vahan = new Vahan();
        vahan.description();

        Car car = new Car();
        car.description();      // since description method Vahan class se inherit ho rha hai.

        Bike bike = new Bike();
        bike.description();     // bike ka description vahan ke description ko override kia.

        Vahan vahan2 = new Bike();   // reference is Vahan but object is Bike
        vahan2.description();       // so, bike's description() method is called
    }
}

/// while overriding a method in child, you can increase access modifier but can't decrease.
///
/// increasing order ::
/// private  ->  default(same package)  ->  protected (same package, subclass)  ->  public
class A {
    public void m1() {}
    protected void m2() {}
    void m3() {}
}
class B extends A {
    // private void m1() {}    // ❌
    public void m2() {}    // ✅
    protected void m3() {}  // ✅
}


/// Override static method - static method can't be overridden. (This is not method overriding, method hiding)
///
/// Method hiding, on the other hand, occurs when a subclass defines a static method with the same signature as a static method in its superclass. Unlike method overriding, where the subclass method overrides the superclass method's behavior, in method hiding, the subclass method shadows the superclass method.
///
/// 👉 reason :: static methods belong to class. Java resolves them at compile time based on the declared reference type, not the runtime object type.
class C {
    public static void description() {
        System.out.println("C called");
    }
}
class D extends C {
    public static void description() {
        System.out.println("D called");
    }
}
class E {
    void main() {
        C c1 = new C();
        c1.description();

        D d = new D();
        d.description();

        C c2 = new D();
        c2.description();
    }
}