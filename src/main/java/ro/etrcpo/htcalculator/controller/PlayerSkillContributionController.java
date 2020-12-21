package ro.etrcpo.htcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.etrcpo.htcalculator.dto.PlayerPositionDto;
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

    @GetMapping("/teamParts")
    public List<TeamPartDto> getTeamParts() {
        return playerSkillContributionService.getTeamParts();
    }

    @PostMapping("/calculatedContributions")
    public Map<TeamPartDto, Double> getCalculatedContributions(@RequestBody PlayerPositionDto playerPositionDto) {
        return playerSkillContributionService.getCalculatedContributions(playerPositionDto);
    }

}