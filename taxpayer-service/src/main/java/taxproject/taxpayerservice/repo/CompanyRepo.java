package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.Company;
@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
