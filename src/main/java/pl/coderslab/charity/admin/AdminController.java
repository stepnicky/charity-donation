package pl.coderslab.charity.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    @GetMapping("")
    public String adminDashboard(@AuthenticationPrincipal CurrentUser currentUser,
                                 Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "admin/dashboard";
    }
}
