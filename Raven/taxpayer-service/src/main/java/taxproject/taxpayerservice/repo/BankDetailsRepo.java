package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.BankDetails;
@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails,Integer> {
}
