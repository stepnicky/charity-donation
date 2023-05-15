package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/registration-form";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid User user,
                                      BindingResult result,
                                      @RequestParam("password2") String password2,
                                      Model model) {
        User userExists = userService.getUserByEmail(user.getEmail());
        if (userExists != null) {
            result.rejectValue(
                    "email",
                    "user.exists"
            );
            return "user/registration-form";
        }
        if(!user.getPassword().equals(password2)) {
            result.rejectValue(
                    "password",
                    "password.non-matching"
            );
            return "user/registration-form";
        }
        List<Role> roles = new ArrayList<>();
        Role userRole = roleService.getRoleByRole("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
        userService.createUser(user);
        model.addAttribute("successMessage", "Użytkownik został pomyślnie zarejestrowany");
        model.addAttribute("user", new User());
        return "user/registration-form";
    }
}
