package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Senders;

@Repository
public interface SendersRepository extends JpaRepository<Senders, Integer>{

}
