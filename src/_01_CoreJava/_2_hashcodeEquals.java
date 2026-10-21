package _01_CoreJava;

/// 👉 .equals() method is used to compare content / values of two objects.
/// equals() and hashCode() both methods belongs to Object class. Object class is parent of all the classes.
/// Therefore, every java class can call equals() and hashCode().
/// as we know == use to compare References of Object.
//class Employee {
//    int id;
//    String name;
//
//    @Override
//    public boolean equals(Object obj) {
//        Employee e = (Employee) obj;
//        if (this.id == e.id && this.name.equals(e.name)) {      // if id and name both are same for object -> true
//            return true;
//        }
//        return false;
//    }
//}
//
//class Main {
//    void main(String[] args) {
//        Employee e1 = new Employee();
//        e1.id = 1;
//        e1.name = "Rahul";
//
//        Employee e2 = new Employee();
//        e2.id = 1;
//        e2.name = "Ram";
//        // System.out.println(e1.equals(e2));   // false
//
//        /// Why e1.equals(e2) = false, since equals() objects ke content ko compare krta hai.
//        ///  but Object class ke andar equals() ka default implementation -
//        //   public boolean equals(Object obj) {
//        //      return (this == obj);       dono objects ke reference compare ho rha hai isiliye 'false' return ho rha hai.
//        //   }
//        /// we need to override equals() for our custom implementation.
//
////        String str1 = "Rahul";
////        String str2 = "Rahul";
////        System.out.println(str1.equals(str2));   /// true - because String has override the equals()
//
//        // ✅ our own implementation
//        System.out.println(e1.equals(e2));  // true
//    }
//}

// ----------------------------------- hashCode -----------------------------------

import java.util.HashSet;
import java.util.Objects;

/// hashCode is also present in Object class, we need to override hashCode() for our custom logic.
/// It is used to create hash value for an Object. Hash means a unique integer value that can be assigned to your object for identification.
/// As per contract, whenever you override equals(), you must override hashCode as well.
///  why do we need hashCode() - there are some scenarios where hashCode and equals both are used -
///     1. All the hashing based data structures like hashset, hashmap
class Employee {
    int id;
    String name;

    @Override
    public boolean equals(Object obj) {
        Employee e = (Employee) obj;
        if (this.id == e.id && this.name.equals(e.name)) {      // if id and name both are same for object -> true
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);    // if id and name both are same then hashcode generated will be same for both object.
    }
}

class Main {
    void main(String[] args) {
        Employee e1 = new Employee();
        e1.id = 1;
        e1.name = "Rahul";

        Employee e2 = new Employee();
        e2.id = 1;
        e2.name = "Rahul";

        HashSet<Employee> set = new HashSet<>();
        set.add(e1);    /// whenever add() is called, internally it uses equals() and hashCode()
        set.add(e2);

        /// size is depend on the elements (both are same or not)
        /// although we override equals() then why set is treating both object different. we also need to override hasCode() [contract in java]
//        System.out.println(set.size());   // before overriding - 2

        // after override both equals and hashCode
        System.out.println(set.size());   // after overriding - 1

    }
}
