package cs544;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// public interface IBookDao {

//     public abstract List<Book> getAll();

//     public abstract void add(Book book);

//     public abstract Book get(int id);

//     public abstract void update(Book book);

//     public abstract void delete(int bookId);
// }

@Repository
public interface IBookDao extends JpaRepository<Book, Integer> {
}
