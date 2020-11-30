package ro.etrcpo.htcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.etrcpo.htcalculator.persistence.model.Skill;
import ro.etrcpo.htcalculator.persistence.repository.SkillRepository;

import java.util.List;

@Service
public class PlayerSkillContributionService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }


}
