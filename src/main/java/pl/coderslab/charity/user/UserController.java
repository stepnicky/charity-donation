package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.email.EmailService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final DonationService donationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final PasswordTokenRepository passwordTokenRepository;

    public UserController(UserService userService,
                          RoleService roleService,
                          DonationService donationService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          EmailService emailService,
                          PasswordTokenRepository passwordTokenRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.donationService = donationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.passwordTokenRepository = passwordTokenRepository;
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
                                      Model model) throws MessagingException, GeneralSecurityException, IOException {
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
        emailService.sendEmail(user.getEmail(), "Charity-donation - kliknij w link potwierdzający", String.format("http://localhost:8080/confirm-registration/%s", user.getId()));
        String successMessage = "Potwierdź swoje dane klikając w link wysłany na Twojego e-maila";
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("user", new User());
        return "user/registration-form";
    }

    @GetMapping("/login")
    public String login(Model model) {
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

    @GetMapping("/confirm-registration/{userId}")
    public String confirmRegistration(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        user.setActive(true);
        userService.updateUser(user);
        model.addAttribute("accountActivated", "Użytkownik został zweryfikowany!");
        return "user/login";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm() {
        return "user/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, Model model) throws MessagingException, GeneralSecurityException, IOException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("errorMessage", "Brak użytkownika o podanym adresie email");
            return "user/reset-password";
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetToken(user, token);
        emailService.sendEmail(email, "Charity-donation - reset hasła (kliknij w link w wiadomości)", String.format("http://localhost:8080/change-password?token=%s", token));
        String successMessage = "Link do resetu hasła został wysłany na Twój adres email!";
        model.addAttribute("successMessage", successMessage);
        return "user/reset-password";
    }

    @GetMapping("/change-password")
    public String setNewPasswordForm(@RequestParam String token, Model model) {
        boolean validToken = userService.validatePasswordResetToken(token);
        if (!validToken) {
            model.addAttribute("errorMessage", "Link do resetu hasła wygasł...");
        }
        return "user/new-password";
    }

    @PostMapping("/change-password")
    public String setNewPassword(@RequestParam String token,
                                 @RequestParam String newPassword,
                                 @RequestParam String rePassword,
                                 Model model) {
        PasswordResetToken passwordResetToken = passwordTokenRepository.getByToken(token);
        User user = passwordResetToken.getUser();
        if (newPassword.equals(rePassword)) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.updateUser(user);
            model.addAttribute("successMessage", "Twoje hasło zostało zmienione!");
            return "user/login";
        } else {
            model.addAttribute("errorMessage", "Oba hasła muszą być jednakowe!");
            return "user/new-password";
        }
    }
}
