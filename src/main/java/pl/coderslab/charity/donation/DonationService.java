package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;

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

    public void createDonation(Donation donation) {
        donationRepository.save(donation);
    }
}
