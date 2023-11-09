package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.GroupCompany;
@Repository
public interface GroupCompanyRepo extends JpaRepository<GroupCompany,Integer> {
    GroupCompany findGroupCompanyByGroupCompanyRegistrationNoEquals(String groupCompanyRegistrationNo);
}
