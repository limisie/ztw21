package pwr.ztw.books.author;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakeAuthorDataAccessService implements AuthorDAO{

    private static List<Author> authors = new ArrayList<>();

    @Override
    public int insertAuthor(Author author) {
        authors.add(new Author(author.getName(), author.getSurname()));
        return 1;
    }

    @Override
    public List<Author> selectAllAuthors() {
        return authors;
    }

    @Override
    public Optional<Author> selectAuthorById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleterAuthorById(Long id) {
        Optional<Author> author = selectAuthorById(id);
        if (author.isPresent()) {
            authors.remove(author.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateAuthorById(Long id, Author author) {
        return selectAuthorById(id)
                .map(a -> {
                    int indexOfAuthorToUpdate = authors.indexOf(a);
                    if (indexOfAuthorToUpdate >= 0) {
                        authors.set(indexOfAuthorToUpdate, new Author(author.getName(), author.getSurname()));
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .orElse(0);
    }
}
