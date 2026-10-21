package _01_CoreJava;

// https://www.youtube.com/watch?v=TcjIB2KLT54&list=PLwsTfOnizFBwpwEw4iYonpl3k4Zx6oONq&index=5
public class _4_strings {
    void main() {
//        String name1 = "Rahul";
//        String name2 = "Rahul";
//        System.out.println(name1 == name2);    // true
//        System.out.println(name1.equals(name2));    // true
//
//        String city1 = new String("Delhi");
//        String city2 = new String("Delhi");
//        System.out.println(city1 == city2);     // false
//        System.out.println(city1.equals(city2));    // true
//
//        String name3 = "Naman";
//        String name4 = new String("Naman");
//        System.out.println(name3 == name4);     // false
//        System.out.println(name3.equals(name4));    // true
//
//        String name5 = new String("Naman");     // ready for gc (unreferenced)
//        name5 = "Naman";        // I explicitly change the ref. variable to literal value.
//        System.out.println(name3 == name5);     // true
//        System.out.println(name3.equals(name5));    // true
//
//        name5 = null;
//        System.out.println(name3 == name5);     // false
//        System.out.println(name3.equals(name5));    // false
//
//        System.out.println(name5.equals(name3));    // NullPointerException
    }
}

/// String constant pool - All the objects are stored in heap memory. inside heap memory, a small area called the string constant pool or string pool.

/// String name1 = "Shivam";  here "Shivam" is string literal and this literal is stored inside string pool.
/// name1 is reference variable, it's pointing towards the literal "Shivam" which is inside the pool.

/// String city1 = new String("Delhi");  becoz of new keyword Java create a new string object in the heap
/// but it's a string. So Java also keeps the literal "delhi" in the pool.
/// 2 copies are made { one in the heap memory and other in string pool. and ref. variable city1 is pointing towords the one heap memory. }

/// why string pool is needed ? [ save memory and improve performance by using identical string literals ]
/// String name2 = "Shivam"; now jvm will check string pool first. if string literal ("Shivam") is there. JVM will return will not create new string.
/// it just makes name2 point to the same "Shivam" in the pool. so both name1, name2 two are pointing to the same object in the pool. No duplicates, no extra memory wasted.
/// Even if you create thousands of literal which have the same value, all their reference variables will be pointing towards the single literal in the pool.

/// == is used to compare references (the address of string not the actual value).
/// .equals() is used to compare values of strings.
/*
 +-------------------------------------------------------------------------+
 |                               HEAP SPACE                                |
 |                                                                         |
 |   city1 ----> Delhi                                                     |
 |                                                                         |
 |   city2 ----> Delhi                                                     |
 |                                                                         |
 |                      +-----------------------------------+              |
 |                      |       STRING CONSTANT POOL        |              |
 |                      |                                   |              |
 |                      |  name2 ---> Shivam <--- name1     |              |
 |                      |                                   |              |
 |                      |                                   |              |
 |                      |                                   |              |
 |                      |   Delhi <--- jvm internally       |              |
 |                      +-----------------------------------+              |
 +-------------------------------------------------------------------------+
*/

/// here, you can see "Delhi" in string pool (no pointer is pointing). hence jvm is pointing internally
/// if garbage collection take place what happen to string pool ?
/// Garbage collection is process od cleaning all those Objects which are not referenced by anyone. JVM does it time to time on its own.
/// "Delhi" is referenced by jvm (it's not unreferenced). gc isko clean nhi karenga.

///       String name3 = "Naman";     ->  "Naman" is create in string pool jiska ref. pointer name3 hai.
///       String name4 = new String("Naman");   -> 👉 java name4 as ref. variable ek new object bna dega inside heap. but string pool me ye same "Naman" ko point karega. alag se stirng pool me kuch nhi bnayega.



/// ---------------------- String immutability --------------------------
/// string pool doesn't contain duplicate value. we create a literal (stored in string pool) and we create another literal (check if same literal is present in the string pool or not).
/// if same literal is present is string pool then it will use the same, it doesn't create new literal.
/// string literal reusability. (reason behind immutability)


class StringImmutability {
    void main(String[] args) {
        String name = "Shivam";
        String name1 = "Shivam";
        String name2 = "Shivam";

        /// here name, name1 and name3 are pointing to same string literal.
        /// a new literal is created "Naman".  {inside string poll :: name ---> "Naman" and name1, name2 ---> "Shivam" }
        name = "Naman";
        System.out.println(name);
        System.out.println(name1);

        String name3 = new String("Shivam");
        name3 = new String("Naman");
        name3 = new String("Shivam");

        /// there are 3 objects in heap memory. but 2 literal in string pool.
/*
 +-------------------------------------------------------------------------+
 |                               HEAP SPACE                                |
 |                                                                         |
 |   Shivam (Heap Object 1)                                                |
 |   Naman  (Heap Object 2)                                                |
 |   name ----> Shivam                                                     |
 |                                                                         |
 |                      +-----------------------------------+              |
 |                      |       STRING CONSTANT POOL        |              |
 |                      |                                   |              |
 |                      |   Shivam                          |              |
 |                      |   Naman                           |              |
 |                      +-----------------------------------+              |
 +-------------------------------------------------------------------------+
*/
        String s1 = "Hello";
        String s2 = s1 + "World";   // when we add to literal, it will be treated like new object (store in heap)
        System.out.println(s1);
        System.out.println(s2);

/*
 +-------------------------------------------------------------------------+
 |                               HEAP SPACE                                |
 |                                                                         |
 |   s2 ----> Hello World                                                  |
 |                                                                         |
 |                      +-----------------------------------+              |
 |                      |       STRING CONSTANT POOL        |              |
 |                      |                                   |              |
 |                      |   Hello <---- s1                  |              |
 |                      |                                   |              |
 |                      |   World                           |              |
 |                      +-----------------------------------+              |
 +-------------------------------------------------------------------------+
*/

        String s3 = "Australia";
        s3 = s3 + "Kangaroo";
        System.out.println(s3);
/*
 +-------------------------------------------------------------------------+
 |                               HEAP SPACE                                |
 |                                                                         |
 |   s1 ----> Australia Kangaroo                                           |
 |                                                                         |
 |                      +-----------------------------------+              |
 |                      |       STRING CONSTANT POOL        |              |
 |                      |                                   |              |
 |                      |   Australia                       |              |
 |                      |                                   |              |
 |                      |   Kangaroo                        |              |
 |                      +-----------------------------------+              |
 +-------------------------------------------------------------------------+
*/
    }
}

class StringTypes {
    void main() {
        String str = "Hello";
        String s4 = str.concat("World");        // it creates another string "HelloWorld" but it could replace the old one.

/*
 +-------------------------------------------------------------------------+
 |                               HEAP SPACE                                |
 |                                                                         |
 |   s4 ----> HelloWorld                                                   |
 |                                                                         |
 |                      +-----------------------------------+              |
 |                      |       STRING CONSTANT POOL        |              |
 |                      |                                   |              |
 |                      |   Hello <---- str                 |              |
 |                      |                                   |              |
 |                      |   World                           |              |
 |                      +-----------------------------------+              |
 +-------------------------------------------------------------------------+
*/



        StringBuffer str1 = new StringBuffer("Hello");
        str1.append("World");

        StringBuilder str2 = new StringBuilder("Hello");
        str2.append("World");

        System.out.println(str);
        System.out.println(str1);
        System.out.println(str2);
    }
}

/// string are immutable but StringBuffer, StringBuilder are mutable.
/// Project where String value keeps on changing, use StringBuffer and StringBuilder.
/// Memory - String literal is stored inside heap memory (String Pool), StringBuffer and StringBuilder stored in heap.
/// String and StringBuffer is Thread Safe. StringBuilder is not Thread Safe. (in multithread env. String and StringBuffer will work but StringBuilder will fail)


class StringTypes2 {
    void main() {
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        StringBuffer s3 = new StringBuffer("Hello");
        StringBuffer s4 = new StringBuffer("Hello");

        StringBuilder s5 = new StringBuilder("Hello");
        StringBuilder s6 = new StringBuilder("Hello");

        System.out.println(s1==s2);     // false
        System.out.println(s3==s4);     // false
        System.out.println(s5==s6);     // false

        System.out.println(s1.equals(s2));      // true (when we click on .eqauls here, it goes to String class)

        // value is same then why it return false -> (in case of StringBuilder and StringBuffer -> it compare reference)
        System.out.println(s3.equals(s4));      // false (when we click on .eqauls() -> goes to Object class)
        System.out.println(s5.equals(s6));      // false (when we click on .eqauls() here -> goes to Object class)
    }
}

class StringThreadSafety {
    void main() throws InterruptedException {
//        StringBuilder s1 = new StringBuilder(); // not thread safe
        StringBuffer s1 = new StringBuffer();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                s1.append('a');
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                s1.append('a');
            }
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final length: " + s1.length());
    }
}