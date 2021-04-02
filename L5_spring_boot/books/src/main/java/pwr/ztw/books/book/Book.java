package pwr.ztw.books.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import pwr.ztw.books.author.Author;
import javax.persistence.*;

@Entity(name = "book")
@Table(name = "book")
public class Book {

    @Id
    @Column(
            name = "book_id",
            updatable = false
    )
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )

    private final Long id = 1L;
    private final String title;

    @ManyToOne(targetEntity=Author.class,
            cascade={CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "author_id",
                nullable = false,
                referencedColumnName = "author_id",
                foreignKey = @ForeignKey(name = "author_book_fk"))
    private Author author;
    private final int pages;

    public Book(@JsonProperty("title") String title,
                @JsonProperty("author") Author author,
                @JsonProperty("pages") int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    protected Book() {
        this.title = null;
        this.pages = 0;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", pages=" + pages +
                '}';
    }
}
