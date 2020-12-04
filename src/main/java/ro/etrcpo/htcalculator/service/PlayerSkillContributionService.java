package ro.etrcpo.htcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.etrcpo.htcalculator.persistence.model.Player;
import ro.etrcpo.htcalculator.persistence.model.PlayerSkillContribution;
import ro.etrcpo.htcalculator.persistence.model.Position;
import ro.etrcpo.htcalculator.persistence.model.SkillContribution;
import ro.etrcpo.htcalculator.persistence.repository.SkillContributionRepository;

import java.util.List;

@Service
public class PlayerSkillContributionService {

    @Autowired
    SkillContributionRepository skillContributionRepository;

    public List<SkillContribution> getSkillContributions() {
        return skillContributionRepository.findAll();
    }

    public List<SkillContribution> getSkillContributionsByTeamPart(Long teamPartId) {
        return skillContributionRepository.findByTeamPart(teamPartId);
    }

    public List<SkillContribution> getSkillContributionsByPosition(Long positionId) {
        return skillContributionRepository.findByPosition(positionId);
    }

    public List<PlayerSkillContribution> getPlayerSkillContributions(Player player, Position position, String centralOrSideFlag) {
        return null;
    }
}
