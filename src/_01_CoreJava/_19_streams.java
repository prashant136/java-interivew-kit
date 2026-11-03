package _01_CoreJava;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/// example of FI - Runnable, Callable and many others.
///
/// ?? Collection vs collections ??
/// Collection is parent interface for various Data structures.
///
/// ?? Stream - it is an interface. It is used to process data. stream doesn't have its own memory. supports functional style program.
///
/// Q:) Stream vs streams() -> Stream is interface and stream() is a method present inside Collection Interface. It is used to convert any Collection to Stream.
///
/// Q:) Why intermediate operations are lazy loaded ?
/// Intermediate operations are lazy loaded to for performance optimization. Instead of executing immediately, they simply build "pipeline" of tasks and wait until a terminal operation like (collect() or forEach()) is called.
///
/// Q:) Terminal Operations ? They trigger the execution of the stream and produce a final result. They are eager in nature.
///
/// Q:) What is a Functional Interface ?
/// A Functional Interface in Java is an interface that contains exactly one abstract method. It can have multiple default or static methods, but only one abstract method.
///     We can use @FunctionalInterface annotation and acts as the foundation for Lambda Expressions.
///
/// Q:) What is a Lambda Expression ?
/// A Lambda Expression is an anonymous function. A function without a name, return type, or access modifier.
/// It provides a clear, concise way to implement a Functional Interface without writing boilerplate anonymous inner class code.
///
/// Q:) What is Java Streams ?
/// It is an interface (introduced in java 8). It is used to processing the data. stream doesn't have its own memory. It provides modern and efficient way to write functional style programing in java.
///
/// Q:) What is a Method Reference ?
/// A Method Reference is a shorthand notation for a lambda expression that simply calls an existing method. It makes the code cleaner and more readable using the double colon :: syntax.
///
/// Q:) Why function interface is introduced ?
/// Functional Interfaces were introduced in Java 8 to enable Functional Programming. their main goal is support Lambda Expressions, reduce boilerplate code, and support streams.

class Employee1 {
    int id;
    String name;
    String department;

    Employee1(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

public class _19_streams {
    void main() {
        List<Employee1> employees = List.of(
                new Employee1(1, "Amit", "Tech"),
                new Employee1(2, "Neha", "HR"),
                new Employee1(3, "Rahul", "Tech"),
                new Employee1(4, "Priya", "Finance"),
                new Employee1(5, "Vikas", "Tech"),
                new Employee1(6, "Sneha", "Marketing"),
                new Employee1(7, "Arjun", "Tech")
        );

// ---------- print the employee name whose department is tech -------------
//        employees.stream()
//                .filter(x -> x.getDepartment().equals("Tech"))
//                .map(x -> x.getName())
//                .forEach(System.out::println);

// --- this example show the execution flow of stream (for better understanding)
        employees.stream()
                .filter(x -> {
                    System.out.println("filter called");
                    if (x.getDepartment().equals("Tech")) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .map(x -> {
                    System.out.println("map called");
                    return x.getName();
                })
                .forEach(System.out::println);

        /// ------- return the employee who key: id and value: name ------
        Function<Employee1, Integer> f1 = (emp) -> emp.getId();
        Function<Employee1, String> f2 = (emp) -> emp.getName();

        Map<Integer, String> ans = employees.stream()
                .collect(Collectors.toMap(f1, f2));
        // System.out.println(ans);

        // OR,
        Map<Integer, String> ans1 = employees.stream()
                .collect(Collectors.toMap((x) -> x.getId(), (x) -> x.getName()));

        // OR,
        Map<Integer, String> ans2 = employees.stream()
                .collect(Collectors.toMap(Employee1::getId, Employee1::getName));




        List<String> names = new ArrayList<>(List.of("Aman", "Raj", "Rohit", "Vikas", "Sneha", "Resham", "Rina", "Ronit"));

// ----- find first employee whose name srat with R
        Optional<String> first = names.stream()
                .filter(str -> str.startsWith("R"))
                .findFirst();
        System.out.println(first.get());

        boolean any = names.stream()
                .anyMatch(str -> str.startsWith("R"));
        System.out.println(any);

        boolean all = names.stream()
                .allMatch(str -> str.length() > 5);
        System.out.println(all);

        boolean none = names.stream()
                .noneMatch(str -> str.startsWith("Z"));
        System.out.println(none);
    }
}

/// Intermediate Operations - They transform or process the data in a stream, but they do not actually produce a result on their own.
/// Terminal Operations - They trigger the execution of the stream and produce a final result.
/// Intermediate operation are lazy loaded, they execute only when a terminal operation is added.
/// we can combine many intermediate operation that is executed lazily when terminal operation is called.

/// 🚩 Terminal operation can run without any intermediate operations in stream.

/**
 * ----------------------------- important methods ----------------------------
 * 1. (Intermediate) map - transform each element into another form in stream
 * 2. (Intermediate) filer - filter elements from a stream based on a condition
 * 3. (Terminal) forEach - execute any action on each element of stream (like loop)
 * 4. (Terminal) collect - collect() is a method inside Stream interface which take all the element from the Stream and transform them into list, Set or Map.
 *      example - .collect(Collectors.toList()) -> collect in the form of List (return type - list)
 *                .collect(Collectors.toSet()) ->  collect in the form of Set (return type - set)
 *                .collect(Collectors.toMap()) -> collect in the form of key value pair. (we need to define key and value both lambdas.)
 *                       Map<Integer, String> res = employees.stream()
 *                                                      .collect(Collectors.toMap((x) -> x.getId(), (x) -> x.getName()));
*                  .collect(Collectors.joining() -> joins a sequence of string into a single String. it only works in Stream<String>
 *
 *
 *          List<String> words = List.of("Java", "Stream", "API");
 *          String result = words.stream().collect(Collectors.joining());
 *          System.out.println(result); // Output: JavaStreamAPI
 *
 *          List<String> words = List.of("Java", "Stream", "API");
 *          String result = words.stream().collect(Collectors.joining(", ")); // Pass separator here
 *          System.out.println(result); // Output: Java, Stream, API
 *
 *          List<String> words = List.of("Java", "Stream", "API");
 *          String result = words.stream().collect(Collectors.joining("-", "[", "]"));  // Arguments: (delimiter, prefix, suffix)
 *          System.out.println(result); // Output: [Java-Stream-API]
 *
 *
 *
 *           .collect(Collectors.partitioningBy()) -> collector splits a stream into exactly two groups based on a boolean condition.
 *                       output - Map<Boolean, List<T>>
 *                       Keys that are true contain elements that matched the condition.
 *                       Keys that are false contain elements that failed the condition.
 *
 *            .collect(Collectors.groupingBy()) method is used to group elements of a stream based on some condition.
 *            It collects elements into a Map, with keys as the grouping criteria and values as lists or aggregated results.
 *                      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
 *                      Map<Boolean, List<Integer>> groupedByOddEven = numbers.stream()
 *                                                  .collect(Collectors.groupingBy(num -> num % 2 == 0));
 *
 *                      System.out.println(groupedByOddEven);
 *
 *                      Key: Woh category ya property hoti hai jiske basis par grouping hui hai.
 *                      Value: Us category mein aane wale items ki ek List hoti hai.
 *
 *
 *
 *
 * 5. (Terminal) findFirst() - (puri bakchodi krke single element chahiye) return the first element of the stream. (return type - Optional<T>)
 * 6. (Terminal) findAny() ???????? why (doubt) ???????? - () return any element of the stream. (return type - Optional<T>)
 *
 * 7. (Terminal) anyMatch() - return true if any element matches a given condition. (return type boolean)
 * 8. (Terminal) anyMatch() - return true if any element matches a given condition. (return type boolean)
 * 9. (Terminal) anyMatch() - return true if any element matches a given condition. (return type boolean)
 * 10. (Terminal) allMatch() - return true if all the elements match a given condition. (return type boolean)
 * 11. (Terminal) noneMatch() - return true if no elements match a given condition. (return type boolean)
 *
 * 12. (Terminal) reduce() - is used to combine all elements of stream into a single value. return type is combined value.
 *          // if you don't provide initial value in reduce then it will return Optional<T> else return normal value type.
 *
 *
 * 13. (Intermediate) flatMap() - is used transforms each element into a stream and flattens all streams into a single stream.
 *                                 flattening nested data structures.
 *
 * 14. (Terminal) min(), max() - takes Comparator / lambda / method reference -
 *      Optional<T> min = stream.min((a, b) -> Integer.compare(a, b));
 *      Optional<T> min = stream.min((a, b) -> Long.compare(a, b));
 *      Optional<T> min = stream.min((a, b) -> Double.compare(a, b));
 *      max and min Optional<T> return karte hai.
 *
 * (Intermediate) sorted() - sorted me Integer.compare() use karo. baki build in methods hai, us bakchodi se bacho.
 *
 * 15. (Terminal) count() - return type long
 *
 * 16. (Intermediate) skip() and limit() -
 *      List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
 *      List<Integer> result = numbers.stream()
 *                               .skip(3)  // Discards 1, 2, 3
 *                               .limit(4) // Takes the next 4 elements
 *                               .toList();
 *      System.out.println(result); // Output: [4, 5, 6, 7]
 *
 *
 *
 *
 *
 *
 *
 *
 * 20.  ****** (Intermediate) mapToInt() - mapToInt() method is used to transform a generic object stream (Stream<T>) into a primitive IntStream. ******
 *      ex- List<Integer> scores = List.of(85, 92, 78);
 *          double averageScore = scores.stream()
 *                             .mapToInt(ele -> ele) // Converts Stream<Integer> to IntStream
 *                             .average()
 *                             .orElse(0.0);
 *
 *     ex - suppose a Product class have (name, price). find the total cost.
 *     class Product {
 *          private String name;
 *          private int price;
 *          public Product(String name, int price) {
 *              this.name = name;
 *              this.price = price;
 *          }
 *          public int getPrice() { return price; }
 *      }
 *      public class Main {
 *         void main() {
 *         List<Product> cart = List.of(
 *             new Product("Book", 15),
 *             new Product("Pen", 2),
 *             new Product("Backpack", 45)
 *         );
 *
 *         int totalCost = cart.stream()
 *                             .mapToInt(product -> product.getPrice()) // covert Stream<Product> to IntStream()
 *                             .sum();                                  // Terminal operation on IntStream
 *
 *         System.out.println("Total: " + totalCost); // Output: Total: 62
 *     }
 * }
 *
 */
class Employee2 {
    private String name;
    private List<String> skills;

    public Employee2(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }
}

class MoreExamples {
    void main() {

        /// ----------------------- reduce() ----------------------
        // find sum of list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum: " + sum);

        // Finding the Longest Word
        List<String> words = Arrays.asList("Java", "Stream", "Reduction", "API");
        Optional<String> longestWord = words.stream()
                .reduce((word1, word2) -> word1.length() >= word2.length() ? word1 : word2); // Optional return type cause it doesn't have initial value.
        System.out.println("longestWord: " + longestWord.get());

        // find the minimum
        List<Integer> values = Arrays.asList(42, 17, 89, 5, 23);
        Optional<Integer> min = values.stream().min((a, b) -> Integer.compare(a, b));     // Optional type return
        System.out.println("min: " + min.get());


        // --------------------------- flatmap() ----------------------------------
        List<List<String>> names = List.of(
                List.of("Alice", "Bob"),
                List.of("Charlie", "David")
        );
        names.stream().flatMap(x -> x.stream()).forEach(x -> System.out.println(x));

        // find distinct list of all skills across entire employee
        List<Employee2> employees = List.of(
            new Employee2("Alice", List.of("Java", "Spring", "SQL")),
            new Employee2("Bob", List.of("Python", "Docker", "Java")),
            new Employee2("Charlie", List.of("AWS", "Kubernetes", "Spring"))
        );

        Set<String> uniqueSkills = employees.stream()
                .flatMap(emp -> emp.getSkills().stream())
                .collect(Collectors.toSet());
        System.out.println("uniqueSkills: " + uniqueSkills);
    }
}

 /** ------------------------------ Primitive Streams ---------------------------------
  * Streams generally work with Collections (List<String>, List<Employee>, Set<Integer> etc).
  * Java Provides special Streams for primitive types.
  * int -> IntStream
  * long -> LongStream
  * double -> DoubleStream
  *
  * There is a method called Arrays.stream() act as bridge b/w arrays and stream. It allows you to convert array to stream.
  * convert primitive array to stream -
  * - Arrays.stream(int[] array) → returns an IntStream
  * - Arrays.stream(long[] array) → returns a LongStream
  * - Arrays.stream(double[] array) → returns a DoubleStream
  *
  * Most Important --- mapToInt() method
  *
  *

 */
 class PrimitiveStreamMain {
     void main() {
        IntStream intStream = IntStream.range(1, 5);        // 1,2,3,4
        int sum = intStream.sum();
        System.out.println(sum);

         IntStream intStream1 = IntStream.rangeClosed(1, 5);        // 1,2,3,4
         OptionalDouble average = intStream1.average();
         System.out.println(average.getAsDouble());

         DoubleStream doubleStream = DoubleStream.of(2.3, 4.1, 7.3);
         System.out.println(doubleStream.average().getAsDouble());
     }
 }

/// ---------------------------------------------------------------------------------
class parallel_Stream {
     void main() {
         /// parallelStream() -> normally stream process the element sequentially,
         ///     but sometimes we want to process large collection faster using multiple thread.
         ///     It allows to process the stream parallel using multiple thread making the processing faster.

         /// 👉 parallelStream uses ForkJoinPool.commonPool() thread pool by default.

         /// ----------- parallelStream example -------------------
         List<String> arr = new ArrayList<>(List.of("Aman", "Raj", "Rohit", "Vikas", "Sneha", "Resham", "Rina", "Ronit", "Ankit", "Vikram", "Rudra", "Hari", "Roko"));

         arr.parallelStream()
                 .filter(str -> str.startsWith("R"))
                 .forEach(x -> System.out.print(x + " "));
         System.out.println();

         arr.parallelStream()
                 .filter(str -> str.startsWith("R"))
                 .forEachOrdered(x -> System.out.print(x + " ")); // for orderedList
         System.out.println();


         // --------- parallel stream example ---------- (good for large dataset, you will notice performance difference between streams and parallelStream)
         List<Integer> list = new ArrayList<>();

         for(int i=0; i<100000; i++) {
             list.add(i);
         }

         long start = System.currentTimeMillis();
/// ------------------- use stream to test performance ----------------------
         list.stream()
                 .map(this::heavyWork)
                 .toList();

/// ----------------- use parallelStream to test performance --------------------
//        list.parallelStream()
//                .map(x -> heavyWork(x))
//                .collect(Collectors.toList());

         long end = System.currentTimeMillis();
         System.out.println("time diff to find which one is fast: " + (end - start));
     }

     int heavyWork(int num) {
         for(int i=1; i<10000; i++) {
             num = num * i;
         }
         return num;
     }
 }



//// -------------------------------- Important -------------------------------
//        List<String> names = Arrays.asList("AAA", "BBB");  // ye immutable hai, runtime error UnsupportedOperationException
//        names.add("CCC");
//        System.out.println(names);

//        List<String> names = new ArrayList<>(Arrays.asList("AAA", "BBB"));    // ye mutable hai because arraylist me wrapped hai.
//        names.add("CCC");
//        System.out.println(names);

//        List<String> names = new ArrayList<>("AAA", "BBB"); // ❌ Compile time error