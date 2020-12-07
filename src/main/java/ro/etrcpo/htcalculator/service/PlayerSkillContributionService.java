package ro.etrcpo.htcalculator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.mapper.PlayerMapper;
import ro.etrcpo.htcalculator.persistence.model.Ability;
import ro.etrcpo.htcalculator.persistence.model.CalculatedContribution;
import ro.etrcpo.htcalculator.persistence.model.Player;
import ro.etrcpo.htcalculator.persistence.model.PlayerAbility;
import ro.etrcpo.htcalculator.persistence.model.PlayerSkillContribution;
import ro.etrcpo.htcalculator.persistence.model.Position;
import ro.etrcpo.htcalculator.persistence.model.Skill;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;
import ro.etrcpo.htcalculator.persistence.repository.SkillContributionRepository;

@Service
public class PlayerSkillContributionService {

    @Autowired
    SkillContributionRepository skillContributionRepository;
    
    @Autowired
    PlayerMapper playerMapper;

    public List<SkillContribution> getSkillContributions() {
        return skillContributionRepository.findAll();
    }

    public List<SkillContribution> getSkillContributionsByPosition(Long positionId, String centralOrSideFlag) {
        return skillContributionRepository.findByPosition(positionId, centralOrSideFlag);
    }
    
    public Map<TeamPart, Double> getCalculatedContribution( PlayerDto playerDto, Long positionId, String centralOrSideFlag  ) {
    	
    	Player player = playerMapper.playerDtoToPlayer(playerDto);
    	List<SkillContribution> contributions = getSkillContributionsByPosition(positionId, centralOrSideFlag );
    	Map<TeamPart, Double> calculatedContribution = new HashMap<>();
    	
    	for( SkillContribution skillContribution : contributions ) {
    		
    		Double calculated = 0d;
    		
    		for( PlayerAbility ability : player.getPlayerAbilities() ) {
    			if(ability.getSkill().equals( skillContribution.getSkill() )){
    				calculated = ability.getAbilityValue() * skillContribution.getPercentValue() / 100;
    				break;
    			}
    		}
    		
    		TeamPart key = skillContribution.getTeamPart();
			if(calculatedContribution.containsKey(key)) {
    			calculatedContribution.put( key , calculatedContribution.get(key) + calculated);
    		
    		} else {
    			calculatedContribution.put(key, calculated);
    		}
    	}
    	 
    	return calculatedContribution;
    }
    

    public List<PlayerSkillContribution> getPlayerSkillContributions(Player player, Position position, String centralOrSideFlag) {
        return null;
    }
}
