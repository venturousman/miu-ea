package cs544;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    private IBookDao bookDao;

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public void add(Book book) {
        bookDao.add(book);
    }

    public Book get(int id) {
        return bookDao.get(id);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete(int id) {
        bookDao.delete(id);
    }
}
