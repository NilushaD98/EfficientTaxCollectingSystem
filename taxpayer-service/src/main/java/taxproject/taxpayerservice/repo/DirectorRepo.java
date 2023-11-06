package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.Director;

@Repository
public interface DirectorRepo extends JpaRepository<Director,Integer> {

    @Query(value = "SELECT * from director where NICOrPassportNo=?1",nativeQuery = true)
    Director findDirectorByNICOrPassportNoEquals(String nicOrPassportNo);
}
