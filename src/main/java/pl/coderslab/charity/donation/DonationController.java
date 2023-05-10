package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.InstitutionService;

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

    @GetMapping("/donate")
    public String donationForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("donation", new Donation());
        return "donation-form";
    }

    @PostMapping("/donate")
    public String processDonationForm(Donation donation) {
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String formConfirmation() {
        return "form-confirmation";
    }
}
