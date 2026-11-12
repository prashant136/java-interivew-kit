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


/// ------------------- Controller and RestController ------------------
// [Don't say this at interview] Misconception: - @RestController is used for json response and @Controller is used for HTML or JSP response.

// @RestController :-
// - It return data directly in response body.
// - No need of ViewResolver
// - It is combination of @Controller + @ResponseBody

// example -
//@RestController
//public class NameController {
//    @GetMapping
//    public String getName() {
//        return "Rahul";   // postman me 'localhost/name' hit -> "Rahul" return hoga.
//    }
//}





// @Controller -
// - It return view name. (view could be html, json, jsp)
// - It use ViewResolver to resolve a template (html or jsp) from view name.
// - It explicitly use @ResponseBody to bypass ViewResolver and send data directly.

// [definition] ViewResolver - controller return view name, ViewResolver is interface that maps view names to actual view file.


// example -
//@Controller
//public class NameController {
//    @GetMapping
//    public String getName() {
//        return "Rahul";  ❌ error
//    }
//}
// postman me 'localhost/name' hit -> error 404 (not found). it considers Rahul as view not data. but humare pass koi 'Rahul' naam ka template nhi h like Rahul.html pr Rahul.jsp
// fix - means resources/templates/ ke andar hum Rahul.html banate hai aur pom.xml me <spring-boot-starter-thymeleaf> dependency dalte h.



//@Controller
//@ResponseBody
//public class NameController {
//    @GetMapping
//    public String getName() {
//        return "Rahul";  //  Yha Rahul as view nhi data h, it works similar to @RestController here.
//    }
//}
