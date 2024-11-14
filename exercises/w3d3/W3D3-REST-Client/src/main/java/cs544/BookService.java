package cs544;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService implements IBookService {

    @Autowired
    private RestTemplate restTemplate;
    private final String bookUrl = "http://localhost:8080/books/{id}";
    private final String pplUrl = "http://localhost:8080/books";

    @Override
    public List<Book> getAll() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                pplUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        return response.getBody();
    }

    @Override
    public void add(Book book) {
        // Ensure the book object is correctly populated
        book.setId(0); // or handle it appropriately

        URI uri = restTemplate.postForLocation(pplUrl, book);
//        System.out.println("@@@ uri= " + uri); // = http://localhost:8080/books
        if (uri == null) {
            return;
        }
//        Matcher m = Pattern.compile(".*/books/(\\d+)").matcher(uri.getPath());
//        m.matches();
//        System.out.println("@@@ matches= " + m.results());
        // return Long.parseLong(m.group(1));
    }

    @Override
    public Book get(int id) {
        return restTemplate.getForObject(bookUrl, Book.class, id);
    }

    @Override
    public void update(Book book) {
        restTemplate.put(bookUrl, book, book.getId());
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(bookUrl, id);
    }

}
