package pl.coderslab.charity.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService,
                          DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.getAllInstitutions();
        int sumOfBags = donationService.getSumOfDonatedBags();
        int numOfDonations = donationService.getNumberOfDonations();
        model.addAttribute("institutions", institutions);
        model.addAttribute("sumOfBags", sumOfBags);
        model.addAttribute("numOfDonations", numOfDonations);
        return "index";
    }
}
