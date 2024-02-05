package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.ContactDetails;
@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails,Integer> {
}
