package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.CompanyTypes;
@Repository
public interface CompanyTypeRepo extends JpaRepository<CompanyTypes,Integer> {
}
