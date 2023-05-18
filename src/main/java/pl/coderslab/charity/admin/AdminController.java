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
        model.addAttribute("user", user);
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
}
