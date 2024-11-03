package cs544a;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Lazy
@Component
public class Third extends First {
    @Value("Random")
    private String text;

    @PostConstruct
    public void start() {
        System.out.println("Third starting - text: " + text);
    }

    @PreDestroy
    public void end() {
        System.out.println("Third exiting - text: " + text);
    }
}
