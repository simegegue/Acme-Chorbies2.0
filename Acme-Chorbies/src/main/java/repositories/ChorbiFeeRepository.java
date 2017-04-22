package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ChorbiFee;

@Repository
public interface ChorbiFeeRepository extends JpaRepository<ChorbiFee, Integer>{

}
