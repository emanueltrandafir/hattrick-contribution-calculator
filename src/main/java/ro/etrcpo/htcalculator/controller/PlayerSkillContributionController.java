package ro.etrcpo.htcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.dto.PositionDto;
import ro.etrcpo.htcalculator.dto.TeamPartDto;
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

    @GetMapping("/positions")
    public List<PositionDto> getPositions() {
        return playerSkillContributionService.getPositions();
    }

    @GetMapping("/teamParts")
    public List<TeamPartDto> getTeamParts() {
        return playerSkillContributionService.getTeamParts();
    }

    @PostMapping("/calculatedContributions")
    public Map<TeamPartDto, Double> getCalculatedContributions(@RequestBody PlayerDto playerDto, @RequestParam Long positionId, @RequestParam String centralOrSideFlag) {
        return playerSkillContributionService.getCalculatedContributions(playerDto, positionId, centralOrSideFlag);
    }

}