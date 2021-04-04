package pwr.ztw.books.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "author")
@Table(name = "author")
public class Author {

    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @Id
    @Column(name = "author_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )

    private final Long id = 1L;
    @NotEmpty
    private final String name;
    @NotEmpty
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
