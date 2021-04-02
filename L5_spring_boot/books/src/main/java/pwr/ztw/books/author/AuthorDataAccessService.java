package pwr.ztw.books.author;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class AuthorDataAccessService implements AuthorDAO {
    @Override
    public int insertAuthor(Author author) {
        return 0;
    }

    @Override
    public List<Author> selectAllAuthors() {
        return List.of(new Author("FROM POSTGRES DB", "FROM POSTGRES DB"));
    }

    @Override
    public Optional<Author> selectAuthorById(Long id) {
        return Optional.empty();
    }

    @Override
    public int deleterAuthorById(Long id) {
        return 0;
    }

    @Override
    public int updateAuthorById(Long id, Author author) {
        return 0;
    }
}
