package ro.etrcpo.htcalculator.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;

import java.util.List;

@Repository
public interface TeamPartRepository extends JpaRepository<TeamPart, Long> {

    @Query(nativeQuery = true, value = "select * from team_parts where parent_team_part_id = :teamPartId"
            + " and (:positionSide = 'C' or :positionSide = substring(code,1,1))")
    public List<TeamPart> findChildTeamParts(@Param("teamPartId") Long teamPartId, @Param("positionSide") String positionSide);

    @Query(nativeQuery = true, value = "select ostp.* from team_parts tp join team_parts ostp on ostp.id = tp.opposite_team_part_id" +
            " where tp.parent_team_part_id = :teamPartId and (:positionSide = substring(tp.code,3,1))")
    public TeamPart findOppositeTeamPart(@Param("teamPartId") Long teamPartId, @Param("positionSide") String positionSide);


}