package pwr.ztw.books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.ztw.books.author.Author;
import pwr.ztw.books.author.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
        author.ifPresent(book::setAuthor);
        bookRepository.saveAndFlush(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void updateBook(Long id, Book book) {
        Optional<Book> bookToUpdate = bookRepository.findById(id);
        if (book.getAuthor() != null && bookToUpdate.isPresent()) {
            Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
            author.ifPresent(a -> bookToUpdate.get().setAuthor(a));
            bookRepository.saveAndFlush(bookToUpdate.get());
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
