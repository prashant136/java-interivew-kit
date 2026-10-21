package _01_CoreJava.streams.questions;
// no need of class to write java code

// there is no need of public static void main(String args[]) { .... } in java 25
// you can write simply     void main() { .... }

// Streams

import java.util.*;
import java.util.stream.Collectors;

public class v25Java {

    //

    // 4. Find the first number greater than 10 (sorted)
    public Optional<Integer> findFirstGreaterThanTen(List<Integer> nums) {
        return nums.stream()
                .filter(x -> x > 10)
                .sorted()
                .findFirst();
    }

    // 5. Count numbers greater than 10
    public long countGreaterThanTen(List<Integer> nums) {
        return nums.stream()
                .filter(x -> x > 10)
                .count();
    }

    // 6a. Sum of all elements
    public int calculateSum(List<Integer> nums) {
        return nums.stream()
                .reduce(0, Integer::sum);
    }

    // 6b. Product of all elements
    public int calculateProduct(List<Integer> nums) {
        return nums.stream()
                .reduce(1, (a, b) -> a * b);
    }

    // 7. Sum of even elements
    public int sumOfEvenNumbers(List<Integer> nums) {
        return nums.stream()
                .filter(x -> x % 2 == 0)
                .reduce(0, Integer::sum);
    }

    // 8. Find maximum number in list
    public Optional<Integer> findMaxNumber(List<Integer> nums) {
        return nums.stream()
                .reduce(Integer::max); // Safe reduce without default 0 seed (handles negative numbers correctly)
    }

    // 9. Find all distinct element from list (remove duplicates)
    public Set<Integer> removeDuplicate(List<Integer> nums) {
//        return new HashSet<>(nums);
        return nums.stream().collect(Collectors.toSet());

//        return nums.stream().distinct().toList();  // return List<Integer>
    }

    // 10. Find the avg of list
    public Integer avgOfList(List<Integer> nums) {
        return 0;
    }



    void main() {

// -------- 1. Given a list of integer, remove duplicates and sort them in descending order.

//        List<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 1, 3, 2, 5, 4));
//        List<Integer> result = list.stream()
//                .distinct()
//                .sorted(Comparator.reverseOrder())
//                .collect(Collectors.toList());
//        System.out.println(result);


// ------- 2. from a list of integer, find all the odd number and return their squares.

//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//        List<Integer> result = list.stream()
//                .filter(ele -> ele % 2 != 0)
//                .map(ele -> ele * ele)
//                .collect(Collectors.toList());
//        System.out.println(result);


// ------- 3. we have list of integer. get 2nd , 3rd element form it and return them.

//        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
//        List<Integer> result = list.stream()
//                .skip(1)
//                .limit(2)
//                .collect(Collectors.toList());
//        System.out.println(result);


// -------- 4. we have list of integer. Find the second-highest number in the list.

//        List<Integer> list = new ArrayList<>(Arrays.asList(20, 10, 10, 45, 30, 45, 5, 20));
//        Optional<Integer> res = list.stream()
//                .distinct()  // [20, 10, 45, 30, 5]
//                .sorted(Comparator.reverseOrder()) // [45, 30, 20, 10, 5]
//                .skip(1)
//                .findFirst();
//        System.out.println(res.isPresent() ? res.get(): "throw exception");
        // orElseThrow - ???


// -------- 5. we have list of integer. divide numbers into even or odd.

//        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//        Map<Boolean, List<Integer>> result = list.stream()
//                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
//        System.out.println(result);


// -------- 6. we have list of strings. find the longest string in list.

//        List<String> list = new ArrayList<>(Arrays.asList("Java", "Spring Boot", "Api"));
//        Optional<String> max = list.stream()
//                .max(Comparator.comparing(elem -> elem.length()));
//        System.out.println(max.isPresent() ? max.get(): "");    // or, max.orElse("")

//        list.stream()
//                .max(Comparator.comparing(elem -> elem.length()))
//                .ifPresent(System.out::println);

// -------- 7. from the list of employees, find the first employee whose salary is greater than 50000.
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Abhishek", "IT", 50000),
                new Employee("Ankit", "IT", 70000),
                new Employee("Rahul", "HR", 40000),
                new Employee("Tina", "HR", 45000),
                new Employee("Esha", "Finance", 60000),
                new Employee("Naman", "Finance", 45000),
                new Employee("Sachit", "IT", 80000)
        ));

//        employees.stream()
//                .filter(emp -> emp.getSalary() > 50000)
//                .findFirst()
//                .ifPresent(System.out::println);

// -------- 8. from employee list, find top 2 highest paid employee
//        List<Employee> result = employees.stream()
//                .sorted((e1, e2) -> e2.getSalary() - e1.getSalary())      // integer overflow issue aa sakta hai.
//                .limit(2)
//                .toList();
// OR,
//        List<Employee> result = employees.stream()
//                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .limit(2)
//                .toList();
// OR,
//        List<Employee> result = employees.stream()
//                .sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
//                .limit(2)
//                .toList();

//        System.out.println(result);



// -------- 8. from emp list, sort employee by salary desc then by name if salaries are equal.

//        List<Employee> list = employees.stream()
//                .sorted((e1, e2) -> {
//                    if (e1.getSalary() > e2.getSalary()) {
//                        return -1;  // desc order
//                    } else if (e1.getSalary() < e2.getSalary()) {
//                        return 1;
//                    } else {
//                        // Do strings ko aapas me alphabetically compare ASC order me.
//                        // jaise: [ "Banana", "Apple", "Cherry" ] ka [Apple, Banana, Cherry]
//                        // (str1, str2) -> str1.compareTo(str2) & iska reverse krna hoto
//                        // (str1, str2) -> str2.compareTo(str1)
//                        // ye compareTo() method Comparable<String> se aaya hai.
//                        return e1.getName().compareTo(e2.getName());
//                    }
//                }).toList();
//
//        System.out.println(list);

// -------- 9. we have list of integer. find frequency of each element.

//        List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 4, 1, 2, 1, 1, 1, 5, 6, 5));
//        Map<Integer, Long> result = list.stream()
//                .collect(Collectors.groupingBy(ele -> ele, Collectors.counting()));
// OR,
//        Map<Integer, Integer> result = list.stream()
//                .distinct() // Pehle saare unique numbers nikaale [3, 2, 4, 1, 5, 6]
//                .collect(Collectors.toMap(
//                        element -> element,
//                        element -> Collections.frequency(list, element)
//                ));
//        System.out.println(result);




// ----------------------------------------------------------------------------------------------
//        List<Employee> employees = List.of(
//                new Employee(1, "Kaitlyn"),
//                new Employee(1, "David"),  // Duplicate ID
//                new Employee(2, "Alex"),
//                new Employee(3, "Chuck")
//        );
//
//        Map<Integer, String> employeeMap = employees.stream()
//                .collect(Collectors.toMap(
//                        Employee::getId,
//                        Employee::getName,
//                        (existingName, newName) -> existingName + ", " + newName  // merge function collision ko prevent karega.
//                ));
// --------------------------------------------------------------------------------------------------





// -------- 10. from employee list, find how many employee are in department.
//        Map<String, Long> result = employees.stream()
//                .collect(Collectors.groupingBy(ele -> ele.getDepartment(), Collectors.counting()));
//        System.out.println(result);



// -------- 11. given a list of transactions, find total transaction amount per category.
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Food", 100),
                new Transaction("Food", 200),
                new Transaction("Food", 150),
                new Transaction("Shopping", 300),
                new Transaction("Shopping", 250),
                new Transaction("Shopping", 100),
                new Transaction("Utilities", 400),
                new Transaction("Utilities", 300),
                new Transaction("Entertainment", 500),
                new Transaction("Entertainment", 200),
                new Transaction("Travel", 700),
                new Transaction("Travel", 300)
        );
//        Map<String, Integer> result = transactions.stream()
//                .collect(Collectors.groupingBy((txn -> txn.getCategory()),
//                        Collectors.summingInt(txn -> txn.getAmount())));
//
//        System.out.println(result);



// -------- 12. from list of employee, find avg salary of employee in each department
//        Map<String, Double> result = employees.stream()
//                .collect(Collectors.groupingBy(emp -> emp.getDepartment(),
//                        Collectors.averagingInt(emp -> emp.getSalary())));
//
//        System.out.println(result);

// -------- 13. from list of employee, find highest paid employee in each department

//        Map<String, Optional<Employee>> result = employees.stream()
//                .collect(Collectors.groupingBy(emp -> emp.getDepartment(),
//                        Collectors.maxBy(Comparator.comparingInt(emp -> emp.getSalary()))));
//
//        System.out.println(result);


// -------- 14. in employee list, convert a list into comma-separated string of employee name
//        String result = employees.stream()
//                .map(emp -> emp.getName())
//                .collect(Collectors.joining(","));
//        System.out.println(result);


// -------- 15. Given two lists, find the common elements b/w them
//        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
//        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));

//        List<Integer> result = list2.stream()
//                .filter(e -> list1.contains(e))
//                .toList();
//        System.out.println(result);

        // better way -
//        Set<Integer> set = new HashSet<>(list1);
//        List<Integer> result = list2.stream()
//                .filter(e -> set.contains(e))
//                .toList();
//        System.out.println(result);


// -------- 17. you have list of list. convert it into a single list with all the elements. remove duplicates if any.
        List<List<Integer>> listOfList = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(3, 4, 5, 6),
                Arrays.asList(7, 8, 1, 2),
                Arrays.asList(9, 10, 5, 6),
                Arrays.asList(11, 12, 7, 8)
        );

//
//        List<Integer> result = listOfList.stream()
//                .flatMap(innerList -> innerList.stream())
//                .distinct()
//                .toList();
//        System.out.println(result);

        /// flatmap() takes multiple stream and converts them into a single stream.
        /// we iterate listOfList. flatmap have five different $04DSA.streams, here five inner list (five different $04DSA.streams).
        /// the job of flatmaop -> (it takes multiple $04DSA.streams, then it converts them in single $04DSA.streams)
        /// [[1,2,3,4], [3,4,5,6], [7,8,1,2], [9,10 5,6], [11,12,7,8]] -> [[1,2,3,4 3,4,5, 6,7,8,1,2,9,10,5,6,11,12,7,8]]



// -------- 16. form employee list, print all employee name, their order doesn't matter but the operation should be very fast.
        ///  you should think of multiple threads (order doesn't matter and very fast)
//        employees.parallelStream()
//                .map(Employee::getName)
//                .forEach(System.out::println);


// -------- 17. find error  ?? -
//        List<Integer> list = new ArrayList<>();
//        IntStream.range(1, 1000)
//                .parallel()
//                .forEach(ele -> list.add(ele));


// -------- 18. form employee list, computer total combined salary of all employees. name. operation should be very fast.
//
//        Integer total = employees.parallelStream()
//                .map(emp -> emp.getSalary())
//                .reduce(0, (a, b) -> a + b);
//        System.out.println(total);



// -------- 19. we have list of integer. divide numbers into even or odd.




// -------- 16. we have list of integer. divide numbers into even or odd.








//        findFirstGreaterThanTen(nums).ifPresent(val -> System.out.println("First number > 10 (sorted): " + val));
//        System.out.println("Count > 10: " + countGreaterThanTen(nums));
//        System.out.println("Sum: " + calculateSum(nums));
//        System.out.println("Product: " + calculateProduct(nums));
//        System.out.println("Sum of Even Numbers: " + sumOfEvenNumbers(nums));
//        findMaxNumber(nums).ifPresent(max -> System.out.println("Max Number: " + max));

    }
}