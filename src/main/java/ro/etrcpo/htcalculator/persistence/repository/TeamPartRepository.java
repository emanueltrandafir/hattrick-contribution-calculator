package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;

@Repository
public interface TeamPartRepository extends JpaRepository<TeamPart, Long> {

}