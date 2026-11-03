package _01_CoreJava;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// there is no need of public static void main(String args[]) { .... } in java 25
// you can write simply     void main() { .... }


class StreamQuestion {
    /// [ REMEMBER ] -------------------- String Comparison ------------------
    /// 1. string by length                 ::  .sorted(Integer.compare(a.length(), b.length()))
    /// 2. string by length (reverse)       ::  .sorted(Integer.compare(b.length(), a.length()))
    /// 3. alphabetically (a - z)           ::  .sorted((a, b) -> a.compareTo(b))
    /// 4. alphabetically reverse (z - a)   ::  .sorted((a, b) -> b.compareTo(a))

    /// 0. Find maximum number in list
     public void findMaxNumber() {
         List<Integer> nums = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
         nums.stream()
                .reduce(Integer::max);
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

/// 16. Find the first number greater than 10 (sorted)
    public void findFirstGreaterThanTen() {
        List<Integer> nums = List.of(2, 19, 10, 12, 5, 12, 2, 7);
        int ans = nums.stream()
                .filter(x -> x > 10)
                .sorted()
                .findFirst()
                .orElse(-1);
        System.out.println("findFirstGreaterThanTen: " + ans);
    }

/// 17. we have list of String. Find the longest string in a list.
    public void findtheLongestString() {
        List<String> list = Arrays.asList("Java", "SpringBoot", "API");

        Optional<String> ans = list.stream()
                .max((a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("findtheLongestString: " + ans.get());
    }

/// 18. find first employee whose salary is greater than 50000
    public void findEmployeeWhoseSalaryGrter50000() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        Employee3 ans = employees.stream()
                .filter(emp -> emp.getSalary() > 50000)
                .findFirst()
                .orElse(new Employee3("No one", "", 0, 0));
        System.out.println("salary greater than 50000: " + ans);

        employees.stream()
                .filter(emp -> emp.getSalary() > 50000)
                .findFirst()
                .ifPresent(emp -> System.out.println("salary greater than 50000: " + emp.getSalary()));
    }

/// 19. find top 2 highest paid employees
    public void top2HighestSalariedEmployee() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        List<Employee3> ans = employees.stream()
                .sorted((a, b) -> Integer.compare(b.getSalary(), a.getSalary()))
                .limit(2)
                .toList();
        System.out.println("top2HighestSalariedEmployee: " + ans);
    }

/// 20. Sort employees by salary, then by name (if salary is same sort them basis of their name)
    public void sortBySalaryThenByName() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        List<Employee3> ans = employees.stream()
                .sorted((e1, e2) -> {
                    if(e1.getSalary() != e2.getSalary()) {
                        return Integer.compare(e1.getSalary(), e2.getSalary());
                    }
                    // alphabetically sort by their name
                    return e1.getName().compareTo(e2.getName());
                })
                .toList();
        System.out.println("sortBySalaryThenByName: " + ans);
    }

/// 21. count how many employees are in each department
    public void countEmployeeEachDepartment() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        Map<String, Long> res = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment(), Collectors.counting()));
        System.out.println("countEmployeeEachDepartment: " + res);
    }

/// 22. find total transaction amount per category
    public void totalTransactionPerCategory() {
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

        Map<String, Integer> ans = transactions.stream()
                .collect(Collectors.groupingBy(txn -> txn.getCategory(), Collectors.summingInt(txn -> txn.getAmount())));
        System.out.println("totalTransactionPerCategory: " + ans);
    }

/// 23. find the average salary of employees in each department
    public void averageSalaryPerDepartment() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        Map<String, Double> res = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment(), Collectors.averagingInt(emp -> emp.getSalary())));
        System.out.println("averageSalaryPerDepartment: " + res);
    }

/// 24. find highest paid employee in each department
    public void highestSalariedEmployeeEachDepartment() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        Map<String, Optional<Employee3>> ans = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getDepartment(), Collectors.maxBy((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()))));
        System.out.println("highestSalariedEmployeeEachDepartment: " + ans);

        // will see another way ---
    }

/// 25. convert the list into a comma-separated string of Employee names.
    public void commaSeparatedEmployeeName() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        String ans = employees.stream()
                .map(Employee3::getName)
                .collect(Collectors.joining(", "));
        System.out.println("commaSeparatedEmployeeName: " + ans);
    }

/// 26. Given two list, find common elements between them.
    public void findCommonElement() {
        List<Integer> list1 = Arrays.asList(1,2,3,4);
        List<Integer> list2 = Arrays.asList(3,4,5,6);

        // use hashSet for faster lookups (applicable for large data set)
        Set<Integer> set = new HashSet<>(list1);

        List<Integer> common = list2.stream()
                .filter(x -> set.contains(x))
                .toList();
        System.out.println("findCommonElement: " + common);

    }

/// 27. convert into single list with all the elements. remove duplicates if any.
    public void convertIntoSingleList() {
        List<List<Integer>> listOfList = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(3,4,5,6),
                Arrays.asList(7,8,1,2),
                Arrays.asList(9,10,5,6),
                Arrays.asList(11,12,7,8)
        );

        List<Integer> ans = listOfList.stream()
                .flatMap(list -> list.stream())
                .distinct()
                .toList();
        System.out.println("list: " + ans);
    }

/// 28. In the below code, find error ?
    public void findError() {
//        List<Integer> list = new ArrayList<>();       // list is not thread safe.
//        IntStream.range(1, 1000)
//                .parallel()
//                .forEach(ele -> list.add(ele);

// correct code ???
    }


/// 29. in employee list. print all employee names, order doesn't matter but operation should be very fast. use parallelStream
    public void printEmployeeVeryFast() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        employees.parallelStream()
                .map(Employee3::getName)
                .forEach(System.out::println);   // ordered doesn't matter

//        employees.parallelStream()
//                .map(Employee3::getName)
//                .forEachOrdered(System.out::println);   // ordered list
    }

/// 30. compute total combined salary of all Employee. Operation should be very fast
    public void combinedSalaryOfEmployeesVeryFast() {
        List<Employee3> employees = new ArrayList<>(Arrays.asList(
                new Employee3("Abhishek", "IT", 50000, 29),
                new Employee3("Ankit", "IT", 70000, 26),
                new Employee3("Rahul", "HR", 40000, 28),
                new Employee3("Tina", "HR", 45000, 31),
                new Employee3("Esha", "Finance", 60000, 27),
                new Employee3("Sanchit", "IT", 80000, 30),
                new Employee3("Pushpa", "Marketing", 50000, 31),
                new Employee3("Sumit", "Marketing", 52000, 26)
        ));

        int ans = employees.parallelStream()
                .map(Employee3::getSalary)
                .reduce(0, (a, b) -> a + b);
        System.out.println("combinedSalaryOfEmployeesVeryFast: " + ans);
    }

/// 30. grouping by string length
    public void groupbyStringLength() {
        List<String> list = List.of("java", "aws", "springboot", "you", "four");

        // Length ke basis par group karna
        Map<Integer, List<String>> result = list.stream()
                .collect(Collectors.groupingBy(str -> str.length()));
        System.out.println("grouping: " + result);
    }


    void main() {
        findMaxNumber();
        findAllDistinctElementFromList();
        findTheAvg();
        sortTheListInAscendingAndDescendingOrder();
        countStringStartWithASpecificLatter();
        joinCommaSeparatedString();
        checkAllPositives();
        flattenList();
        checkAnyNumberDivisibleBy3();
        findNonEmptyString();
        fifthLargestNumber();

        sortListOfEmployeeBySalary();
        avgAgeOfEmployee();
        partitionByEvenOrOdd();
        countOccurrences();
        groupingByBasedDepartment();
        findFirstGreaterThanTen();
        findtheLongestString();

        findEmployeeWhoseSalaryGrter50000();
        top2HighestSalariedEmployee();
        sortBySalaryThenByName();
        countEmployeeEachDepartment();
        averageSalaryPerDepartment();
        highestSalariedEmployeeEachDepartment();
        commaSeparatedEmployeeName();

        findCommonElement();
        convertIntoSingleList();
        findError();
        printEmployeeVeryFast();
        combinedSalaryOfEmployeesVeryFast();
        groupbyStringLength();

    }
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
}

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
