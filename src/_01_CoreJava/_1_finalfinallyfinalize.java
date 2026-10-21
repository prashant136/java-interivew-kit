package _01_CoreJava;


// 1. ----------------------------- finally ------------------------------------
class FinallyKeyword {
    public static int testMethod() {
        try {
            System.out.println("In try block");
            return 10;
        } catch (Exception e) {
            System.out.println("In catch block");
            return 20;
        } finally {
            System.out.println("In finally block");
            return 30;
        }
    }

    public static void main(String[] args) {
        System.out.println("Result: " + testMethod());
    }

    // output - 30
    // If both try and finally contain return statements, the return from finally overrides the return from try or catch.
}

/// finally is block which is used along with try catch
/// we can write finally without catch
///  finally always execute except on condition
// 1. jvm termination
//    try {
//            System.exit(0);
//    } finally {
//            System.out.println("Finally");
//    }

//2. inifinte loop
//    try {
//        while (true) {
//            ...inifinte loop
//        }
//    } finally {
//        System.out.println("Finally");
//    }





// 2. -------------------------------------- final -----------------------------------------
// -------------------- final on field ---------------------
//class Employee {
//    Long id;
//    String name;
//    // final String company = "Google";    // final field must be initialized
//    final String company;
//
//    public Employee(String company) {
//        this.company = company;     // you can set fields and final fields in constructor.
//    }
//}
//
//public class FinalKeyword {
//    void main() {
//        Employee emp = new Employee("Facebook");
//        emp.id = 1L;
//        emp.name = "kimi";
//        // emp.company = "tcs"  // ❌ can't do becoz it's final (compile time error)
//    }
//}


// -------------------- final on method ---------------------
//// we can't override greet() method
//class Employee {
//    Long id;
//    String name;
//    String company;
//
//    public Employee() {}
//
//    public Employee(String company, String name, Long id) {
//        this.company = company;
//        this.name = name;
//        this.id = id;
//    }
//
//    public void printInfo() {
//        System.out.println("id: "+ id);
//        System.out.println("name: "+ name);
//        System.out.println("company: "+ company);
//    }
//
//    public final void greet() {
//        System.out.println("Hello I am an Employee");
//    }
//}
//
//class Intern extends Employee {
//    public void greet() {       // ❌
//        System.out.println("Hello I am an Intern");
//    }
//}
//
//class Manager extends Employee {
//    public void greet() {       // ❌
//        System.out.println("Hello I am an Manager");
//    }
//}
//
//public class FinalKeyword {
//    void main() {
//        Employee e1 = new Employee();
//        e1.greet();
//
//        Intern intern = new Intern();
//        intern.greet();
//
//        Manager manager = new Manager();
//        manager.greet();
//
//    }
//}
///     Default Constructor Rule: Java me jab kisi class ka parameterize constructor (Employee ka parameterized constructor) banate hain, toh Java compiler apna default no-argument constructor (public Employee() {}) khud nahi banata.
///     Jab 'class Intern extends Employee' likhte h aur Intern ka koi constructor nhi banate, toh Java Intern class ke default constructor khud generate karta hai.
///     es generated constructor ke andar compiler sabse pehle super() (means superclass ka default constructor) ko call karta h.
///     Employee class ka koi default no-argument constructor nhi h. That's why compiler error throw kr rha hai.


// -------------------- final on class ---------------------
//// we can't extend the final class
//final class Employee {
//    Long id;
//    String name;
//    String company;
//
//    public Employee() {}
//
//    public void greet() {
//        System.out.println("Hello I am an Employee");
//    }
//}
//
//class Intern extends Employee {
//    public void greet() {
//        System.out.println("Hello I am an Intern");
//    }
//}



// ------------------------ finalize -------------------------

///  finalize is the method which is called at the time of Garbage Collection by JVM.
/// Garbage collection is process where JVM free up the memory by deleting all the unreferenced objects.
class FinalizeKeyword {
    void main() {
        String s = new String("Hello");
        s = null;  // "Hello" is still in the heap memory. but no one is pointing. (ready for GC)

        System.gc();  // JVM run its garbage collector to free unused memory.
    }

    // whenever garbage collection take palace this method is called. finalize method is part of Object class.
    // after java 8 (deprecated)
    @Override
    protected void finalize() throws Throwable {

    }
}
