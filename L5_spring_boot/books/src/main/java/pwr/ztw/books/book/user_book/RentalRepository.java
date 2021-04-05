package pwr.ztw.books.book.user_book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository
        extends JpaRepository<Rent, Long> {

    Optional<Rent> findByUserUsernameAndBookId(String username, Long book_id);
    List<Rent> findByUserUsername(String username);

}
