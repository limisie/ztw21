package pwr.ztw.books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("book")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return this.bookService.getBooks();
    }

    @GetMapping(path = "{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping(path = "{id}")
    public void updateBook(@PathVariable("id") Long id,
                           @RequestBody Book bookToUpdate) {
        bookService.updateBook(id, bookToUpdate);
    }
}

