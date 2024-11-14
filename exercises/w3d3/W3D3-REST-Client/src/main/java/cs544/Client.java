package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Client implements CommandLineRunner {

    @Autowired
    private IBookService bookService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("======== Application started! ========");
        // make a call to whatever url you mapped getAll() on and print the result
        var books = bookService.getAll();
        for (var book : books) {
            System.out.println(book);
        }
//        System.out.println(books);

//        System.out.println("Before adding a new book");
        // make a call to the url that you mapped add() on (giving it a new book)
        bookService.add(
                new Book(
                        "Beginning Java 8 Fundamentals",
                        "978-0-306-40615-7",
                        "Kishori Sharan",
                        49)
        );
        System.out.println("After adding a new book");
        books = bookService.getAll();
        for (var book : books) {
            System.out.println(book);
        }

        // make a call to the url that you mapped update() on (giving it an updated
        // version)
        var updatingBook = books.get(books.size() - 1);
        updatingBook.setTitle("Beginning Java 8 Fundamentals (updated)");
        updatingBook.setAuthor("Kishori Sharan (updated)");
        updatingBook.setPrice(37.25);
        bookService.update(updatingBook);

        // make a call to the url that you mapped delete() on (deleting one of the
        // books)
        bookService.delete(1);

        // make a call to the url that you mapped getAll() on and print the result again
        System.out.println("After updating and deleting");
        books = bookService.getAll();
        for (var book : books) {
            System.out.println(book);
        }

        // make a call to the url that you mapped get() to check that you can get one
        // book
        System.out.println("After getting a book");
        var foundBook = bookService.get(2);
        if (foundBook != null) {
            System.out.println(foundBook);
        }
    }

}
