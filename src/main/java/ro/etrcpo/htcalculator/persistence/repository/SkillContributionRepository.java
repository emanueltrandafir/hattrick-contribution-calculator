package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;

import java.util.List;

@Repository
public interface SkillContributionRepository extends JpaRepository<SkillContribution, Long> {

    @Query(nativeQuery = true, value = "select * from skill_contributions where position_id = :positionId "
    		+ " and nvl(central_or_side_flag, nvl(:centralOrSideFlag, '0')) = nvl(:centralOrSideFlag, '0')")
    public List<SkillContribution> findByPosition(@Param("positionId") Long positionId, @Param("centralOrSideFlag") String centralOrSideFlag );
    
}