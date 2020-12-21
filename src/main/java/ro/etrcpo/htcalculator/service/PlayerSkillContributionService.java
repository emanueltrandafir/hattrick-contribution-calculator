package ro.etrcpo.htcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.etrcpo.htcalculator.common.PositionSide;
import ro.etrcpo.htcalculator.dto.PlayerPositionDto;
import ro.etrcpo.htcalculator.dto.PositionDto;
import ro.etrcpo.htcalculator.dto.TeamPartDto;
import ro.etrcpo.htcalculator.mapper.PlayerMapper;
import ro.etrcpo.htcalculator.mapper.PositionMapper;
import ro.etrcpo.htcalculator.mapper.TeamPartMapper;
import ro.etrcpo.htcalculator.persistence.model.*;
import ro.etrcpo.htcalculator.persistence.repository.PositionRepository;
import ro.etrcpo.htcalculator.persistence.repository.SkillContributionRepository;
import ro.etrcpo.htcalculator.persistence.repository.TeamPartRepository;

import java.util.*;

@Service
public class PlayerSkillContributionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    TeamPartRepository teamPartRepository;

    @Autowired
    TeamPartMapper teamPartMapper;

    @Autowired
    SkillContributionRepository skillContributionRepository;

    @Autowired
    PlayerMapper playerMapper;

    public List<TeamPartDto> getTeamParts() {
        return teamPartMapper.teamPartToTeamPartDto(teamPartRepository.findAll());
    }

    public Map<TeamPartDto, Double> getCalculatedContributions(PlayerPositionDto playerPositionDto) {
        PositionDto positionDto = playerPositionDto.getPositionDto();
        Position position = positionMapper.positionDtoToPosition(positionDto);
        String centralOrSideFlag = (positionDto.getPositionSide() == PositionSide.Central) ? "C" : "S";

        Player player = playerMapper.playerDtoToPlayer(playerPositionDto.getPlayerDto());

        List<SkillContribution> skillContributions = skillContributionRepository.findByPosition(position.getId(), centralOrSideFlag);

        Map<TeamPart, Double> calculatedContributions = new HashMap<>();
        for (SkillContribution skillContribution : skillContributions) {
            Double calculatedContribution = 0d;

            for (PlayerAbility playerAbility : player.getPlayerAbilities()) {
                try {
                    if (playerAbility.getSkill().equals(skillContribution.getSkill())) {
                        calculatedContribution = playerAbility.getAbilityValue() * skillContribution.getPercentValue() / 100;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // team part collection that will be derived from skillContribution.getTeamPart()
            List<TeamPart> teamParts = new ArrayList<>();
            Boolean isDerivedTeamPart = Boolean.FALSE;

            // get child team parts (Left & Right) if position side = Central
            // get child team part (Left/Right) if position side = Left/Right
            List<TeamPart> childTeamParts = teamPartRepository.findChildTeamParts(skillContribution.getTeamPart().getId(), positionDto.getPositionSide().getValue());
            if (Objects.nonNull(childTeamParts) && !childTeamParts.isEmpty()) {
                isDerivedTeamPart = Boolean.TRUE;
                for (TeamPart childTeamPart : childTeamParts) {
                    teamParts.add(childTeamPart);
                }
            }

            // get opposite side team part (Left/Right) if position side = Right/Left
            TeamPart oppositeTeamPart = teamPartRepository.findOppositeTeamPart(skillContribution.getTeamPart().getId(), positionDto.getPositionSide().getValue());
            if (Objects.nonNull(oppositeTeamPart)) {
                isDerivedTeamPart = Boolean.TRUE;
                teamParts.add(oppositeTeamPart);
            }

            if (isDerivedTeamPart.equals(Boolean.FALSE)) {
                // if there is no child team part or no opposite team part, populate team part collection with parent team part
                teamParts.add(skillContribution.getTeamPart());
            }

            for (TeamPart teamPart : teamParts) {
                if (calculatedContributions.containsKey(teamPart)) {
                    calculatedContributions.put(teamPart, calculatedContributions.get(teamPart) + calculatedContribution);
                } else {
                    calculatedContributions.put(teamPart, calculatedContribution);
                }
            }
        }

        Map<TeamPartDto, Double> calculatedContributionsDto = new HashMap<>();
        for (TeamPart teamPart : calculatedContributions.keySet()) {
            TeamPartDto teamPartDto = new TeamPartDto(teamPart.getCode(), teamPart.getName());
            calculatedContributionsDto.put(teamPartDto, calculatedContributions.get(teamPart));
        }
        return calculatedContributionsDto;
    }
}
