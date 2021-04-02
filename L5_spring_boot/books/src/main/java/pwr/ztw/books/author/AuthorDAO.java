package pwr.ztw.books.author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    int insertAuthor(Author author);

    List<Author> selectAllAuthors();

    Optional<Author> selectAuthorById(Long id);

    int deleterAuthorById(Long id);

    int updateAuthorById(Long id, Author author);
}
