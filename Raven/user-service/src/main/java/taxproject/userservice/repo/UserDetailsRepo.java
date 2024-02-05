package taxproject.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import taxproject.userservice.entity.UserDetails;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {
//    @Query(value = "select auth_detail_id,username,password,job_title,private_key from auth_details where username=?1 and wallet_address=?2 ",nativeQuery = true)
    UserDetails findByUsernameEqualsAndWalletAddressEquals(String username,String walletaddress);

    UserDetails findByEmpIdEquals(Long userID);
}
