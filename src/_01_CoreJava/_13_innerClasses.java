package _01_CoreJava;

public class _13_innerClasses {

}

/// 1. ----------------------------- Member inner class -----------------------------
/// inner class is associated with instance of outer class. inner behave as member of outer class.
/// ye directly outer class ke members ko access kr sakta including private variable and methods, without needing public getter and setter.
//class Car {
//    private String model;
//    private boolean isEngineOn;
//
//    public Car(String model) {
//        this.model = model;
//        this.isEngineOn = false;
//    }
//
//    class Engine {
//        void start() {
//            if(!isEngineOn) {
//                isEngineOn = true;
//                System.out.println(model + " engine started");
//            } else {
//                System.out.println(model + " engine is already on");
//            }
//        }
//
//        void stop() {
//            if(isEngineOn) {
//                isEngineOn = false;
//                System.out.println(model + " engine stopped");
//            } else {
//                System.out.println(model + " engine is already off");
//            }
//        }
//    }
//}
//class MemberInnerClassMain {
//    public static void main(String[] args) {
//        Car car = new Car("BMW series 2");
//        Car.Engine engine = car.new Engine();
//        engine.start();
//        engine.stop();
//    }
//}


/// ==== agar hum member inner class na banye aur dono classes ko alag rakhe toh ? Yes kr sakte hai isme humein Engine class me car ka object pass krna padega. gtter and setter ki need hogi engine ko.
//class Car {
//    private String model;
//    private boolean isEngineOn;
//
//    public Car(String model) {
//        this.model = model;
//        this.isEngineOn = false;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public boolean isEngineOn() {
//        return isEngineOn;
//    }
//
//    public void setEngineOn(boolean engineOn) {
//        isEngineOn = engineOn;
//    }
//}
//class Engine {
//    Car car;
//
//    public Engine(Car car) {
//        this.car = car;
//    }
//
//    void start() {
//        if(!car.isEngineOn()) {
//            car.setEngineOn(true);
//            System.out.println(car.getModel() + " engine started");
//        } else {
//            System.out.println(car.getModel() + " engine is already on");
//        }
//    }
//
//    void stop() {
//        if(car.isEngineOn()) {
//            car.setEngineOn(false);
//            System.out.println(car.getModel() + " engine stopped");
//        } else {
//            System.out.println(car.getModel() + " engine is already off");
//        }
//    }
//}
//class MemberInnerClassMain {
//    public static void main(String[] args) {
//        Car car = new Car("BMW series 2");
//        Engine engine = new Engine(car);
//        engine.start();
//        engine.stop();
//
//        Car car1 = new Car("Lamborghini");
//        Engine engine1 = new Engine(car1);
//        engine1.start();
//        engine1.stop();
//    }
//}


/// 2. -------------------------------- static nested class ----------------------------------
/// static inner class is associated with outer class rather than the instance of outer class.
class Computer {
    private String brand;
    private String model;
    private OperatingSystem os;

    /// static means kisi instance ka part nhi hai.
    /// Computer, OperatingSystem ka instance banega, but USB ka instance nhi banega.
    static class USB {
        private String type;

        public USB(String type) {
            this.type = type;
        }

        public void displayInfo() {
            System.out.println("USB Type: " + type);
        }
    }

    public Computer(String brand, String model, String os) {
        this.brand = brand;
        this.model = model;
        this.os = new OperatingSystem(os);
    }

    class OperatingSystem {
        private String osName;

        public OperatingSystem(String osName) {
            this.osName = osName;
        }

        public void displayName() {
            System.out.println("Computer Model: " + model + ", OS: " + osName);
        }
    }

    public OperatingSystem getOs() {
        return os;
    }

}
class StaticInnerClassMain {
    public static void main(String[] args) {
        Computer computer = new Computer("HP", "XEr234bgt00", "Windows 11");
        computer.getOs().displayName();

        /// intuation: -
        ///     Computer ki kini (hazaro copies) bhi copies bna lo. USB ek static class hai jo Computer ke instance se related nhi hoga.
        ///     agar USB ko static class nhi banate, toh USB ka bhi instance Computer ke instances ke sath bnata like OperatingSystem. jo memory efficient hoga.
        ///     logically group helper classes in one place. exmaple kal hum compter se related aur bhi staic classes bna sakte hai jaise pendrive, CD, printer static
        Computer.USB usb1 = new Computer.USB("TYPE-C");
        Computer.USB usb2 = new Computer.USB("TYPE-A");
        Computer.USB usb3 = new Computer.USB("thunderbolt");
    }
}


/// 3. ------------------------------- Anonymous inner class ------------------------------
interface Payment {
    void pay(double amount);
}
/// Payment class ka implementation
class CreditCardV0 implements Payment {

    private String cardNumber;

    public CreditCardV0(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Credit Card Payment: " + amount);
    }
}
class ShoppingCart {
    private double totalAmount;

    public ShoppingCart(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void processPayment(Payment paymentMethod) {
        paymentMethod.pay(totalAmount);
    }
}
class AnonymousMain {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart(200);

        /// ✅ logic :: yaha mujhe ek class ka object chahiye jo payment interface ko implement karta ho.
        /// ek CreditCard class jo Payment interface ko implement krta ho. uska object processPayment() me pass kr sakte hai.
        // CreditCard creditCard = new CreditCard("232547656");
        // shoppingCart.processPayment(creditCard);

        /// Alternate way - But iska ka alternate hai, jisme implementation class na bnake yahi ek on the fly implemantaion object pass kr dete hai.
        /// --> An anonymous class (inner class) declared without any class name, allowing you to define and instantiate a class at the exact same moment in a single statement.
        ///     one-time implementation of an interface.
        /// when to use anonymous class ?? jab hume koi interface implement krna without creating its separate implementation class, koi class extend krwani ho without creating its separate subclass.
        shoppingCart.processPayment(new Payment() {
            @Override
            public void pay(double amount) {
                System.out.println("Credit Card Payment: " + amount);
            }
        });

        shoppingCart.processPayment(new Payment() {
            @Override
            public void pay(double amount) {
                System.out.println("Payment: " + amount + " using Paypal");
            }
        });

        // java 8 ke baad se lambda expression bhi de sakte hai.
        shoppingCart.processPayment((double amount) -> {
                System.out.println("Payment: " + amount + " using UPI");
        });
    }
}


/// 4. ----------------------------- Local Inner class (method ke andar class) -----------------------------
class Hotel {
    private String name;
    private int totalRooms;
    private int reservedRooms;

    public Hotel(String name, int totalRooms, int reservedRooms){
        this.name = name;
        this.totalRooms = totalRooms;
        this.reservedRooms = reservedRooms;
    }

    public void reserveRoom(String guestName, int numOfRooms) {
        class ReservationValidator {
            boolean validate() {
                if(guestName == null || guestName.isBlank()) {
                    System.out.println("Guest name can't be empty !");
                    return false;
                }
                if(numOfRooms < 0) {
                    System.out.println("Number of rooms should be positive");
                    return false;
                }
                if(reservedRooms + numOfRooms > totalRooms) {
                    System.out.println("Not enough rooms available");
                    return false;
                }
                return true;
            }
        }

        ReservationValidator reservationValidator = new ReservationValidator();
        if(reservationValidator.validate()) {
            reservedRooms += numOfRooms;
            System.out.println("Reservation confirmed for: " + guestName + " for " + numOfRooms);
        } else {
            System.out.println("Reservation failed");
        }
    }
}
class LocalInnerClassMain {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Sunshine", 10, 5);
        hotel.reserveRoom("Akshit", 2);
        hotel.reserveRoom("Shivam", -2);
        hotel.reserveRoom("", 1);
    }
}

/// local inner class - logic ko encapsulate kr dia, class ka context define kr diya, tightly coupled hai.