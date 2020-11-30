package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;

import java.util.List;

@Repository
public interface SkillContributionRepository extends JpaRepository<SkillContribution, Long> {

    @Query(nativeQuery = true, value = "select * from skill_contributions where team_part_id = :teamPartId order by position_id, skill_id")
    public List<SkillContribution> findByTeamPart(@Param("teamPartId") Long teamPartId);


    @Query(nativeQuery = true, value = "select * from skill_contributions where position_id = :positionId order by team_part_id, skill_id")
    public List<SkillContribution> findByPosition(@Param("positionId") Long positionId);
}