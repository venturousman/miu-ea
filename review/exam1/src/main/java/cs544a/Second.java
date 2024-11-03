package cs544a;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Second {
    @Value("Second")
    private String text;

    public String getText() {
        return text;
    }
}
