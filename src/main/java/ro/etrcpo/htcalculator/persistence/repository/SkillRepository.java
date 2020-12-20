package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.Skill;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT s.* FROM SKILLS s JOIN LABELS l ON l.SKILL_ID = s.ID WHERE LOWER(l.label) = lower(:name)")
    public Skill findByName(@Param("name") String name);
}