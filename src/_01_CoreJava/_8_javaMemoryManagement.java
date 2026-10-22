package _01_CoreJava;

class Person {
    String name;    // instance variable
    int age;

    static String company = "TheCuriousCoder";

    public void workHours() {
        int hours = 8;      // local variable
        System.out.println(hours);

        if(false) {

        } else {

        }

        for (int i = 0; i < 10; i++) {
            // ....
        }
    }
}
/*
 +-------------------------------------------------------------------------+
 |                              JVM MEMORY                                 |
 |                                                                         |
 |   +-------------+    +-------------+    +-------------+                 |
 |   | METHOD      |    | HEAP        |    | STACK       |                 |
 |   | AREA        |    | MEMORY      |    | MEMORY      |                 |
 |   +-------------+    +-------------+    +-------------+                 |
 |                                                                         |
 |                      +-------------+    +-------------+                 |
 |                      | PC          |    | NATIVE      |                 |
 |                      | REGISTER    |    | METHOD      |                 |
 |                      |             |    | STACK       |                 |
 |                      +-------------+    +-------------+                 |
 +-------------------------------------------------------------------------+
*/
/// the instance variable stored in heap memory. The local variable stored in the stack memory.
/// static variable saved in method area.
/// there is no object, there is nothing in heap area. we have nothing in the stack. becoz we haven't called any method()
/// but in method area we have certain things -
/// 1. the blueprint of the class always stored in method area.
/// 2. in method area - the class name, the method name, the instance variable name, (whn object is created then value stored in heap) but signature is stored in method area.
/// 3. but for STATIC variable both name and value is stored in method area.
///
/// jab hum new keyword se object banate hai toh object heap memory me create hota hai. with their default value. string - null, int - 0
///  stack - function { reference variable + address of object } heap - { object stored in heap }
/// there is dedicated frame in stack memory for every method. There is a stack frame for main method.
///
/// "Every thread in java has its own memory block called PC Register". And its only job is to store the address of the instruction the thread is currently executing.
/// So, whatever operation we want to perform next that is stored in the PC register. So let's say there is a thread running and the next step (it has to print something, start a loop, call a method)
/// whatever next instruction it has to do that is stored in the PC register.
///
/// This is used for native methods - (java jvm ka internal method jo phle se implemented hai)
///  1. Native methods are written in other languages, integrated in JDK.
///  2. Comes into play whenever these native methods are called.
///  3. Stores the local variables of the native method.
///

public class _8_javaMemoryManagement {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "Shivam";
        p1.age = 18;        // ab heap memory me name="Shivam" and age = 18 store ho gya hai.

        Person p2 = new Person();
        p2.name = "rahul";
        p2.age = 22;        // ab heap me dono object stored honge. aur stack me do reference variable - person, p1

        p2.workHours();     // now in stack memory there is dedicated stack frame for workHours() now.
    }
}
/*
 +---------------------------------------+
 |              HEAP  MEMORY            |
 |                                      |
 |   Address: 101        Address: 109   |
 |   +-------------+    +-------------+ |
 |   | name=Shivam|     | name=Rahul |  |
 |   | age = 18  |      | age = 22  |   |
 |   +-----------+     +------------+   |
 +--------------------------------------+

 +-----------------------------------+
 |           STACK MEMORY            |
 |                                   |
 |   +---------------+-------+       |
 |   | p1            |  101  |       | main method Frame
 |   +---------------+-------+       |
 |   | p2            |  109  |       |
 |   +---------------+-------+       |
 |   | hours         |   8   |       | workHours() Frame
 |   +---------------+-------+       |
 +-----------------------------------+

 +---------------------------------------------+
 |                 METHOD AREA                 |
 |                                             |
 |   +-----------------+-------------------+   |
 |   | Employee        |                   |   |
 |   +-----------------+-------------------+   |
 |   | String name     |                   |   |
 |   +-----------------+-------------------+   |
 |   | int age         |                   |   |
 |   +-----------------+-------------------+   |
 |   | workHours()     |                   |   |
 |   +-----------------+-------------------+   |
 |   | String company  | TheCuriousCoder   |   |
 |   +-----------------+-------------------+   |
 |   | Main            |                   |   |
 |   +-----------------+-------------------+   |
 |   | main()          |                   |   |
 |   +-----------------+-------------------+   |
 |   +-----------------+-------------------+   |
 |   | if() else { }   |                   |   |
 |   +-----------------+-------------------+   |
 |   +-----------------+-------------------+   |
 |   | for loop        |                   |   |
 |   +-----------------+-------------------+   |
 +---------------------------------------------+
*/