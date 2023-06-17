package pl.coderslab.charity.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    private final InstitutionService institutionService;
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(InstitutionService institutionService,
                           AdminService adminService,
                           UserService userService) {
        this.institutionService = institutionService;
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("")
    public String adminDashboard(@AuthenticationPrincipal CurrentUser currentUser,
                                 Model model) {
        User user = currentUser.getUser();
        model.addAttribute("currentUser", user);
        return "admin/dashboard";
    }

    @GetMapping("/institution/list")
    public String institutionList(Model model) {
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "admin/institution/list";
    }

    @GetMapping("/institution/add")
    public String addInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution/add";
    }

    @PostMapping("/institution/add")
    public String addInstitution(Institution institution) {
        institutionService.createInstitution(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/{id}/edit")
    public String editInstitutionForm(@PathVariable Long id, Model model) {
        Institution institution = institutionService.getInstitutionById(id);
        model.addAttribute("institution", institution);
        return "admin/institution/edit";
    }

    @PostMapping("/institution/{id}/edit")
    public String editInstitution(Institution institution) {
        institutionService.updateInstitution(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/{id}/delete")
    public String deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitutionById(id);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/administrator/list")
    public String adminList(Model model) {
        List<User> admins = adminService.getAllAdmins();
        model.addAttribute("administrators", admins);
        return "admin/administrator/list";
    }

    @GetMapping("/administrator/{id}/edit")
    public String editAdminForm(@PathVariable Long id, Model model) {
        User admin = userService.getUserById(id);
        model.addAttribute("admin", admin);
        return "admin/administrator/edit";
    }

    @PostMapping("/administrator/{id}/edit")
    public String editAdmin(User admin) {
        adminService.updateAdmin(admin);
        return "redirect:/admin/administrator/list";
    }

    @GetMapping("/administrator/user-list")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/administrator/user-list";
    }

    @GetMapping("/administrator/{id}/delete")
    public String deleteAdminRights(@PathVariable Long id) {
        userService.deleteAdminRights(id);
        return "redirect:/admin/administrator/user-list";
    }

    @GetMapping("/administrator/{id}/add")
    public String addAdminRights(@PathVariable Long id) {
        userService.addAdminRights(id);
        return "redirect:/admin/administrator/user-list";
    }

    @GetMapping("/user/list")
    public String listOfUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/user/list";
    }

    @GetMapping("/user/{userId}/edit")
    public String editUserForm(Model model, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/user/{userId}/edit")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/{userId}/toggle-activity")
    public String toggleUserActivity(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        user.setActive(!user.isActive());
        userService.updateUser(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("user/{userId}/delete")
    public String deleteUser(@PathVariable Long userId,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             Model model) {
        User user = currentUser.getUser();
        if (user.getId().equals(userId)) {
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("users", allUsers);
            model.addAttribute("actionDenied", "Nie możesz usunąć samego/samej siebie!");
            return "admin/user/list";
        }
        userService.deleteUser(userId);
        return "redirect:/admin/user/list";
    }
}
