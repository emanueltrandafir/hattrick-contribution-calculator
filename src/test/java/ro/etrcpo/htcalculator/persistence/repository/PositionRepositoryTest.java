package ro.etrcpo.htcalculator.persistence.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.etrcpo.htcalculator.persistence.model.Position;

@SpringBootTest
class PositionRepositoryTest {

    @Autowired
    PositionRepository positionRepository;

    @Test
    public void findByCode() {
        Position position = positionRepository.findByCode("GK");
        Assert.assertEquals("Goalkeeper", position.getName());
    }
}