package cs544.message;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String ISBN;
    private String author;
    private double price;

    public Book() {
    }

    public Book(String title, String ISBN, String author, double price) {
        super();
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + title + ", " + ISBN + ", " + author + ", " + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // int result = (id != null) ? id.hashCode() : 0;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + Double.hashCode(price);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Book other = (Book) obj;

        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;

        if (ISBN == null) {
            if (other.ISBN != null)
                return false;
        } else if (!ISBN.equals(other.ISBN))
            return false;

        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;

        if (price != other.price)
            return false;

        return true;
    }
}
