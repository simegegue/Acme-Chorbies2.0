
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("select e, datediff(e.moment,current_date)/(30) from Event e")
	List<Object[]> eventsInLessOneMonth();

	@Query("select e from Event e where e.manager.id=?1")
	Collection<Event> eventByManagerId(int id);

}
