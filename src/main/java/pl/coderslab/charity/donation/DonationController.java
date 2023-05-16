package pl.coderslab.charity.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;

import java.util.List;

@Controller
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(DonationService donationService,
                              CategoryService categoryService,
                              InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/user/donate")
    public String donationForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", new Donation());
        return "donation/donation-form";
    }

    @PostMapping("/user/donate")
    public String processDonationForm(@AuthenticationPrincipal CurrentUser currentUser,
                                      Donation donation) {
        donation.setUser(currentUser.getUser());
        donationService.createDonation(donation);
        return "redirect:/user/confirmation";
    }

    @GetMapping("/user/confirmation")
    public String formConfirmation() {
        return "donation/form-confirmation";
    }
}
