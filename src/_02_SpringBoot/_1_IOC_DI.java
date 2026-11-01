package _02_SpringBoot;

public class _1_IOC_DI {
}

/**
 * [definition] IOC - Inversion of control means that spring can create objects in the form of beans.
 * There is no need manually create object new keyword. It creates beans in IOC container.
 * then these beans can inject into field of a class using @Autowired annotation.
 *
 * /// using this IOC container and beans, we reuse the object (we don't need to create object again and again)
 * /// we need to tell spring boot that we need object of these classes aur wo unke object IOC me bnake rakh dega.
 * /// we can use those object many times. hum annotation ka use krte hai to tell spring ki es class ka bean bna do.
 *
 * [definition] DI - Dependency Injection  is a design pattern where an object receives its required dependencies from an external source instead af creating them itself, promoting loose coupling
 *
 * [definition] Autowiring - is a feature of the Spring that automatically injects dependent objects into a bean, it reduces boilerplate code and configuration.
 * It helps make applications loosely coupled, cleaner, and easier to maintain.
 *
 * Difference between spring and spring boot ??
 * [definition]
 *
 */

