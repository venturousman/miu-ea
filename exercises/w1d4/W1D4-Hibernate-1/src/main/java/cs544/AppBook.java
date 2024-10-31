package cs544;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AppBook {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        emf = Persistence.createEntityManagerFactory("cs544");

        // 1 ==================================
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of Book and set values in it
        Book book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "978-0132350884",
                "Robert C. Martin", 35, dateFormat.parse("2008-08-01"));
        // save the book
        em.persist(book1);

        // Create new instance of Book and set values in it
        Book book2 = new Book("The Pragmatic Programmer: Your Journey to Mastery", "978-0135957059",
                "Andrew Hunt and David Thomas", 40, dateFormat.parse("2019-09-13"));
        // save the book
        em.persist(book2);

        // Create new instance of Book and set values in it
        Book book3 = new Book("Code: The Hidden Language of Computer Hardware and Software", "978-0735611313",
                "Charles Petzold", 25, dateFormat.parse("2000-10-11"));
        // save the book
        em.persist(book3);

        em.getTransaction().commit();
        em.close();

        // 2 ==================================
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all books
        TypedQuery<Book> query = em.createQuery("from Book", Book.class);
        List<Book> bookList = query.getResultList();
        for (Book book : bookList) {
            System.out.println("title= " + book.getTitle()
                    + ", isbn= " + book.getISBN()
                    + ", author= " + book.getAuthor()
                    + ", price= " + book.getPrice()
                    + ", publish_date= " + book.getPublishDate());
        }
        em.getTransaction().commit();
        em.close();

        // 3 ==================================
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all books
        query = em.createQuery("from Book", Book.class);
        bookList = query.getResultList();

        // Change the title and price of one book
        Book updatingBook = bookList.get(0);
        updatingBook.setTitle(updatingBook.getTitle() + " (edited)");
        updatingBook.setPrice(updatingBook.getPrice() + 2);

        // Use em.remove() to delete a different book
        Book deletingBook = bookList.get(1);
        em.remove(deletingBook);

        em.getTransaction().commit();
        em.close();

        // 4 ==================================
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all books
        query = em.createQuery("from Book", Book.class);
        bookList = query.getResultList();
        for (Book book : bookList) {
            System.out.println("title= " + book.getTitle()
                    + ", isbn= " + book.getISBN()
                    + ", author= " + book.getAuthor()
                    + ", price= " + book.getPrice()
                    + ", publish_date= " + book.getPublishDate());
        }
        em.getTransaction().commit();
        em.close();
    }
}
