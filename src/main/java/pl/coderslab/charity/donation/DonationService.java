package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.User;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int getSumOfDonatedBags() {
        try {
            return donationRepository.getOverallQuantity();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public int getNumberOfDonations() {
        return donationRepository.getNumberOfDonations();
    }

    public int getNumberOfDonationsByUser(User user) {
        return donationRepository.getNumberOfDonationsByUser(user);
    }

    public int getSumOfDonatedBagsByUser(User user) {
        try {
            return donationRepository.getOverallQuantityByUser(user);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void createDonation(Donation donation) {
        donationRepository.save(donation);
    }
}
