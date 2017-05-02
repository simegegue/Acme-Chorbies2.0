
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.userAccount.id=?1")
	Manager findByUserAccountId(int userAccountId);

	//Dashboard
	@Query("select m from Manager m order by m.events.size DESC")
	Collection<Manager> managersByEvents();

	@Query("select m,m.feeAmount from Manager m")
	List<Object[]> managerFeeAmount();

}
