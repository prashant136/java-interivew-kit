package _01_CoreJava;

public class _6_javaMain {

    // method overloading
    public static void main(String[] args1, String[] args2)  {

    }

    // method overloading - we can have two different methods with the same name with the same class but their argument will be different.
    public static void main(int[] args) {

    }

    public static void main(String[] args) {
        // initially args has nothing. we need to pass some input.
        for (int i = 0; i < args.length; i++) {
            System.out.println("input: " + args[i]);
        }
    }
}

/// why public - JVM can access main method outside of this program. private likhkar dekho jvn won't able to find it.
/// why static - main method can be triggered without making its object. If we remove static jvm will have to create an object of '_6_javaMain' -> then it will trigger the main method.

/// We know that our code starts from the main method. So before even starting the main method how can JVM create an object ?
/// answer - jvm can't create an object. that's way we need to declare static here.

/// When we run our Java allows us to give some inputs in that project. input stored in args
/// if we didn't pass any input initially
/// hum terminal me file ko compile krke argurment bhej sakte hai jisse ye chalega.
// complile - javac _6_javaMain.java,  run - java _6_javaMain Apple Banana Cherry (terminal me output print hoga)

/// we can overload main method but jvm will run only public static void main(String[] args) { ... } method.

/// we can have multiple main method in our project. we can have different classes and different have their own main methods. there is absolutely no problem in that.
/// we cna run all these classes individually.

/// we can complile any java class but we cna only run those java class which has main method (else it will show error)