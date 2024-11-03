package cs544;

// import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // var context = new AnnotationConfigApplicationContext(Config.class);
        // output: Hello World
        var context = new ClassPathXmlApplicationContext("springconfig.xml"); // output: Hello World from XML
        Greeting greeting = context.getBean("greeting", Greeting.class);
        greeting.sayHello();
        context.close();
    }
}
