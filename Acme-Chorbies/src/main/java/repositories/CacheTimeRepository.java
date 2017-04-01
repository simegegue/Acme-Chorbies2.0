package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CacheTime;

@Repository
public interface CacheTimeRepository extends JpaRepository<CacheTime, Integer>{

}
