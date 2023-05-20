package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.DonationService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final DonationService donationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService,
                          RoleService roleService,
                          DonationService donationService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.donationService = donationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        int numOfDonations = donationService.getNumberOfDonationsByUser(user);
        int sumOfBags = donationService.getSumOfDonatedBagsByUser(user);
        model.addAttribute("numOfDonations", numOfDonations);
        model.addAttribute("sumOfBags", sumOfBags);
        model.addAttribute("userName", user.getFirstName());
        return "index";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model,
                              @AuthenticationPrincipal
                              CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/profile/edit")
    public String editUserDataForm(Model model,
                                   @AuthenticationPrincipal
                                   CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    @PostMapping("/user/profile/edit")
    public String editUserData(User user,
                               @AuthenticationPrincipal
                               CurrentUser currentUser) {
        User userToUpdate = currentUser.getUser();
        user.setId(userToUpdate.getId());
        user.setActive(userToUpdate.isActive());
        user.setPassword(userToUpdate.getPassword());
        user.setRoles(userToUpdate.getRoles());
        userService.updateUser(user);
        currentUser.setUser(user);
        return "redirect:/user/profile";
    }

    @GetMapping("/user/password/change")
    public String changePasswordForm() {
        return "user/password-change";
    }

    @PostMapping("/user/password/change")
    public String changePassword(@RequestParam String newPassword,
                                 @RequestParam String oldPassword,
                                 @AuthenticationPrincipal
                                 CurrentUser currentUser,
                                 Model model) {
        User user = currentUser.getUser();
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("errorMessage", "Podałeś nieprawidłowe hasło");
            return "user/password-change";
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userService.updateUser(user);
        return "redirect:/user/profile";
    }
}
