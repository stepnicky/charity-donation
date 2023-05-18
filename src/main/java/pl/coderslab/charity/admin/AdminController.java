package pl.coderslab.charity.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    private final InstitutionService institutionService;

    public AdminController(InstitutionService institutionService) {
        this.institutionService = institutionService;
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
}
