package pwr.ztw.books.book.user_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pwr.ztw.books.book.Book;

import java.security.Principal;
import java.util.List;

@RestController
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping(path = "/rent/{id}")
    public Book rentBookById(@PathVariable("id") Long id, Principal user) {
        return rentalService.rentBook(id, user)
                .orElse(null);
    }

    @GetMapping(path = "/return/{id}")
    public void returnBookById(@PathVariable("id") Long id, Principal user) {
        rentalService.returnBook(id, user);
    }

    @GetMapping(path = "/rent")
    public List<Book> getRentedBooks(Principal user) {
        return rentalService.getRented(user);
    }

}
