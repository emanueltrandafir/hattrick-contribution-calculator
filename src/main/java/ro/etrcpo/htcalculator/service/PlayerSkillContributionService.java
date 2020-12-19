package ro.etrcpo.htcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.dto.PositionDto;
import ro.etrcpo.htcalculator.dto.TeamPartDto;
import ro.etrcpo.htcalculator.mapper.PlayerMapper;
import ro.etrcpo.htcalculator.mapper.PositionMapper;
import ro.etrcpo.htcalculator.mapper.TeamPartMapper;
import ro.etrcpo.htcalculator.persistence.model.Player;
import ro.etrcpo.htcalculator.persistence.model.PlayerAbility;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;
import ro.etrcpo.htcalculator.persistence.repository.PositionRepository;
import ro.etrcpo.htcalculator.persistence.repository.SkillContributionRepository;
import ro.etrcpo.htcalculator.persistence.repository.TeamPartRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<PositionDto> getPositions() {
        return positionMapper.positionToPositionDto(positionRepository.findAll());
    }

    public List<TeamPartDto> getTeamParts() {
        return teamPartMapper.teamPartToTeamPartDto(teamPartRepository.findAll());
    }

    public Map<TeamPartDto, Double> getCalculatedContributions(PlayerDto playerDto, Long positionId, String centralOrSideFlag) {
        Player player = playerMapper.playerDtoToPlayer(playerDto);
        List<SkillContribution> skillContributions = skillContributionRepository.findByPosition(positionId, centralOrSideFlag);
        Map<TeamPart, Double> calculatedContributions = new HashMap<>();

        for (SkillContribution skillContribution : skillContributions) {
            Double calculatedContribution = 0d;

            for (PlayerAbility playerAbility : player.getPlayerAbilities()) {
            	try {
            		if (playerAbility.getSkill().equals(skillContribution.getSkill())) {
            			calculatedContribution = playerAbility.getAbilityValue() * skillContribution.getPercentValue() / 100;
            			break;
            		}
            	} catch(Exception e) {
            		System.out.println( e.getMessage() );
            	}
            }

            TeamPart teamPart = skillContribution.getTeamPart();
            if (calculatedContributions.containsKey(teamPart)) {
                calculatedContributions.put(teamPart, calculatedContributions.get(teamPart) + calculatedContribution);
            } else {
                calculatedContributions.put(teamPart, calculatedContribution);
            }
        }

        Map<TeamPartDto, Double> calculatedContributionsDto = new HashMap<>();
        for (TeamPart teamPart : calculatedContributions.keySet()) {
            TeamPartDto teamPartDto = new TeamPartDto(teamPart.getId(), teamPart.getName());
            calculatedContributionsDto.put(teamPartDto, calculatedContributions.get(teamPart));
        }
        return calculatedContributionsDto;
    }

}
