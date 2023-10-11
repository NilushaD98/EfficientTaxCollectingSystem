package taxproject.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.userservice.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
