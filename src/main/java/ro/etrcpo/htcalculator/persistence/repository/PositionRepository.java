package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    public Position findByCode(String code);

}