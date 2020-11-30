package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;

@Repository
public interface SkillContributionRepository extends JpaRepository<SkillContribution, Long> {

}