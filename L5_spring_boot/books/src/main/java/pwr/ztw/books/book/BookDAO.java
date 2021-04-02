package pwr.ztw.books.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookDAO {

    int insertBook(Book book);

    List<Book> selectAllBooks();

    Optional<Book> selectBookById(Long id);

    int deleteBookById(Long id);

    int updateBookById(Long id, Book book);

    int insertBook(Book book, Long author_id);
}
