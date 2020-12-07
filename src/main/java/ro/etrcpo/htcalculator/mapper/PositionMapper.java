package ro.etrcpo.htcalculator.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.etrcpo.htcalculator.dto.PositionDto;
import ro.etrcpo.htcalculator.persistence.model.Position;
import ro.etrcpo.htcalculator.persistence.repository.PositionRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PositionMapper {

    public List<PositionDto> positionToPositionDto(List<Position> positionList) {
        List<PositionDto> positionDtoList = new ArrayList<>();
        for (Position position : positionList) {
            PositionDto positionDto = new PositionDto(position.getId(), position.getName());
            positionDtoList.add(positionDto);
        }
        return positionDtoList;
    }

}


