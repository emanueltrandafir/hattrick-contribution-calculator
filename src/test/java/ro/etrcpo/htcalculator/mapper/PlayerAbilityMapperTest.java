package ro.etrcpo.htcalculator.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.etrcpo.htcalculator.dto.PlayerDto;
import ro.etrcpo.htcalculator.persistence.model.Player;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class PlayerAbilityMapperTest {

    @Autowired
    PlayerMapper playerMapper;

    @Test
    void playerDtoToPlayerAbilities() throws IOException {
        File playerDtoFile = new File(this.getClass().getClassLoader().getResource("playerDto.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        PlayerDto playerDto = objectMapper.readValue(playerDtoFile, PlayerDto.class);

        Player player = playerMapper.playerDtoToPlayer(playerDto);

        Assert.assertNotNull(player);
        Assert.assertEquals("Edu Ibarburen", player.getName());
        Assert.assertEquals("Stamina", player.getPlayerAbilities().get(0).getSkill().getName());
        Assert.assertEquals(new Double(6), player.getPlayerAbilities().get(0).getAbilityValue());
    }
}