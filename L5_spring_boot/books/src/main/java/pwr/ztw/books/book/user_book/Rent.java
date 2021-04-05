package pwr.ztw.books.book.user_book;

import com.fasterxml.jackson.annotation.JsonFormat;
import pwr.ztw.books.book.Book;
import pwr.ztw.books.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "rent")
@Table(name = "rent")
public class Rent {

    @SequenceGenerator(
            name = "rent_sequence",
            sequenceName = "rent_sequence",
            allocationSize = 1
    )
    @Id
    @Column(
            name = "rent_id",
            updatable = false
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rent_sequence"
    )
    private final Long id = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "user_fk"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id",
            nullable = false,
            referencedColumnName = "book_id",
            foreignKey = @ForeignKey(name = "book_fk"))
    private Book book;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate rentedOn;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate returnedOn;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getRentedOn() {
        return rentedOn;
    }

    public void setRentedOn(LocalDate rentedOn) {
        this.rentedOn = rentedOn;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }
}
