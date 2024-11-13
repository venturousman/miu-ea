package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "bookList";
    }

    @PostMapping("/books")
    public String add(@Valid Book book, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println("@@@ Error: " + result.getAllErrors());
            attr.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
            attr.addFlashAttribute("book", book);
            attr.addFlashAttribute("msg", "Add");
            // return "bookDetail";
            return "redirect:/books/add";
        } else {
            bookService.add(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/books/add")
    public String viewAdd(Model model) {
        if (!model.containsAttribute("book")) {
            model.addAttribute("book", new Book());
        }
        model.addAttribute("msg", "Add");
        return "bookDetail";
    }

    @GetMapping("/books/{id}")
    public String get(@PathVariable int id, Model model) {
        // model.addAttribute("book", bookService.get(id));
        if (!model.containsAttribute("book")) {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
        }
        model.addAttribute("msg", "Update");
        return "bookDetail";
    }

    @PostMapping("/books/{id}")
    public String update(@Valid Book book, BindingResult result, RedirectAttributes attr, @PathVariable int id) {
        if (result.hasErrors()) {
            System.out.println("@@@ Update error: " + result.getAllErrors());
            attr.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
            attr.addFlashAttribute("book", book);
            attr.addFlashAttribute("msg", "Update");
            // return "bookDetail";
            return "redirect:/books/" + id;
        } else {
            bookService.update(book); // book.id already set by binding
            return "redirect:/books";
        }
    }

    @PostMapping(value = "/books/delete")
    public String delete(@RequestParam("id") int bookId) {
        bookService.delete(bookId);
        return "redirect:/books";
    }

}
