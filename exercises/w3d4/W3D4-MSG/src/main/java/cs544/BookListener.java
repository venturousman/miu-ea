package cs544;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "book")
public class BookListener {
    private final BookService bookService;

    @Autowired
    public BookListener(BookService bookService) {
        this.bookService = bookService;
    }

    // @RabbitHandler
    // public void receive(cs544.message.Book book) {
    // System.out.println("Received: " + book.toString());
    // try {
    // var id = book.getId();
    // // NOTE: convert cs544.message.Book (must be the same package in the sender)
    // // to cs544.Book entity
    // var entity = new Book(book.getTitle(), book.getISBN(), book.getAuthor(),
    // book.getPrice());

    // if (id == null) {
    // System.out.println("Book not found in database");
    // bookService.add(entity);
    // return;
    // }

    // var foundBook = bookService.get(id);
    // if (foundBook == null) {
    // System.out.println("Book not found in database");
    // bookService.add(entity);
    // System.out.println("Book added to database");
    // } else {
    // System.out.println("Book found in database");
    // entity.setId(id);
    // bookService.update(entity);
    // System.out.println("Book updated to database");
    // }
    // } catch (Exception e) {
    // System.out.println("Error: " + e.getMessage());
    // }
    // }

    @RabbitHandler
    public void receiveToDelete(Integer id) {
        System.out.println("Received: " + id);
        try {
            if (id == null) {
                System.out.println("Book not found in database");
                return;
            }

            var foundBook = bookService.get(id);
            if (foundBook == null) {
                System.out.println("Book not found in database");
            } else {
                bookService.delete(id);
                System.out.println("Book deleted from database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @RabbitHandler
    public void receive(cs544.message.Book book, @Header("operation") String operation) {
        System.out.println("Received: " + book.toString() + " operation: " + operation);
        try {
            // NOTE: convert cs544.message.Book (must be the same package in the sender)
            // to cs544.Book entity
            var entity = new Book(book.getTitle(), book.getISBN(), book.getAuthor(), book.getPrice());

            if (operation.equals("add")) {
                bookService.add(entity);
                System.out.println("Book added to database");
            } else if (operation.equals("update")) {
                var id = book.getId();
                if (id == null) {
                    System.out.println("Book not found in database");
                    return;
                }
                var foundBook = bookService.get(id);
                if (foundBook == null) {
                    System.out.println("Book not found in database");
                    return;
                }
                entity.setId(id);
                bookService.update(entity);
                System.out.println("Book updated to database");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
