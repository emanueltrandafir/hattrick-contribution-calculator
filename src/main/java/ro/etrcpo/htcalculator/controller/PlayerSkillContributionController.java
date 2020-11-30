package ro.etrcpo.htcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;
import ro.etrcpo.htcalculator.service.PlayerSkillContributionService;

import java.util.List;

@RestController
class PlayerSkillContributionController {
    @Autowired
    private PlayerSkillContributionService playerSkillContributionService;

    @GetMapping("/isAlive")
    public String isAlive() {
        return "hattrick rating contribution is up and running!";
    }

    @GetMapping("/skillContributions")
    public List<SkillContribution> getSkillContributions() {
        return playerSkillContributionService.getSkillContributions();
    }
}