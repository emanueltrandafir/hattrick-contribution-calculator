package ro.etrcpo.htcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
