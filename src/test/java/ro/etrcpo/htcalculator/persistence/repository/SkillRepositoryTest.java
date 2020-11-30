package ro.etrcpo.htcalculator.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.etrcpo.htcalculator.persistence.model.Skill;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SkillRepositoryTest {
    @Autowired
    SkillRepository skillRepository;

    @Test
    public void findByNameShouldReturnSkill() {
        Skill skill = skillRepository.findByName("playmaking");
        assertEquals("Playmaking", skill.getName());
    }
}