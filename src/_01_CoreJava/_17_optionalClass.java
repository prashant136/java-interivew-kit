package _01_CoreJava;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/// Optional is special class in Java that may or may not contain a value.
///     to overcome NullPointerException in our code.

class People {
    private int id;
    private String name;

    public People(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
class PeopleService {
    List<People> peopleList = Arrays.asList(
            new People(1, "Rahul"),
            new People(2, "Mimpi"),
            new People(3, "Kali"),
            new People(4, "Sameer")
    );

    public People getPeopleById(int id) {
        for (People people : peopleList) {
            if(people.getId() == id)
                return people;
        }
        return null;    // People is not found.
    }

    public Optional<People> getPeopleByName(String name) {
        for (People people : peopleList) {
            if(people.getName().equals(name))
                return Optional.of(people);
        }
        return Optional.empty();
    }

}
class PeopleMain {
    void main() {
        PeopleService service1 = new PeopleService();
        People people = service1.getPeopleById(101); // id = 4 doesn't exists
        // System.out.println(manushya.getName());     // NullPointerException
        if(people != null) {
            System.out.println(people.getName());
        }

        // we need to manually check the null, what if we forget to add null check.
        // it is necessary to add null pointer check to avoid null pointer exception.
        // to overcome this issue Optional class is introduced.
        // --------- with optional class ----------
        Optional<People> optionalPeople = service1.getPeopleByName("Kali");
        if(optionalPeople.isPresent()) {
            System.out.println(optionalPeople.get().getName());
        }

        optionalPeople.ifPresent(user -> System.out.println(user.getName()));

        // value nhi mila toh default value dega.
        People p1 = optionalPeople.orElse(new People(-1, "Default User"));
        System.out.println(p1.getName());

        // value nhi milega toh default value generate karega.
        People p2 = optionalPeople.orElseGet(() -> new People(-2, "xyz user"));
        System.out.println(p2.getName());

        People p3 = optionalPeople.orElseThrow(() -> new RuntimeException("user not found"));
        System.out.println(p3.getName());

        /// ------------
        People p4 = null;
//        Optional.of(p4);      // NPE
        Optional<People> p5 =  Optional.ofNullable(p4);
        System.out.println(p5.isPresent());     // return Optional.empty()


    }
}


/// --------------- Optional methods ----------------
/// Optional.of() - get an instance of this Optional class. throw NullPointerException if value is null.
/// Optional.empty() - method returns an empty instance of this Optional class.
/// Optional.isPresent() - check if a value is present in the Optional object. return type boolean (true/false)
/// Optional.get() - used to get the value of this Optional instance. If there is no value -> throws NullPointerException
/// Optional.ifPresent() - which executes a given action only if value is present.
/// Optional.orElse() - return value if present, otherwise return default value.
/// Optional.orElseGet() - return value if present, otherwise calls a supplier to generate the default value.
/// Optional.orElseThrow() - return value if present, otherwise throws an exception.
/// Optional.ofNullable() - same Optional.of() but if value is null, it returns Optional.empty(), doesn't throw NPE unlike 'of'.