package ro.etrcpo.htcalculator.mapper;

import org.springframework.stereotype.Component;
import ro.etrcpo.htcalculator.dto.TeamPartDto;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamPartMapper {

    public List<TeamPartDto> teamPartToTeamPartDto(List<TeamPart> teamPartList) {
        List<TeamPartDto> teamPartDtoList = new ArrayList<>();
        for (TeamPart teamPart : teamPartList) {
            TeamPartDto teamPartDto = new TeamPartDto(teamPart.getId(), teamPart.getName());
            teamPartDtoList.add(teamPartDto);
        }
        return teamPartDtoList;
    }

}


