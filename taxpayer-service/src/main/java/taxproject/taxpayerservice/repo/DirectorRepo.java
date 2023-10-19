package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.Director;
@Repository
public interface DirectorRepo extends JpaRepository<Director,Integer> {
}
