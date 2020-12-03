package ro.etrcpo.htcalculator.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.etrcpo.htcalculator.dto.AbilityDto;
import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.persistence.model.Player;
import ro.etrcpo.htcalculator.persistence.model.PlayerAbility;
import ro.etrcpo.htcalculator.persistence.model.Skill;
import ro.etrcpo.htcalculator.persistence.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PlayerMapper {

    @Autowired
    SkillRepository skillRepository;

    public Player playerDtoToPlayer(PlayerDto playerDto) {
        List<PlayerAbility> playerAbilities = new ArrayList<>();
        for (Map.Entry<String, AbilityDto> skillDto : playerDto.getSkills().entrySet()) {
            Skill skill = skillRepository.findByName(skillDto.getKey());
            Double value = skillDto.getValue().getValue();
            playerAbilities.add(new PlayerAbility(null, skill, value));
        }

        return new Player(null, playerDto.getName(), playerAbilities);
    }
}


