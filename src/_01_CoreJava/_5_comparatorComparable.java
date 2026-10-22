package _01_CoreJava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Majdoor implements Comparable<Majdoor> {
    private int id;
    private String name;
    private int age;

    public Majdoor(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    /// 👉 if you compare :: e1.compare(e2)
    /// Return +ve --> e1 > e2
    /// Return 0 --> e1 == e2
    /// Return -ve --> e1 < e2
    @Override
    public int compareTo(Majdoor e) {
        if(this.getAge() == e.getAge()) {
            return 0;
        } else if(this.getAge() > e.getAge()) {
            return 1;
        } else {
            return -1;
        }
        /// return 0; means all the objects are equal.
    }
}

public class _5_comparatorComparable {
    public void print(List<Majdoor> employees) {
        for (Majdoor employee : employees) {
            System.out.println("ID: " + employee.getId() + " Name: " + employee.getName() + " Age: " + employee.getAge());
        }
    }
    void main() {
        Majdoor e1 = new Majdoor(1,"Sachit",25);
        Majdoor e2 = new Majdoor(2,"Kunal",21);
        Majdoor e3 = new Majdoor(3,"Sumit",28);
        Majdoor e4 = new Majdoor(4,"Pushp",31);
        Majdoor e5 = new Majdoor(5,"Manas",18);

        List<Majdoor> employees = Arrays.asList(e1,e2,e3,e4,e5);

        Collections.sort(employees);
        print(employees);
    }
}

/// Comparable is interface. we implement Comparable with class and override  compareTo() method (give our custom implementation)
/// internally when Collections.sort() is called, compareTo() method compare the objects. write our own implementation in compareTo()
/// but in Comparator, we don't need to do any of thing just implementation using lambda expression in Comparator (functional interface) reduce boilerplate code

/// ✅ In Comparable we can sort with same logic(one logic for every time) but Comparator gives flexibility that we can sort different ways
/// 🚩 when we use Comparable and Comparator both together then Comparator will take preference. Comparable treated as second option.


/// ------------------------------- Comparator -----------------------------
/// Comparator also an interface.
/// Comparator -> compare() method
/// Comparable -> compareTo() method
class Student  {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}

class ComparatorExample {
    public void print(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName() + " Age: " + student.getAge());
        }
    }
    void main() {
        Student e1 = new Student("Sachit",25);
        Student e2 = new Student("Kunal",21);
        Student e3 = new Student("Sumit",28);
        Student e4 = new Student("Pushpa",31);

        List<Student> students = Arrays.asList(e1,e2,e3,e4);
        Collections.sort(students, (a, b) -> {
            if(a.getAge() == b.getAge()) {
                return 0;
            } else if (a.getAge() > b.getAge()) {
                return 1;
            } else {
                return -1;
            }
        });
        print(students);

        Collections.sort(students, (a, b) -> {
            // if age is same then compare with name
            return 1; // we can write later
        });
        print(students);
    }
}

