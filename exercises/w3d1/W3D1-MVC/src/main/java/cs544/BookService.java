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
        // return bookDao.getAll();
        return bookDao.findAll();
    }

    public void add(Book book) {
        // bookDao.add(book);
        bookDao.save(book);
    }

    public Book get(int id) {
        // return bookDao.get(id);
        // return bookDao.findById(id).get();
        return bookDao.getById(id);
    }

    public void update(Book book) {
        // bookDao.update(book);
        bookDao.save(book);
    }

    public void delete(int id) {
        // bookDao.delete(id);
        bookDao.deleteById(id);
    }
}
