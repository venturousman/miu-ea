package cs544;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/books")
public class BookRestController {
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @PostMapping
    public RedirectView add(@RequestBody Book book) {
        bookService.add(book);
        return new RedirectView("/books");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Book book) {
        Book foundBook = bookService.get(id);
        if (foundBook == null) {
            return;
            // String message = "Book not found with ID: " + id;
            // return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Book foundBook = bookService.get(id);
        if (foundBook == null) {
            return;
            // String message = "Book not found with ID: " + id;
            // return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        bookService.delete(id);
    }
}
