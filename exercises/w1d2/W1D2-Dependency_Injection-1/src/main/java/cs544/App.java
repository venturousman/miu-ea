package cs544;

// import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // System.out.println("FIXME: Empty Project");
        // var context = new AnnotationConfigApplicationContext(Config.class);
        var context = new ClassPathXmlApplicationContext("springconfig.xml");
        Greeting greeting = context.getBean("greeting", Greeting.class);
        greeting.sayHello();
        context.close();
    }
}
