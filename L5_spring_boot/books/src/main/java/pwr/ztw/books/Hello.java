package pwr.ztw.books;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/")
    public String run() {
        return "Hello ! I'm, running";
    }
}