// java.lang -> import ki need nhi kyuki ye java ka default package hai.

//class InsufficientException extends RuntimeException {
//    InsufficientException(String message) {
//        super(message);
//    }
//}
//
//class BankBalance {
//    int bankBalance = 7000;
//
//    public void withdraw(int money) throws InsufficientException {
//        if(money > bankBalance) {
//            throw new InsufficientException("sdvbsdv");
//        }
//        bankBalance = bankBalance - money;
//    }
//}

//class Singleton {
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {}
//
//    public static Singleton getInstance() {
//        return instance;
//    }
//}

class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
            return instance;
    }
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Demo {
    void main() {
        Calculator obj = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        System.out.println(obj.calculate(10, 20));

        Calculator cal = (a, b) -> a + b;
        System.out.println(cal.calculate(10, 20));
    }
}
