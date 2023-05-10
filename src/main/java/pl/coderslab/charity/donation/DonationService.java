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
}
