package cs544a;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("Testing Spring Startup");
        Third t = context.getBean(Third.class);
        System.out.println("In main: " + t.getSecond().getText());
        context.close();

        // First is an abstract class
        // Third is lazy , inherit from First
    }
}