package pwr.ztw.books.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.ztw.books.user.User;
import pwr.ztw.books.user.UserRole;
import pwr.ztw.books.user.UserService;

@Service
public class RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void register(RegistrationRequest request) {
        userService.signUpUser(
                new User(request.getUsername(),
                        request.getPassword(),
                        UserRole.USER)
        );
    }
}
