package taxproject.taxpayerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxproject.taxpayerservice.entity.Person;
@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {
}
