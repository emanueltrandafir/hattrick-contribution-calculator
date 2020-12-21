package ro.etrcpo.htcalculator.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.etrcpo.htcalculator.dto.PositionDto;
import ro.etrcpo.htcalculator.persistence.model.Position;
import ro.etrcpo.htcalculator.persistence.repository.PositionRepository;

@Component
public class PositionMapper {

    @Autowired
    PositionRepository positionRepository;

    public Position positionDtoToPosition(PositionDto positionDto) {
        String code = positionDto.getBasePosition().getValue();
        code += positionDto.getOrientation().getValue();
        Position position = positionRepository.findByCode(code);
        return position;
    }

}


