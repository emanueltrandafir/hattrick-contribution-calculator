package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(nativeQuery = true, value = "select * from SKILLS where lower(name) = :name")
    public Skill findByName(@Param("name") String name);
}