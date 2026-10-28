package _01_CoreJava;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Employee3 {
    String name;
    String department;
    int salary;
    int age;

    public Employee3(String name, String department, int salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class Transaction {
    String category;
    int amount;

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }
    
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}


// there is no need of public static void main(String args[]) { .... } in java 25
// you can write simply     void main() { .... }


class StreamQuestion {
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
                .reduce(0, (a, b) -> a + b);
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
                .reduce(0, (a, b) -> a + b);
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














/// 1. find the all distinct elements from a list (remove duplicates)
    public void findAllDistinctElementFromList() {
        List<Integer> list = List.of(3,5,1,7,9,3,5,1);

        List<Integer> ans1 = list.stream().distinct().toList();
        System.out.println("distinct element: " + ans1);

        Set<Integer> ans2 = list.stream().collect(Collectors.toSet());
        System.out.println("distinct element: " + ans2);

        List<Integer> ans3 = list.stream()
                .mapToInt(x -> x)
                .distinct()
                .boxed()
                .collect(Collectors.toList());
        System.out.println("distinct element: " + ans3);
    }

/// 2. find the average of all numbers in a list.
    public void findTheAvg() {
        List<Integer> list = List.of(1,2,3,4,5);
        double avg = list.stream()
                .mapToInt(x -> x)
                .average()
                .orElse(0.0);
        System.out.println("avg: " + avg);
    }

/// 3. sort a list of integer in ascending and descending order using streams.
    public void sortTheListInAscendingAndDescendingOrder() {
        List<Integer> list = List.of(3,5,1,7,9);

        // ascending order
        List<Integer> ans1 = list.stream()
                .sorted((a, b) -> Integer.compare(a, b))
                .collect(Collectors.toList());
        System.out.println("ascending: " + ans1);

        // descending order
        List<Integer> ans2 = list.stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .collect(Collectors.toList());
        System.out.println("descending: " + ans2);

        // using mapToInt
        List<Integer> ans3 = list.stream()
                .mapToInt(x -> x)       // convert Stream<Integer> to IntStream
                .sorted()                       // ❌ sorted((a, b) -> Integer.compare(a, b)) -> can't because of primitive type
                .boxed()                        // convert back IntStream to Stream<Integer>
                .collect(Collectors.toList());
        System.out.println("ascending: " + ans3);

        // using mapToInt
        List<Integer> ans4 = list.stream()
                .mapToInt(x -> x)
                .map(x -> -x)  // make all negative  [-3,-5,-1,-7,-9]
                .sorted()           // sort ascending    [-9,-7,-5,-3,-1]
                .map(x -> -x)    // make all positive  [9,7,5,3,1]
                .boxed()        // convert back IntStream to Stream<Integer>
                .toList();
        System.out.println("descending: " + ans4);
    }

/// 4. count how many strings start with specific letter (like "A")
    public void countStringStartWithASpecificLatter() {
        List<String> fruits = List.of("Apple", "Banana", "Apricot", "Strawberry", "Avocado", "Mango");

        long ans = fruits.stream()
                .filter(x -> x.startsWith("A"))
                .count();
        System.out.println("count: " + ans);
    }

/// 5. join all string in comma-separated string.
    public void joinCommaSeparatedString() {
        List<String> fruits = List.of("Apple", "Banana", "Apricot", "Strawberry", "Avocado", "Mango");

        String ans = fruits.stream()
                .collect(Collectors.joining(", "));
        System.out.println("comma-separated string: " + ans);

        // prefix, suffix and hyphen separated
        String ans1 = fruits.stream()
                .collect(Collectors.joining("-","[", "]"));
        System.out.println("string: " + ans1);
    }

/// 6. check if all element are positive numbers.
    public void checkAllPositives() {
        List<Integer> nums = List.of(3,5,-1,7,-9);

        boolean ans = nums.stream()
                .allMatch(x -> x > 0);
        System.out.println("allPositives: " + ans);

        boolean ans1 = nums.stream()
                .mapToInt(x -> x)
                .allMatch(x -> x > 0);
        System.out.println("allPositives: " + ans1);
    }

/// 7. check if any number is divisible by 3
    public void checkAnyNumberDivisibleBy3() {
        List<Integer> nums = List.of(3,5,1,7,-9);

        boolean ans = nums.stream()
                .anyMatch(x -> x % 3 == 0);
        System.out.println("any number divisible by 3: " + ans);

        boolean ans1 = nums.stream()
                .mapToInt(x -> x)
                .anyMatch(x -> x % 3 == 0);
        System.out.println("any number divisible by 3: " + ans1);
    }

/// 8. Flatten the list of list
    public void flattenList() {
        List<List<Integer>> list = List.of(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5),
                Arrays.asList(6,7,8)
        );
        List<Integer> ans = list.stream()
                .flatMap(x -> x.stream())
                .toList();
        System.out.println("flatten list: " + ans);


        List<Integer> ans1 = list.stream()
                .flatMap(x -> x.stream())
                .mapToInt(x -> x)
                .boxed()
                .toList();
        System.out.println("flatten list: " + ans1);
    }

/// 9. find first the non-empty string in a list
    public void findNonEmptyString() {
        List<String> strings = Arrays.asList("", "", "Hello", "", "world");
        Optional<String> ans = strings.stream()
                .filter(s -> s.length() > 0)
                .findFirst();
        System.out.println("noneEmptyString: " + ans.get());

        String ans1 = strings.stream()
                .filter(s -> s.length() > 0)
                .findFirst()
                .orElse("no string found");
        System.out.println("noneEmptyString: " + ans1);
    }

/// 10. find the fifth-largest number in a list using stream.
    public void fifthLargestNumber() {
        List<Integer> nums = List.of(11, 12, 1, 3, 5, 15, 16, 8, 9, 20, 14, 7, 18, 4, 13, 10, 2, 6);

        int ans = nums.stream()
                .sorted((x, y) -> Integer.compare(y, x))
                .skip(4)
                .findFirst()
                .orElse(-1);
        System.out.println("fifthLargestNumber: " + ans);
    }

/// 11. Sort list of Employee By Salary
    public void sortListOfEmployeeBySalary() {
        List<Employee3> employees = List.of(
            new Employee3("Abhishek", "IT", 50000, 29),
            new Employee3("Ankit", "IT", 70000, 26),
            new Employee3("Rahul", "HR", 40000, 28),
            new Employee3("Tina", "HR", 45000, 31),
            new Employee3("Esha", "Finance", 60000, 27),
            new Employee3("Sanchit", "IT", 80000, 30)
        );

        List<Employee3> ans = employees.stream()
                .sorted((a, b) -> a.getSalary() - b.getSalary())
                .toList();
        System.out.println("sortListOfEmployeeBySalary: " + ans);
    }

/// 12. calculate the average age of the list of person object java streams
    public void avgAgeOfEmployee() {
        List<Employee3> employees = List.of(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30)
        );

        double ans = employees.stream()
                .mapToInt(x -> x.getAge())
                .average()
                .orElse(0.0);
        System.out.println("avgAgeOfEmployee: " + ans);
    }

/// 13. Partition number of each element in a list according to even or odd
    public void partitionByEvenOrOdd() {
        List<Integer> nums = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());

        Map<Boolean, List<Integer>> partitionBy = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partitionByEven: " + partitionBy.get(true) + " and partitionByOdd: " + partitionBy.get(false));
    }

/// 14. count the occurrence of each element in list
    public void countOccurrences() {
        List<String> fruits = List.of("apple", "banana", "apple", "orange", "banana", "apple");

        Map<String, Long> ans = fruits.stream()
                .collect(Collectors.groupingBy(ele -> ele, Collectors.counting()));
        System.out.println("countOccurrences: " + ans);
    }

/// 15. group of employee based on their department
    public void groupingByBasedDepartment() {
        List<Employee3> employees = List.of(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30)
        );

        Map<String, Long> ans = employees.stream()
                .collect(Collectors.groupingBy(ele -> ele.getDepartment(), Collectors.counting()));
        System.out.println("group by department: " + ans);
    }


    void main() {
        findAllDistinctElementFromList();
        findTheAvg();
        sortTheListInAscendingAndDescendingOrder();
        countStringStartWithASpecificLatter();
        joinCommaSeparatedString();
        checkAllPositives();
        checkAnyNumberDivisibleBy3();
        findNonEmptyString();
        fifthLargestNumber();

        sortListOfEmployeeBySalary();
        avgAgeOfEmployee();
        partitionByEvenOrOdd();
        countOccurrences();
        groupingByBasedDepartment();

























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
//        List<Employee3> employees = new ArrayList<>(Arrays.asList(
//                new Employee3("Abhishek", "IT", 50000),
//                new Employee3("Ankit", "IT", 70000),
//                new Employee3("Rahul", "HR", 40000),
//                new Employee3("Tina", "HR", 45000),
//                new Employee3("Esha", "Finance", 60000),
//                new Employee3("Naman", "Finance", 45000),
//                new Employee3("Sachit", "IT", 80000)
//        ));

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
//        List<Employee3> result = employees.stream()
//                .sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
////                .limit(2)
//                .toList();
//
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
