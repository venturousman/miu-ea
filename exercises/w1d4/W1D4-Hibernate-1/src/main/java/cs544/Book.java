package cs544;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String isbn;
    private String author;
    private double price;
    private Date publish_date;

    public Book() {
    }

    public Book(String title, String isbn, String author, double price, Date publish_date) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
        this.publish_date = publish_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getISBN() {
        return isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPublishDate(Date publish_date) {
        this.publish_date = publish_date;
    }

    public Date getPublishDate() {
        return publish_date;
    }

}
