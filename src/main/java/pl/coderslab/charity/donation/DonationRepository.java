package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.user.User;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) from Donation d")
    Integer getOverallQuantity();

    @Query("select count(d) from Donation d")
    Integer getNumberOfDonations();

    @Query("select sum(d.quantity) from Donation d where d.user = ?1")
    Integer getOverallQuantityByUser(User user);

    @Query("select count(d) from Donation d where d.user = ?1")
    Integer getNumberOfDonationsByUser(User user);
}
