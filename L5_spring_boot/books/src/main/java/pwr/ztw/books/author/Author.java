package pwr.ztw.books.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity(name = "author")
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "author_id")
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )

    private final Long id = 1L;
    private final String name;
    private final String surname;

    public Author(@JsonProperty("name") String name,
                  @JsonProperty("surname") String surname) {
        this.name = name;
        this.surname = surname;
    }

    protected Author() {
        this.name = null;
        this.surname = null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getId() {
        return id;
    }
}
