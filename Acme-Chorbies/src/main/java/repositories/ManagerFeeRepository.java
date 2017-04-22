package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ManagerFee;

@Repository
public interface ManagerFeeRepository extends JpaRepository<ManagerFee, Integer>{

}
