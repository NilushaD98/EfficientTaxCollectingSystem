package taxproject.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.userservice.entity.RemovedEmployee;

@Repository
public interface RemoveEmployeeRepo extends JpaRepository<RemovedEmployee,Long> {
}
