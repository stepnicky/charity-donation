package pl.coderslab.charity.user;

import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Controller
public class ExceptionHandlerController {
    @ExceptionHandler(LockedException.class)
    public String handleLockedException(LockedException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "user/login";
    }
}
