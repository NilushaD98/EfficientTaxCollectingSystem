package taxproject.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.userservice.entity.UserDetails;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {
    UserDetails findByUsernameEquals(String username);

    UserDetails findByEmp_IdEquals(Long userID);
}
