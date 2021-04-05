package pwr.ztw.books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.ztw.books.author.Author;
import pwr.ztw.books.author.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        String authorName = book.getAuthor().getName();
        String authorSurname = book.getAuthor().getSurname();
        Long authorId = book.getAuthor().getId();

        Optional<Author> author = null;
        if (authorId != 0) {
            author = authorService.getAuthorById(authorId);
        } else if (authorName != null && authorSurname != null) {
            author = authorService.getAuthorByNameAndSurname(authorName, authorSurname);
        }

        if (author.isPresent()) {
            book.setAuthor(author.get());
        } else {
            Author newAuthor = new Author(authorName, authorSurname);
            book.setAuthor(newAuthor);
        }
        bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void updateBook(Long id, Book book) {
        Optional<Book> bookToUpdate = bookRepository.findById(id);
        if (book.getAuthor() != null && bookToUpdate.isPresent()) {
            Optional<Author> author = authorService.getAuthorById(book.getAuthor().getId());
            author.ifPresent(a -> bookToUpdate.get().setAuthor(a));
            bookRepository.save(bookToUpdate.get());
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
