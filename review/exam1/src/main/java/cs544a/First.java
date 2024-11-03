package cs544a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class First {
    private Second second;

    @Autowired
    public void setSecond(Second second) {
        System.out.println("Setting second - it has text: " + second.getText());
        this.second = second;
    }

    public Second getSecond() {
        return second;
    }
}
