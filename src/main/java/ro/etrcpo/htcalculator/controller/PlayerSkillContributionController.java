package ro.etrcpo.htcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;
import ro.etrcpo.htcalculator.persistence.model.TeamPart;
import ro.etrcpo.htcalculator.service.PlayerSkillContributionService;

import java.util.List;
import java.util.Map;

@RestController
class PlayerSkillContributionController {
	
    @Autowired
    private PlayerSkillContributionService playerSkillContributionService;

    @GetMapping("/isAlive")
    public String isAlive() {
        return "hattrick rating contribution is up and running!";
    }

    @GetMapping("/skillContributions")
    public List<SkillContribution> getSkillContributionsByPosition(@RequestParam Long positionId, @RequestParam String centralOrSideFlag ) {
        return playerSkillContributionService.getSkillContributionsByPosition(positionId, centralOrSideFlag);
    }

    @GetMapping("/calculatedContributions")
    public Map<TeamPart, Double> getCalculatedContributions(@RequestBody PlayerDto playerDto, @RequestParam Long positionId, @RequestParam String centralOrSideFlag ) {
        return playerSkillContributionService.getCalculatedContribution(playerDto, positionId, centralOrSideFlag);
    }
    
}