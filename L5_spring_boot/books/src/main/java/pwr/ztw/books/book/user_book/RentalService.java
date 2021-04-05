package pwr.ztw.books.book.user_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.ztw.books.book.Book;
import pwr.ztw.books.book.BookService;
import pwr.ztw.books.user.User;
import pwr.ztw.books.user.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookService bookService, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public Optional<Book> rentBook(Long bookId, Principal logged_user) {
        Optional<Book> book = bookService.getBookById(bookId);

        if (book.isPresent()) {
            Book bookToRent  = book.get();
            Rent rent_log = new Rent();

            if (!getRented(logged_user).contains(bookToRent)) {
                User user = userService.getUserByUsername(logged_user.getName()).get();

                rent_log.setBook(bookToRent);
                rent_log.setUser(user);
                rent_log.setRentedOn(LocalDate.now());
                rentalRepository.save(rent_log);
            }
        }
        return book;
    }

    public void returnBook(Long id, Principal logged_user) {
        Optional<Rent> rent = rentalRepository.findByUserUsernameAndBookId(logged_user.getName(), id);
        if (rent.isPresent()) {
            Rent rent_log = rent.get();
            if (rent_log.getReturnedOn() == null) {
                rent_log.setReturnedOn(LocalDate.now());
                rentalRepository.save(rent_log);
            }
        }
    }

    public List<Book> getRented(Principal logged_user) {
        List<Rent> rented = rentalRepository.findByUserUsername(logged_user.getName());
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < rented.size(); i++) {
            if (rented.get(i).getReturnedOn() == null) {
                books.add(rented.get(i).getBook());
            }
        }
        return books;
    }
}
