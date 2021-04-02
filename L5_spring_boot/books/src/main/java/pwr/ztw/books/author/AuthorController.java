package pwr.ztw.books.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("author")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @GetMapping
    public List<Author> getAuthors() {
        return this.authorService.getAuthors();
    }

    @GetMapping(path = "{id}")
    public Author getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAuthorById(@PathVariable("id")Long id) {
        authorService.deleteAuthor(id);
    }

//    @PutMapping(path = "{id}")
//    public void updateAuthor(@PathVariable("id")Long id,
//                             @NonNull @RequestBody Author authorToUpdate) {
//        authorService.updateAuthor(id, authorToUpdate);
//    }
}
