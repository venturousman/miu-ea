package cs544;

// import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// @DiscriminatorValue("book")
public class Book extends Product {
    private String title;

    public Book() {
        super();
    }

    public Book(String name, String description, double price, String title) {
        super(name, description, price);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
